package com.ramindu.weeraman.myapplication.test

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.SectionIndexer
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ramindu.weeraman.myapplication.R
import java.util.*


class ContentAdapter(private val itemActionListener: ItemActionListener) : ListAdapter<Content, ContentAdapter.MMVFilterViewHolder>(ContentAdapterDiffCallback()),

    SectionIndexer {

    private var mSectionPositions = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MMVFilterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false))

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: MMVFilterViewHolder, position: Int) {
        Log.d("TAG", "Changed onBindViewHolder")
        val item = getItem(position)
        holder.label.text = item.label

        holder.checkBox.isChecked = item.isSelected
        holder.itemView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) itemActionListener.onItemClick(getItem(holder.adapterPosition))
        }

        holder.checkBox.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                itemActionListener.checkItem(getItem(holder.adapterPosition), holder.checkBox.isChecked)
            }
        }
    }

    class MMVFilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val label: TextView = itemView.findViewById(R.id.text)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
    }

    override fun getSections(): Array<String> {
        val sections: MutableList<String> = ArrayList(26)
        mSectionPositions = ArrayList<Int>(26)
            var i = 0
            val size: Int = currentList.size
            while (i < size) {
                val section: String = currentList.get(i).label.get(0).toString().toUpperCase()
                if (!sections.contains(section)) {
                    sections.add(section)
                    mSectionPositions.add(i)
                }
                i++
            }

        return sections.toTypedArray<String>()
        //return Testjava().getSections(currentList)
    }

    override fun getSectionForPosition(p0: Int): Int {
        return 0
    }

    override fun getPositionForSection(p0: Int): Int {
        return mSectionPositions.get(p0)
    }
}

