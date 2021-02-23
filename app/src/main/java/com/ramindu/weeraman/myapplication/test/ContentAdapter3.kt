package com.ramindu.weeraman.myapplication.test

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.SectionIndexer
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ramindu.weeraman.myapplication.R
import java.util.*


class ContentAdapter3(private val itemActionListener: ItemActionListener2) : ListAdapter<Content, ContentAdapter3.MMVFilterViewHolder>(MMVFilterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MMVFilterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false))

    override fun onBindViewHolder(holder: MMVFilterViewHolder, position: Int) {
        Log.d("TAG","###### onBindViewHolder")
        val item = getItem(position)
        holder.label.text = item.label
        holder.checkBox.isChecked = item.isSelected
        holder.itemView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) itemActionListener.onItemClicked(currentList[holder.adapterPosition])
        }
        holder.checkBox.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                itemActionListener.checkItem(currentList[holder.adapterPosition], holder.checkBox.isChecked)
            }
        }
    }

    override fun getItemCount() = currentList.size

    /*inner class MMVFilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val label = itemView.findViewById<TextView>(R.id.text)
        private val checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)

        fun bind(item: Content) {
            Log.d("TAG","CHANGED")
            label.text = item.label
            checkBox.isChecked = item.isSelected
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) itemActionListener.onItemClicked(currentList[adapterPosition])
            }

            checkBox.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemActionListener.checkItem(currentList[adapterPosition], checkBox.isChecked)
                }
            }
        }
    }*/


    class MMVFilterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val label: TextView = view.findViewById(R.id.text)
        val checkBox: CheckBox = view.findViewById(R.id.checkbox)
    }
}

class MMVFilterDiffCallback : DiffUtil.ItemCallback<Content>() {

    override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
        return oldItem == newItem
    }
}

interface ItemActionListener2 {
    fun onItemClicked(item: Content)
    fun checkItem(item: Content, isSelected: Boolean)
}

