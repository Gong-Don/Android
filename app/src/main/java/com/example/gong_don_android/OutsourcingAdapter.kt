package com.example.gong_don_android

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class OutsourcingAdapter(val postList: ArrayList<Post>, private val context: Context) :
RecyclerView.Adapter<OutsourcingAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.outsourcing_list, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return postList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position], context)

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.outsourcing_name)
        private val txtContent: TextView = itemView.findViewById(R.id.outsourcing_context)
        private val txtState: TextView = itemView.findViewById(R.id.outsourcing_state)

        fun bind(item: Post, context: Context) {
            txtName.text = item.title
            txtContent.text = item.content
            txtState.text = item.matchingStatus.toString()

        }
    }
}
