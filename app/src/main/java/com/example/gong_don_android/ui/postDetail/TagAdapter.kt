package com.example.gong_don_android.ui.postDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gong_don_android.R
import kotlinx.android.synthetic.main.item_tag.view.*

class TagAdapter(var datas: ArrayList<String>) : RecyclerView.Adapter<TagAdapter.ListAdapter>() {
    class ListAdapter(val layout: View) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter {
        return ListAdapter(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListAdapter, position: Int) {
        holder.layout.taglist.text = datas[position]
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}