package com.pavanpathro.dagger2demo.owner

import android.content.Context
import com.pavanpathro.dagger2demo.application.GithubApplication
import com.pavanpathro.dagger2demo.api.models.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OwnerPresenterImpl(context: Context): OwnerPresenter {

    private var ownerView: OwnerView = context as OwnerView

    override fun getRepos(name: String) {

        GithubApplication.service.getReposByName(name).enqueue(object: Callback<List<Item>> {
            override fun onFailure(call: Call<List<Item>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                ownerView.attachRepos(response.body()!!)
            }
        })

    }

}