package com.example.gong_don_android

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.tag.view.*

class TagAdapter(var datas: ArrayList<String>): RecyclerView.Adapter<TagAdapter.ListAdapter>() {
    class ListAdapter(val layout: View) : RecyclerView.ViewHolder(layout)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter {
        return ListAdapter(
            LayoutInflater.from(parent.context).inflate(R.layout.tag, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListAdapter, position: Int) {
        holder.layout.taglist.text = datas[position]
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}