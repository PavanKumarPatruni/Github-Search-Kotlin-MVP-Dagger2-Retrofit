package com.pavanpathro.dagger2demo.repo

import com.pavanpathro.dagger2demo.api.models.Owner

interface RepoView {

    fun attachContributors(list: List<Owner>)

    fun changeMessage(message: String)

}