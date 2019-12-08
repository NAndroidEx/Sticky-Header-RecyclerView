package com.nandroidex.demostickyheaderrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView

import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.futuremind.recyclerviewfastscroll.SectionTitleProvider
import com.squareup.picasso.Picasso

class DummyAdapter(private val dummyList: List<*>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), SectionTitleProvider {

    private val headerView = 0
    private val dateView = 1
    private val itemView = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            headerView -> {
                HeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.list_item_header,
                        parent,
                        false
                    )
                )
            }
            dateView -> {
                DateViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.list_item_date,
                        parent,
                        false
                    )
                )
            }
            else -> {
                ItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.list_item_dummy,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            getItemViewType(position) == headerView -> {
                onBindHeaderViewHolder()
            }
            getItemViewType(position) == dateView -> {
                val dateViewHolder: DateViewHolder = holder as DateViewHolder
                onBindDateViewHolder(dateViewHolder, position)
            }
            else -> {
                val itemViewHolder: ItemViewHolder = holder as ItemViewHolder
                onBindItemViewHolder(itemViewHolder, position)
            }
        }
    }

    private fun onBindHeaderViewHolder() {
        //NO DATA
    }

    private fun onBindDateViewHolder(holder: DateViewHolder, position: Int) {
        val dummy: Date = dummyList[position] as Date
        holder.tvHeader.text = dummy.date
    }

    private fun onBindItemViewHolder(holder: ItemViewHolder, position: Int) {
        val dummy: Dummy = dummyList[position] as Dummy
        Picasso.get().load(dummy.thumbnail).into(holder.ivThumbnail)
        holder.tvName.text = dummy.name
        holder.tvDate.text = dummy.date
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            dummyList[position] is Header -> {
                headerView
            }
            dummyList[position] is Date -> {
                dateView
            }
            else -> {
                itemView
            }
        }
    }

    override fun getItemCount(): Int {
        return dummyList.size
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHeader: AppCompatTextView = itemView.findViewById(R.id.tvHeader)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate: AppCompatTextView = itemView.findViewById(R.id.tvDate)
        val ivThumbnail: AppCompatImageView = itemView.findViewById(R.id.ivThumbnail)
        val tvName: AppCompatTextView = itemView.findViewById(R.id.tvName)
    }

    override fun getSectionTitle(position: Int): String {
//        val date = dummyList[position] as Date
        return "DATE"
    }
}