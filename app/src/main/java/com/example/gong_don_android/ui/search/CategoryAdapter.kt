package com.example.gong_don_android.ui.search

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.recyclerview.widget.RecyclerView
import com.example.gong_don_android.Category
import com.example.gong_don_android.R
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(var datas: ArrayList<Category>): RecyclerView.Adapter<CategoryAdapter.ListAdapter>() {
    var col_index : Int = 0
    class ListAdapter(val layout: View) : RecyclerView.ViewHolder(layout)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter {
        return ListAdapter(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListAdapter, position: Int) {
        holder.layout.category.text = datas[position].cate
        val cate = holder.itemView.findViewById<TextView>(R.id.category)
        val cateView : LinearLayout = holder.itemView.findViewById(R.id.category_view)

        if(col_index==position){
            cateView.setBackgroundResource(R.drawable.cate_selected)
            cate.setTextColor(Color.parseColor("#000000"))
            cate.setTypeface(null, Typeface.BOLD)
            cate.setTextSize(Dimension.SP, 15F)
        }
        else{
            cateView.setBackgroundResource(R.drawable.cate)
            cate.setTextColor(Color.parseColor("#8F9090"))
            cate.setTypeface(null, Typeface.NORMAL)
            cate.setTextSize(Dimension.SP, 13F)
        }

        // (1) 리스트 내 항목 클릭 시 onClick() 호출
            holder.itemView.setOnClickListener {
                itemClickListener.onClick(it, position)
                col_index = position
                notifyDataSetChanged()
            }

    }

    override fun getItemCount(): Int {
        return datas.size
    }

    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {

        this.itemClickListener = onItemClickListener
    }

    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener: OnItemClickListener
}