package com.nandroidex.demostickyheaderrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val dummyList: ArrayList<Dummy> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createDummyList()

        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = DummyAdapter(dummyList, this)

        rvItems.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager: LinearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager
                val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
                val headerDate = dummyList[firstVisiblePosition].date
                Log.e("TAG", headerDate)
                tvHeaderStrip.text = headerDate
            }
        })
    }

    private fun createDummyList() {
        for (i in 0..4) {
            dummyList.add(Dummy("20 November, 2019"))
        }
        for (i in 0..4) {
            dummyList.add(Dummy("21 November, 2019"))
        }
        for (i in 0..4) {
            dummyList.add(Dummy("22 November, 2019"))
        }
        for (i in 0..4) {
            dummyList.add(Dummy("23 November, 2019"))
        }
        for (i in 0..4) {
            dummyList.add(Dummy("24 November, 2019"))
        }
        for (i in 0..4) {
            dummyList.add(Dummy("25 November, 2019"))
        }
    }
}
