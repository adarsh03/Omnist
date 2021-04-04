package com.example.omnist.viewModel

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.omnist.R
import com.example.omnist.data.login.LoginData
import com.example.omnist.repo.LoginRepository
import com.example.omnist.utils.Coroutines
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject

class LoginViewModel(application: Application) : AndroidViewModel(application) {


     val repository: LoginRepository

    var emailtext = MutableLiveData<String>().apply { postValue("") }
    var passwordText = MutableLiveData<String>().apply { postValue("") }
    var loginProgressBar =  MutableLiveData<Int>().apply {postValue(View.GONE)  }
    var canenablebutton = MutableLiveData<Boolean>().apply { postValue(true) }

init {
    repository = LoginRepository(application)
}


        fun loginMothodCall(view: View) {


            if (emailtext.value.isNullOrEmpty()) {
                Snackbar.make(view, "Please enter Email", Snackbar.LENGTH_LONG).show()
            } else if (passwordText.value.isNullOrEmpty()) {
                Snackbar.make(view, "Please enter Password", Snackbar.LENGTH_LONG).show()
            } else {
                loginProgressBar.value = View.VISIBLE
                canenablebutton.value = false

                    var response: retrofit2.Response<LoginData>?= null
                    Coroutines.main() {

                        response = repository.loginMethodCall(
                            emailtext.value!!,
                            passwordText.value!!
                        )
                        if (response!!.isSuccessful ) {

                            if(response!!.body()!!.responseCode.toString() =="200"){
                                Toast.makeText(this.getApplication(), response!!.body()!!.message.toString(), Toast.LENGTH_SHORT
                                ).show()

                                view.findNavController().navigate(R.id.action_loginFragment_to_projectFragment)
                            }else
                                Toast.makeText(this.getApplication(), response!!.body()!!.message.toString(), Toast.LENGTH_SHORT
                                ).show()

                            loginProgressBar.value = View.GONE
                            canenablebutton.value = true
                        }
                        else {
                            Coroutines.main {

                                try {
                                    if (!response!!.errorBody()!!.equals(null)) {
                                        val jObjError = JSONObject(response!!.errorBody()!!.string())
                                        val error = jObjError.getString("error")
                                        val snackbar = Snackbar.make(view, error, Snackbar.LENGTH_LONG)
                                        snackbar.show()

                                    }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                                canenablebutton.value = true
                                loginProgressBar.value = View.GONE
                            }
                        }

                    }

                }
        }


}