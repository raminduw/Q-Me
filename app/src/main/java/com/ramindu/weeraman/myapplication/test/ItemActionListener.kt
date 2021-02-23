package com.ramindu.weeraman.myapplication.test

interface ItemActionListener {
    fun onItemClick(item: Content)
    fun checkItem(item: Content, isSelected: Boolean)
}