package com.pavanpathro.dagger2demo.home

import com.pavanpathro.dagger2demo.api.models.Item

interface HomeView {

    fun changeMessage(message: String)

    fun showMessage()

    fun hideMessage()

    fun updateList(items: List<Item>)

    fun clearList()

    fun showList()

    fun hideList()

    fun resetFilters()

}