package com.example.gong_don_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gong_don_android.retrofit.ApiService
import com.example.gong_don_android.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class SearchActivity : AppCompatActivity() {
    val categories = arrayListOf("전체", "IT", "영상", "글쓰기", "IT", "영상", "글쓰기")
    val categoryAdapter = CategoryAdapter(categories)
    private lateinit var postallService : ApiService;
    private lateinit var retrofit : Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initRetrofit()
        postallService.getPostAll().enqueue(object: Callback<PostList>{
            override fun onResponse(call: Call<PostList>, response: Response<PostList>) {

                if(response.isSuccessful()){
                    Log.d("POSTALL RESULT", "${response.body()?.post?.size}")
                    val body = response.body()
                    body?.let {
                        setAdapter(it.post)
                    }
                }
            }
            override fun onFailure(call: Call<PostList>, t: Throwable) {

            }
        })

        category_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        category_recycler.adapter=categoryAdapter
        back_btn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        categoryAdapter.setItemClickListener(object :CategoryAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                //category 검색 api

                Toast.makeText(this@SearchActivity, "${categories[position].toString()}", Toast.LENGTH_SHORT).show()
                //글씨굵게

            }
        })

    }
    private fun setAdapter(postList : ArrayList<Post>){
        val outsourcingAdapter = OutsourcingAdapter(postList,this)
        outsourcing_list.adapter = outsourcingAdapter
        outsourcing_list.layoutManager = LinearLayoutManager(this)
    }
    private fun initRetrofit(){
        retrofit = RetrofitClient.create()
        postallService = retrofit.create(ApiService::class.java)
    }
}
