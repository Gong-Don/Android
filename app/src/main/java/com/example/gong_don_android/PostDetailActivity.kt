package com.example.gong_don_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gong_don_android.retrofit.ApiService
import com.example.gong_don_android.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_post_detail.*
import kotlinx.android.synthetic.main.activity_post_detail.view.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.tag.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PostDetailActivity : AppCompatActivity() {
    private lateinit var postdetailService : ApiService;
    private lateinit var retrofit : Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        val postId = intent.getIntExtra("postId", 0)

        initRetrofit()
        postdetailService.getPost(postId).enqueue(object: Callback<PostData>{
            override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                Log.d("GETPOST RESULT", response.toString())
                if(response.isSuccessful){
                    Log.d("GETPOST RESULT", response.body().toString())
                    var post = response.body()!!
                    setAdapter(post.tags)
                    postTitle.text = post.title
                    postContent.text = post.content
                    postPrice.text = post.price.toString()
                }
            }
            override fun onFailure(call: Call<PostData>, t: Throwable) {
                Log.d("GETPOST RESULT", t.toString())
            }
        })
    }
    private fun setAdapter(tagList : ArrayList<String>){
        val tagAdapter = TagAdapter(tagList)
    }
    private fun initRetrofit(){
        retrofit = RetrofitClient.create()
        postdetailService = retrofit.create(ApiService::class.java)
        postdetailService = retrofit.create(ApiService::class.java)
    }
}