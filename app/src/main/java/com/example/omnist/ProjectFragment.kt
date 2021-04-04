package com.example.omnist

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.omnist.adapter.ProjectAdapter
import com.example.omnist.data.projectData.Data
import com.example.omnist.data.projectData.ProjectEntity
import com.example.omnist.databinding.FragmentProjectBinding
import com.example.omnist.viewModel.ProjectViewModel
import kotlinx.android.synthetic.main.fragment_project.*

class ProjectFragment : Fragment() {

    lateinit var viewModel: ProjectViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_project, container, false)
        val binding: FragmentProjectBinding = FragmentProjectBinding.bind(view)
        viewModel = ViewModelProviders.of(this).get(ProjectViewModel::class.java)
        binding.projectViewModel = viewModel
        binding.lifecycleOwner = this

        if(isNetworkConnected(view.context))
        {
            try {
                viewModel.getProjectFromAPIAndStore()

            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        else
        {
            Toast.makeText(context,"No internet found.", Toast.LENGTH_LONG).show()
        }
       lateinit var projectResults: List<Data>
        viewModel.getAllProjectData().observe(viewLifecycleOwner, Observer<List<ProjectEntity>> { projectData ->
            try{
                 projectResults = projectData.get(0).data!!
            }catch (e: java.lang.Exception){
                e.printStackTrace()
            }


            setUpProjectRecyclerView(projectResults)
        })
        return view
    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun setUpProjectRecyclerView(projectDataList : List<Data>)
    {
        val projectRecyclerViewAdapter = ProjectAdapter(projectDataList)
        rvProject.adapter = projectRecyclerViewAdapter
        rvProject.layoutManager = LinearLayoutManager(context,
            RecyclerView.VERTICAL,false)
        rvProject.setHasFixedSize(true)


    }

}