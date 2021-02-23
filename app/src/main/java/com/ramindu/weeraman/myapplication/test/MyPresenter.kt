package com.ramindu.weeraman.myapplication.test

import java.util.ArrayList

class MyPresenter {
    var contentRepository: ContentRepository = ContentRepository()

    fun getValues(): List<Content> {
        val list = ArrayList<Content>()
        list.addAll(contentRepository.generate())
        return list
    }

    fun changeMe(item: Content, isSelected: Boolean) {
        contentRepository.changeValue(item, isSelected)
    }
}