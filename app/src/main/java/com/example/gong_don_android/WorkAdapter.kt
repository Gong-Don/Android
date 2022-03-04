package com.example.gong_don_android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gong_don_android.retrofit.WorkData

class WorkAdapter(private val context: Context) :

RecyclerView.Adapter<WorkAdapter.ViewHolder>() {

    var datas = mutableListOf<WorkData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.work_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.work_name)
        private val txtContext: TextView = itemView.findViewById(R.id.work_context)
        private val txtDate: TextView = itemView.findViewById(R.id.work_date)

        fun bind(item: WorkData) {
            txtName.text = item.name
            txtContext.text = item.context
            txtDate.text = item.date
        }
    }
}
