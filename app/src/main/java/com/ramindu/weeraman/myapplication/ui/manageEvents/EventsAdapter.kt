package com.ramindu.weeraman.myapplication.ui.manageEvents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ramindu.weeraman.domain.entities.EventModel
import com.ramindu.weeraman.myapplication.R

class EventsAdapter(var items: List<EventModel>, val callback: Callback) : RecyclerView.Adapter<EventsAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_row, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(dataList: List<EventModel>) {
        items = dataList
        notifyDataSetChanged()
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val firstName = itemView.findViewById<TextView>(R.id.itemTitleTextView)
        private val lastName = itemView.findViewById<TextView>(R.id.itemSubtitleTextView)

        fun bind(item: EventModel) {
            firstName.text = item.name
            lastName.text = item.description
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
            }
        }
    }

    interface Callback {
        fun onItemClicked(item: EventModel)
    }

}