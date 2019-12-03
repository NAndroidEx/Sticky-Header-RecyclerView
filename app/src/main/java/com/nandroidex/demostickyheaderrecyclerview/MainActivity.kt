package com.nandroidex.demostickyheaderrecyclerview

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val dummyList: ArrayList<Any> = ArrayList()
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createDummyList()

        setUpRV()
    }

    private fun createDummyList() {
        dummyList.add(Header(""))
        dummyList.add(Date("21 November, 2019"))
        for (i in 0..4) {
            val thumbnail = getThumbnail(i)
            val title = getTitle(i)
            dummyList.add(Dummy("21 November, 2019", thumbnail, title))
        }
        dummyList.add(Date("22 November, 2019"))
        for (i in 0..4) {
            val thumbnail = getThumbnail(i)
            val title = getTitle(i)
            dummyList.add(Dummy("22 November, 2019", thumbnail, title))
        }
        dummyList.add(Date("23 November, 2019"))
        for (i in 0..4) {
            val thumbnail = getThumbnail(i)
            val title = getTitle(i)
            dummyList.add(Dummy("23 November, 2019", thumbnail, title))
        }
        dummyList.add(Date("24 November, 2019"))
        for (i in 0..4) {
            val thumbnail = getThumbnail(i)
            val title = getTitle(i)
            dummyList.add(Dummy("24 November, 2019", thumbnail, title))
        }
        dummyList.add(Date("25 November, 2019"))
        for (i in 0..4) {
            val thumbnail = getThumbnail(i)
            val title = getTitle(i)
            dummyList.add(Dummy("25 November, 2019", thumbnail, title))
        }
    }

    private fun getTitle(i: Int): String {
        return when {
            i % 5 == 4 -> {
                "Hearing aid support on Android"
            }
            i % 5 == 3 -> {
                "Family Link: Digital Wellbeing for the family"
            }
            i % 5 == 2 -> {
                "10 ways Android 10 will help you"
            }
            i % 5 == 1 -> {
                "Say hello to Android’s new brand identity"
            }
            else -> {
                "How to protect your privacy with Android"
            }
        }
    }

    private fun getThumbnail(i: Int): String {
        return when {
            i % 5 == 4 -> {
                "https://img.youtube.com/vi/P5ZmqEoOheQ/0.jpg"
            }
            i % 5 == 3 -> {
                "https://img.youtube.com/vi/d2zPq1lfqQs/0.jpg"
            }
            i % 5 == 2 -> {
                "https://img.youtube.com/vi/1-AozVirb88/0.jpg"
            }
            i % 5 == 1 -> {
                "https://img.youtube.com/vi/-2w7RKAlKTs/0.jpg"
            }
            else -> {
                "https://img.youtube.com/vi/yRf5h0JIgVk/0.jpg"
            }
        }
    }

    private fun setUpRV() {
        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = DummyAdapter(dummyList)
        layoutManager = rvItems.layoutManager as LinearLayoutManager
        rvItems.addOnScrollListener(scrollListener)
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> {
                    hideStrip()
                }
                RecyclerView.SCROLL_STATE_DRAGGING -> {
                    showStrip()
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            updateStrip()
        }
    }

    //show strip when scroll started
    private fun showStrip() {
        tvHeaderStrip.animate().translationY(0F)
            .setInterpolator(DecelerateInterpolator(2F)).start()
    }

    //hide strip when scroll stopped
    private fun hideStrip() {
        tvHeaderStrip.animate().translationY(-tvHeaderStrip.height.toFloat())
            .setInterpolator(AccelerateInterpolator(2F)).start()
    }

    //update strip text
    private fun updateStrip() {
        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
        val headerDate: String
        val any: Any
        when {
            dummyList[firstVisiblePosition] is Header -> {
                headerDate = ""
            }
            dummyList[firstVisiblePosition] is Date -> {
                any = dummyList[firstVisiblePosition] as Date
                headerDate = any.date
            }
            else -> {
                any = dummyList[firstVisiblePosition] as Dummy
                headerDate = any.date
            }
        }
        if (headerDate.isEmpty()) {
            tvHeaderStrip.visibility = GONE
        } else {
            tvHeaderStrip.visibility = VISIBLE
            tvHeaderStrip.text = headerDate
        }
    }
}
