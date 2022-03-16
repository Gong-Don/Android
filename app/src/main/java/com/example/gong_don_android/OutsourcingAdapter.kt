package com.example.gong_don_android

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class OutsourcingAdapter(val postList: List<Post>, private val context: Context) :
RecyclerView.Adapter<OutsourcingAdapter.ViewHolder>()/*, Filterable*/ {
    //var filteredPosts = ArrayList<Post>()
    //var itemFilter = ItemFilter()
    /*
    init {
        filteredPosts.addAll(postList)
    }
    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.outsourcing_list, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return postList.count()
        //return filteredPosts.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position], context)
        //holder.bind(filteredPosts[position], context)

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
    //filter
    /*
    override fun getFilter(): Filter {
        return itemFilter
    }
    inner class ItemFilter : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filterString = charSequence.toString()
            val results = FilterResults()
            Log.d("SEARCH", "charSequence : $charSequence")

            //검색이 필요없을 경우를 위해 원본 배열을 복제
            val filteredList: ArrayList<Post> = ArrayList<Post>()
            //공백제외 아무런 값이 없을 경우 -> 원본 배열
            if (filterString.trim { it <= ' ' }.isEmpty()) {
                results.values = postList
                results.count = postList.size

                return results
                //공백제외 2글자 이하인 경우 -> 제목으로만 검색
            } else if (filterString.trim { it <= ' ' }.length <= 2) {
                for (post in postList) {
                    if (post.title.contains(filterString)) {
                        filteredList.add(post)
                    }
                }
                //그 외의 경우(공백제외 2글자 초과) -> 제목/내용으로 검색
            } else {
                for (post in postList) {
                    if (post.title.contains(filterString) || post.content.contains(filterString)) {
                        filteredList.add(post)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size

            return results
        }
        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
            filteredPosts.clear()
            filteredPosts.addAll(filterResults.values as ArrayList<Post>)
            notifyDataSetChanged()
        }
    }*/
}
