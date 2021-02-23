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
import com.ramindu.weeraman.domain.entities.EventModel
import com.ramindu.weeraman.myapplication.R
import com.ramindu.weeraman.myapplication.ui.manageEvents.EventsAdapter
import java.util.*


class ContentAdapter2(private val itemActionListener: ItemActionListener, private val list: List<Content>) : RecyclerView.Adapter<ContentAdapter2.MMVFilterViewHolder>(),

    SectionIndexer {

    private var mSectionPositions: MutableList<Int> = ArrayList(26)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MMVFilterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MMVFilterViewHolder, position: Int) {
        holder.bind(list[position])
    }
/*
    class MMVFilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val label: TextView = itemView.findViewById(R.id.text)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
    }*/

    inner class MMVFilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val label = itemView.findViewById<TextView>(R.id.text)
        private val checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)

        fun bind(item: Content) {
            label.text = item.label
            checkBox.isChecked = item.isSelected
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) itemActionListener.onItemClick(list[adapterPosition])
            }

            checkBox.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemActionListener.checkItem(list[adapterPosition], checkBox.isChecked)
                }
            }
        }
    }

    /*override fun getSections(): Array<String> {
        val sections: MutableList<String> = ArrayList(26)
        mSectionPositions = ArrayList<Int>(26)
            var i = 0
            val size: Int = list.size
            while (i < size) {
                val section: String = list.get(i).label.get(0).toString().toUpperCase()
                if (!sections.contains(section)) {
                    sections.add(section)
                    mSectionPositions.add(i)
                }
                i++
            }

        return sections.toTypedArray()
    }*/

    override fun getSections(): Array<String> {
        val sections: MutableList<String> = ArrayList(26)
        list.forEachIndexed { index, content ->
            val section = content.label[0].toString().toUpperCase()
            if (!sections.contains(section)) {
                sections.add(section)
                mSectionPositions.add(index)
            }
        }

        return sections.toTypedArray()
    }

    override fun getSectionForPosition(p0: Int): Int {
        return 0
    }

    override fun getPositionForSection(p0: Int): Int {
        return mSectionPositions[p0]
    }
}
