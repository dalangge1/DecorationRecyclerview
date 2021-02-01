package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextPaint
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataList = ArrayList<String>()
        dataList.addAll(listOf("hh", "asd", "wter", "sddlfkja", "zkjs", "ns", "ha", "hh", "asd", "wter", "sddlfkja", "zkjs", "ns", "sfda", "dfgh", "wxxcvb", "yuioa", "ptswe", "oer", "zsa", "sdf", "sdfg", "csd", "fghj", "pyt", "iree"))
        dataList.sortWith(kotlin.Comparator { o1, o2 -> if (o1[0] < o2[0]) -1 else 1 }) // 正数就要交换位置，负数和0不交换

        val adapter = MyRvAdapter(dataList, this)
        my_rv.adapter = adapter
        my_rv.layoutManager = LinearLayoutManager(this)
        my_rv.addItemDecoration(FirstItemDecoration(my_rv, {
            return@FirstItemDecoration if (it == 0){
                true
            }else{
                dataList[it - 1][0] != dataList[it][0]
            }
        }, {
            return@FirstItemDecoration dataList[it][0].toUpperCase().toString()
        }))



    }
}