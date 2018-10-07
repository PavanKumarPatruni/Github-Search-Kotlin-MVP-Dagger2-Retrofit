package com.pavanpathro.dagger2demo.repo

interface RepoPresenter {

    fun getContributors(ownerName: String, repoName: String)

}