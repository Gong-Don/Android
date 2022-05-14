package com.example.gong_don_android.ui.postDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gong_don_android.*
import com.example.gong_don_android.network.ApiService
import com.example.gong_don_android.network.RetrofitClient
import com.example.gong_don_android.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_post_detail.*
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
                    tv_title_post_detail.text = post.title
                    postContent.text = post.content
                    tv_cate_post_detail.text = mappingLocation(post.category.toString())
                    postPrice.text = post.price.toString()
                    tv_date_post_detail.text = post.date
                    setFileAdapter(post.files)
                }
            }
            override fun onFailure(call: Call<PostData>, t: Throwable) {
                Log.d("GETPOST RESULT", t.toString())
            }
        })

        val toolbar = findViewById<Toolbar>(R.id.toolbar_post_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
        return when(english) {
            "DESIGN" -> "디자인"
            "IT" -> "IT"
            "MEDIA" -> "미디어"
            "TRANSLATION" -> "번역"
            "DOCUMENT" -> "문서"
            "STUDY" -> "스터디"
            else -> "디자인"
        }
    }
}