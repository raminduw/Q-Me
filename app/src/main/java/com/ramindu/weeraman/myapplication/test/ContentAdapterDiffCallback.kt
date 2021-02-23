package com.ramindu.weeraman.myapplication.test

import androidx.recyclerview.widget.DiffUtil

class ContentAdapterDiffCallback : DiffUtil.ItemCallback<Content>() {

    override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
        return oldItem == newItem
    }

}