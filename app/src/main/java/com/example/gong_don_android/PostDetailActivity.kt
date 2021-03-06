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
import kotlinx.android.synthetic.main.tag.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File

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
                    postCategory.text = mappingLocation(post.category.toString())
                    postPrice.text = post.price.toString()
                    setFileAdapter(post.files)
                }
            }
            override fun onFailure(call: Call<PostData>, t: Throwable) {
                Log.d("GETPOST RESULT", t.toString())
            }
        })

        back_btn.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setAdapter(tagList : ArrayList<String>){
        val tagAdapter = TagAdapter(tagList)
        tag_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        tag_recycler.adapter = tagAdapter
        Log.d("TAG COUNT", tagAdapter.itemCount.toString())
    }
    private fun setFileAdapter(fileList : ArrayList<FileData>){
        val fileAdapter = FileAdapter(this, fileList)
        file_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        file_recycler.adapter = fileAdapter
        Log.d("FILE COUNT", fileAdapter.itemCount.toString())
    }
    private fun initRetrofit(){
        retrofit = RetrofitClient.create()
        postdetailService = retrofit.create(ApiService::class.java)
        postdetailService = retrofit.create(ApiService::class.java)
    }
    private fun mappingLocation(english: String):String {
        when(english) {
            "DESIGN" -> return "?????????"
            "IT" -> return "IT"
            "MEDIA" -> return "?????????"
            "TRANSLATION" -> return "??????"
            "DOCUMENT" -> return "??????"
            "STUDY" -> return "?????????"
            else -> return "?????????"
        }
    }
}