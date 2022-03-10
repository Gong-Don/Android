package com.example.gong_don_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gong_don_android.retrofit.ApiService
import com.example.gong_don_android.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class SearchActivity : AppCompatActivity() {
    val categories = arrayListOf(Category.ALL, Category.IT, Category.DESIGN, Category.TRANSLATION, Category.DOCUMENT, Category.STUDY)
    val categoryAdapter = CategoryAdapter(categories)

    //lateinit var outsourcingAdapter: OutsourcingAdapter
    //lateinit var search_view_post: SearchView

    private lateinit var postallService : ApiService;
    private lateinit var postCateService : ApiService;
    private lateinit var retrofit : Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //search_view_post = findViewById(R.id.search)
        //search_view_post.setOnQueryTextListener(searchViewTextListener)

        initRetrofit()
        postallService.getPostAll().enqueue(object: Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                Log.d("POSTALL RESULT", response.toString())
                if(response.isSuccessful) {
                    Log.d("POSTALL RESULT", response.body().toString())
                    Log.d("POSTALL RESULT", "${response.body()?.size}")
                    val body = response.body()
                    body?.let {
                        setAdapter(body)
                    }
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("POSTALL RESULT", t.toString())
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
                if(position==0){
                    postallService.getPostAll().enqueue(object: Callback<List<Post>>{
                        override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                            Log.d("POSTALL RESULT", response.toString())
                            if(response.isSuccessful) {
                                Log.d("POSTALL RESULT", response.body().toString())
                                Log.d("POSTALL RESULT", "${response.body()?.size}")
                                val body = response.body()
                                body?.let {
                                    setAdapter(body)
                                }
                            }
                        }
                        override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                            Log.d("POSTALL RESULT", t.toString())
                        }
                    })
                }else{
                    postCateService.getPostCategory(categories[position].toString()).enqueue(object: Callback<List<Post>>{
                        override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                            Log.d("GETPOSTCATE RESULT", response.toString())
                            if(response.isSuccessful) {
                                Log.d("GETPOSTCATE RESULT", response.body().toString())
                                Log.d("GETPOSTCATE RESULT", "${response.body()?.size}")
                                val body = response.body()
                                body?.let {
                                    setAdapter(body)
                                }
                            }
                        }
                        override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                            Log.d("POSTALL RESULT", t.toString())
                        }
                    })
                }
                //Toast.makeText(this@SearchActivity, "${categories[position].toString()}", Toast.LENGTH_SHORT).show()
            }
        })

    }
    /*var searchViewTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            //텍스트 입력/수정시에 호출
            override fun onQueryTextChange(s: String): Boolean {
                outsourcingAdapter.getFilter().filter(s)
                Log.d("SEARCH", "SearchVies Text is changed : $s")
                return false
            }
        }*/
    private fun setAdapter(postList : List<Post>){
        val outsourcingAdapter = OutsourcingAdapter(postList,this)
        outsourcing_list.adapter = outsourcingAdapter
        outsourcing_list.layoutManager = LinearLayoutManager(this)
    }
    private fun initRetrofit(){
        retrofit = RetrofitClient.create()
        postallService = retrofit.create(ApiService::class.java)
        postCateService = retrofit.create(ApiService::class.java)
    }

}
