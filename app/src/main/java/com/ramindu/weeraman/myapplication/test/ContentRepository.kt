package com.ramindu.weeraman.myapplication.test

import java.util.*

class ContentRepository {

    fun generate(): List<Content> {

        val list  = mutableListOf<Content>()

        val rand = Random(System.currentTimeMillis())
        list.add(Content(1, "AA", rand.nextBoolean()))
        list.add(Content(2, "AA", rand.nextBoolean()))
        list.add(Content(3, "AA", rand.nextBoolean()))
        list.add(Content(4, "BA", rand.nextBoolean()))
        list.add(Content(5, "AA", rand.nextBoolean()))
        list.add(Content(6, "AA", rand.nextBoolean()))
        list.add(Content(7, "CA", rand.nextBoolean()))
        list.add(Content(8, "AA", rand.nextBoolean()))
        list.add(Content(9, "DA", rand.nextBoolean()))
        list.add(Content(10, "EA", rand.nextBoolean()))
        list.add(Content(11, "FA", rand.nextBoolean()))
        list.add(Content(12, "GA", rand.nextBoolean()))
        list.add(Content(1, "AA", rand.nextBoolean()))
        list.add(Content(2, "AA", rand.nextBoolean()))
        list.add(Content(3, "AA", rand.nextBoolean()))
        list.add(Content(4, "BA", rand.nextBoolean()))
        list.add(Content(5, "AA", rand.nextBoolean()))
        list.add(Content(6, "AA", rand.nextBoolean()))
        list.add(Content(7, "CA", rand.nextBoolean()))
        list.add(Content(8, "AA", rand.nextBoolean()))
        list.add(Content(9, "DA", rand.nextBoolean()))
        list.add(Content(10, "EA", rand.nextBoolean()))
        list.add(Content(11, "FA", rand.nextBoolean()))
        list.add(Content(12, "GA", rand.nextBoolean()))
        list.add(Content(1, "AA", rand.nextBoolean()))
        list.add(Content(2, "AA", rand.nextBoolean()))
        list.add(Content(3, "AA", rand.nextBoolean()))
        list.add(Content(4, "BA", rand.nextBoolean()))
        list.add(Content(5, "AA", rand.nextBoolean()))
        list.add(Content(6, "AA", rand.nextBoolean()))
        list.add(Content(7, "CA", rand.nextBoolean()))
        list.add(Content(8, "AA", rand.nextBoolean()))
        list.add(Content(9, "DA", rand.nextBoolean()))
        list.add(Content(10, "EA", rand.nextBoolean()))
        list.add(Content(11, "FA", rand.nextBoolean()))
        list.add(Content(12, "GA", rand.nextBoolean()))
        list.add(Content(1, "AA", rand.nextBoolean()))
        list.add(Content(2, "AA", rand.nextBoolean()))
        list.add(Content(3, "AA", rand.nextBoolean()))
        list.add(Content(4, "BA", rand.nextBoolean()))
        list.add(Content(5, "AA", rand.nextBoolean()))
        list.add(Content(6, "AA", rand.nextBoolean()))
        list.add(Content(7, "CA", rand.nextBoolean()))
        list.add(Content(8, "AA", rand.nextBoolean()))
        list.add(Content(9, "DA", rand.nextBoolean()))
        list.add(Content(10, "EA", rand.nextBoolean()))
        list.add(Content(11, "FA", rand.nextBoolean()))
        list.add(Content(12, "GA", rand.nextBoolean()))
        list.add(Content(1, "AA", rand.nextBoolean()))
        list.add(Content(2, "AA", rand.nextBoolean()))
        list.add(Content(3, "AA", rand.nextBoolean()))
        list.add(Content(4, "BA", rand.nextBoolean()))
        list.add(Content(5, "AA", rand.nextBoolean()))
        list.add(Content(6, "AA", rand.nextBoolean()))
        list.add(Content(7, "CA", rand.nextBoolean()))
        list.add(Content(8, "AA", rand.nextBoolean()))
        list.add(Content(9, "DA", rand.nextBoolean()))
        list.add(Content(10, "EA", rand.nextBoolean()))
        list.add(Content(11, "FA", rand.nextBoolean()))
        list.add(Content(12, "GA", rand.nextBoolean()))


        val items = (1..100).map { Content(it, "Item $it", rand.nextBoolean()) }
        return list.sortedBy { it.label?.toString() }
    }

    fun changeValue(item: Content, selected: Boolean) {
        //val x = items.first { it.id == item.id }
       // x.isSelected = selected
    }
}