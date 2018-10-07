package com.pavanpathro.dagger2demo.owner

import com.pavanpathro.dagger2demo.api.models.Item

interface OwnerView {

    fun attachRepos(items: List<Item>)

}