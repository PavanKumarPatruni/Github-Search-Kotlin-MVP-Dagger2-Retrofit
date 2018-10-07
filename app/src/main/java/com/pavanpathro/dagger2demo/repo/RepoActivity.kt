package com.pavanpathro.dagger2demo.repo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.pavanpathro.dagger2demo.api.Constants
import com.pavanpathro.dagger2demo.api.models.Item
import com.pavanpathro.dagger2demo.api.models.Owner
import com.pavanpathro.dagger2demo.owner.OwnerActivity
import com.pavanpathro.dagger2demo.webview.WebViewActivity
import com.pavanpathro.dagger2demo.R
import com.pavanpathro.dagger2demo.adapters.ContributorsAdapter
import com.pavanpathro.dagger2demo.application.GithubApplication
import kotlinx.android.synthetic.main.activity_repo.*

class RepoActivity : AppCompatActivity(), RepoView, ContributorsAdapter.OnItemClickListener {

    private var repoPresenterImpl: RepoPresenterImpl? = null
    private var contributorsAdapter: ContributorsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        repoPresenterImpl = RepoPresenterImpl(this)

        initRecyclerView()

        getIntentData()
    }

    private fun getIntentData() {
        val item: Item = intent.getParcelableExtra(Constants.REPO_DETAILS) as Item

        textViewName.text = item.name
        textViewLink.text = item.htmlUrl
        textViewDesc.text = item.description

        GithubApplication.picasso.load(item.owner.avatarUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageViewProfile)

        repoPresenterImpl?.getContributors(item.owner.login, item.name)

        textViewLinkClick.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(Constants.REPO_NAME, item.name)
            intent.putExtra(Constants.REPO_URL, item.htmlUrl)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        recyclerViewContributors.itemAnimator = DefaultItemAnimator()
        recyclerViewContributors.layoutManager = GridLayoutManager(this, 4)
        ViewCompat.setNestedScrollingEnabled(recyclerViewContributors, false)

        contributorsAdapter = ContributorsAdapter()
        contributorsAdapter!!.onItemClickListener = this

        recyclerViewContributors.adapter = contributorsAdapter
    }

    override fun attachContributors(list: List<Owner>) {
        contributorsAdapter?.updateList(list)
        hideMessage()
    }

    override fun changeMessage(message: String) {
        textViewFetching.text = message
        showMessage()
    }

    private fun hideMessage() {
        textViewFetching.visibility = View.GONE
        recyclerViewContributors.visibility = View.VISIBLE
    }

    private fun showMessage() {
        recyclerViewContributors.visibility = View.GONE
        textViewFetching.visibility = View.VISIBLE
    }

    override fun onItemClick(owner: Owner) {
        launchProfileActivity(owner)
    }

    private fun launchProfileActivity(owner: Owner) {
        val intent = Intent(this, OwnerActivity::class.java)
        intent.putExtra(Constants.OWNER_DETAILS, owner)
        startActivity(intent)
    }


}
