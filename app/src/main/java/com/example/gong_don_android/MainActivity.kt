package com.example.gong_don_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gong_don_android.retrofit.WorkData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var workAdapter: WorkAdapter
    val datas = mutableListOf<WorkData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }
    private fun initRecycler() {
        workAdapter = WorkAdapter(this)
        work_list.adapter = workAdapter

        datas.apply {
            add(WorkData(context = "context1", name = "work1", date = "2022.03.04"))
            add(WorkData(context = "context2", name = "work2", date = "2022.03.04"))

            workAdapter.datas = datas
            workAdapter.notifyDataSetChanged()
        }
    }
}