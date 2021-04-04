package com.example.omnist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.omnist.R
import com.example.omnist.data.projectData.Data
import java.lang.Exception


class ProjectAdapter(val projectList: List<Data?>) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(p0.context)
            .inflate(R.layout.project_items,p0,false)

        return ViewHolder(v)
    }

/*class CustomViewHolder(val view:View):RecyclerView.ViewHolder(view)*/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    val projectData = projectList.get(position)

    holder.projectName.text = projectData!!.projectName
    holder.towerName.text = projectData.tower
    holder.flatName.text = projectData.flats


    }
    override fun getItemCount(): Int {
        var count: Int =0
      try {
          count  = projectList.size
      }catch (e: Exception){
          e.printStackTrace()
      }
        return count
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val projectName : TextView = itemView.findViewById(R.id.tvProjectFilled)
        val towerName : TextView = itemView.findViewById(R.id.tvTowerFilled)
        val flatName : TextView = itemView.findViewById(R.id.tvFlatFilled)

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}