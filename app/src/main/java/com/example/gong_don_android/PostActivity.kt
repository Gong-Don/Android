package com.example.gong_don_android

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import com.example.gong_don_android.network.ApiService
import com.example.gong_don_android.network.RetrofitClient
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_post.*
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

        //var items = arrayOf("SM3", "SM5", "SM7", "SONATA", "AVANTE", "SOUL", "K5", "K7")
        var items : List<String?>
        items = ArrayList<String>()
        var tags = ArrayList<String>()


        initRetrofit()

        retrofitService.getTagAll().enqueue(object: Callback<HashMap<String,Int>>{
            override fun onResponse(call: Call<HashMap<String,Int>>, response: Response<HashMap<String,Int>>) {
                if(response.isSuccessful) {
                    var temp : List<String?>
                    temp=ArrayList(response.body()!!.keys)
                    for(i in 0 until temp.size) {
                        items.add(temp[i].toString())
                    }
                    Log.e("tagSearch",items.toString())//해야할일 버튼 제일 오른쪽으로...  ,chip색깔 바꾸기 ,자동완성 좌우 꽉차게, 서버 그냥 string주게 하기...
                }
            }
            override fun onFailure(call: Call<HashMap<String,Int>>, t: Throwable) {
                Log.d("TagTALL RESULT", t.toString())
            }
        })
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items)
        tagSearch.setOnItemClickListener { adapterView, view, i, l ->
            tags.add(adapterView.getItemAtPosition(i).toString())
        }
        tagSearch.setAdapter(adapter)

        tagPlusBtn.setOnClickListener{
            val now = tagSearch.text
            if(now==null)
                Toast.makeText(this,"입력해주세요",Toast.LENGTH_SHORT).show()
            else {
                if(postChipGroup.size==5) {
                    //tagSearch.isGone=true
                    Toast.makeText(this, "5개 이상 ㄴ", Toast.LENGTH_SHORT).show()
                }
                else {
                    //if(!tagSearch.isVisible)
                        //tagSearch.isVisible=true
                    postChipGroup.addView(Chip(this).apply {
                        text = now
                        isCloseIconVisible = true
                        setOnCloseIconClickListener {
                            tags.remove(it.toString())
                            postChipGroup.removeView(this) }
                    })
                    tagSearch.setText("")
                }
            }
        }

        tv_cate_post_detail.setOnClickListener(){
            locationClicked()
        }

        postButton.setOnClickListener() {
            var title = tv_title_post_detail.text.toString()
            var price = postPrice.text.toString().toInt()
            var content = postContent.text.toString()

            if(title!=""&&postPrice.text.toString()!=""&&content!="") {
                val data = PostArticle(category, content, price, tags,title,App.appId)

                retrofitService.requestPost(data).enqueue(object : Callback<PostResult> {
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>
                    ) {
                        Log.e(
                            "Result", data.toString())
                        Toast.makeText(this@PostActivity, "성공", Toast.LENGTH_SHORT).show()
                        //val intent = Intent(this@PostActivity, SearchActivity::class.java)
                        //startActivity(intent)
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
                tv_cate_post_detail.text = items[which]
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
