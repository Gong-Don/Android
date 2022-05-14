package com.example.gong_don_android.ui.postDetail

import android.content.Context
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gong_don_android.FileData
import com.example.gong_don_android.R
import java.util.regex.Pattern

class FileAdapter(private val context: Context, val fileList: ArrayList<FileData>) :

RecyclerView.Adapter<FileAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_file, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = fileList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(fileList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val fileName: TextView = itemView.findViewById(R.id.file_name)

        fun bind(item: FileData) {
            fileName.text = item.name
            val mTransform = Linkify.TransformFilter(){ _, _ -> "" }
            val pattern = Pattern.compile(item.name)
            Linkify.addLinks(fileName, pattern, item.url, null, mTransform)
        }
    }
}
