package com.example.gong_don_android

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.ButtonBarLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.outsourcing_list.*

class OutsourcingAdapter(private val context: Context, val postList: List<Post>) :
RecyclerView.Adapter<OutsourcingAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.outsourcing_list, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return postList.count()
    }
    var checkboxList = arrayListOf<likeBtnData>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position], position)

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
            notifyDataSetChanged()
        }
    }

    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, id: Int)
    }

    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {

        this.itemClickListener = onItemClickListener
    }

    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.outsourcing_name)
        private val txtContent: TextView = itemView.findViewById(R.id.outsourcing_context)
        private val txtState: TextView = itemView.findViewById(R.id.outsourcing_state)

        val heart_btn: CheckBox = itemView.findViewById(R.id.like_btn)

        fun bind(item: Post, num: Int) {
            txtName.text = item.title
            txtContent.text = item.content
            txtState.text = item.matchingStatus.toString()

            if(num >= checkboxList.size){
                checkboxList.add(num, likeBtnData(item.postId, false))
            }

            heart_btn.isChecked = checkboxList[num].checked

            heart_btn.setOnClickListener{
                if(heart_btn.isChecked){
                    checkboxList[num].checked = true
                } else{
                    checkboxList[num].checked = false
                }
            }
        }
    }
}
