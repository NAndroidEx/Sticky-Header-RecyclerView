package com.nandroidex.demostickyheaderrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class DummyAdapter(private val dummyList: List<Dummy>, private val context: Context) :
    RecyclerView.Adapter<DummyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_dummy, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = dummyList[position]
        holder.tvHeader.text = video.date
        holder.tvDate.text = video.date
        if (position > 0) {
            val prevDate = dummyList[position - 1].date
            if (video.date != prevDate) {
                holder.tvHeader.visibility = View.VISIBLE
            } else {
                holder.tvHeader.visibility = View.GONE
            }
        } else {
            holder.tvHeader.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return dummyList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate: AppCompatTextView = itemView.findViewById(R.id.tvDate)
        val tvHeader: AppCompatTextView = itemView.findViewById(R.id.tvHeader)
    }
}