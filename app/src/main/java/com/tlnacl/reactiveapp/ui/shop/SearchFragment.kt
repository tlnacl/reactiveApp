package com.tlnacl.reactiveapp.ui.shop

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.tlnacl.reactiveapp.R

/**
 * Created by tomt on 21/06/17.
 */
class SearchFragment: Fragment(),SearchView{
    @BindView(R.id.searchView) lateinit var searchView: android.widget.SearchView
    @BindView(R.id.container) lateinit var container: ViewGroup
    @BindView(R.id.loadingView) lateinit var loadingView: View
    @BindView(R.id.errorView) lateinit var errorView: TextView
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView
    @BindView(R.id.emptyView) lateinit var emptyView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_search, container, false)
        ButterKnife.bind(this,view!!)
        return view
    }

    override fun render(searchViewState: SearchViewState) {

    }

}