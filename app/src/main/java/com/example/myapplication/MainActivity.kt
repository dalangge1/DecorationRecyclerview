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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataList = ArrayList<String>()
        dataList.addAll(listOf("hh", "asd", "wter", "sddlfkja", "zkjs", "ns", "ha", "hh", "asd", "wter", "sddlfkja", "zkjs", "ns", "ha"))
        dataList.sortWith(kotlin.Comparator { o1, o2 -> if (o1[0] < o2[0]) -1 else 1 })

        val adapter = MyRvAdapter(dataList, this)
        my_rv.adapter = adapter
        my_rv.layoutManager = LinearLayoutManager(this)
        my_rv.addItemDecoration(object : RecyclerView.ItemDecoration(){
            override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
                if(isFirst(itemPosition)){
                    outRect.set(0, 100, 0, 0)
                }else{
                    outRect.set(0, 0, 0, 0)
                }
            }

            @SuppressLint("ResourceType")
            override fun onDraw(c: Canvas, parent: RecyclerView) {
                val paint = Paint()
                paint.color = Color.DKGRAY
                val textPaint = TextPaint()
                textPaint.color = Color.WHITE
                textPaint.textSize = 50f
                for (i in 0 until  parent.childCount){ // 返回显示在屏幕上的数量
                    // getChildAt 返回显示出来的条目中，第i个条目的view实例
                    // getChildAdapterPosition 返回第i个条目的实例在原数据集的真实位置
                    if(isFirst(parent.getChildAdapterPosition(parent.getChildAt(i)))){
                        c.drawRect(Rect(0, parent.getChildAt(i).top - 100, parent.width, parent.getChildAt(i).top), paint)
                        c.drawText(getFirstChar(parent.getChildAdapterPosition(parent.getChildAt(i))).toString(), 20F, parent.getChildAt(i).top - 30F, textPaint)
                    }

                    Log.d("sandyzhang", parent.getChildLayoutPosition(parent.getChildAt(0)).toString())
                }
                Log.d("sandyzhang-childcount", parent.childCount.toString())
            }
            fun isFirst(pos: Int): Boolean{
                return if (pos == 0){
                    true
                }else{
                    dataList[pos - 1][0] != dataList[pos][0]
                }
            }
            fun getFirstChar(pos: Int): Char{
                return dataList[pos][0]
            }
        })


    }
}