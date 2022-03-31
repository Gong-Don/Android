package com.example.gong_don_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.gong_don_android.retrofit.ApiService
import com.example.gong_don_android.retrofit.RetrofitClient
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PostActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private var category : String = "디자인"
    private lateinit var retrofitService: ApiService;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        initRetrofit()
        postCategory.setOnClickListener(){
            locationClicked()
        }

        postButton.setOnClickListener() {
            var title = postTitle.text.toString()
            var price = postPrice.text.toString().toInt()
            var content = postContent.text.toString()

                //intent.getStringExtra("id").toString()//나중에 해야함

            if(title!=""&&postPrice.text.toString()!=""&&content!="") {
                val data = PostArticle(category, content, price, title,App.appId)

                retrofitService.requestPost(data).enqueue(object : Callback<PostResult> {
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>
                    ) {
                        Log.e(
                            "Result", data.toString())
                        Toast.makeText(this@PostActivity, "성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@PostActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        Log.e("Post", t.message.toString())
                    }
                })
            } else if(title==""){
                Toast.makeText(this, "제목을 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else if(postPrice.text.toString()==""){
                Toast.makeText(this, "가격을 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else if(content==""){
                Toast.makeText(this, "내용을 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "내용을 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initRetrofit(){
        retrofit = RetrofitClient.create()
        retrofitService = retrofit.create(ApiService::class.java)
    }
    private fun locationClicked() {
        val items = arrayOf("디자인","IT","미디어","번역","문서","스터디")
        MaterialAlertDialogBuilder(this)
            .setItems(items) { dialog, which ->
                postCategory.text = items[which]
                category = mappingLocation(items[which])
            }
            .show()
    }

    private fun mappingLocation(korean: String):String {
        when(korean) {
            "디자인"-> return "DESIGN"
            "IT" -> return "IT"
            "미디어" -> return "MEDIA"
            "번역" -> return "TRANSLATION"
            "문서" -> return "DOCUMENT"
            "스터디"-> return "STUDY"
            else -> return "DESIGN"
        }
    }
}