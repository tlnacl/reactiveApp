package com.tlnacl.reactiveapp.ui.shop

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.tlnacl.reactiveapp.R
import com.tlnacl.reactiveapp.businesslogic.model.Product
import com.tlnacl.reactiveapp.ui.widgets.GridSpacingItemDecoration

/**
 * Created by tomt on 21/06/17.
 */
class SearchFragment: Fragment(),SearchView, SearchAdapter.ViewHolder.ProductClickedListener {
    override fun onProductClicked(product: Product) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        ProductDetailsActivity.start(activity, product)
    }

    @BindView(R.id.searchView) lateinit var searchView: android.widget.SearchView
    @BindView(R.id.container) lateinit var container: ViewGroup
    @BindView(R.id.loadingView) lateinit var loadingView: View
    @BindView(R.id.errorView) lateinit var errorView: TextView
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView
    @BindView(R.id.emptyView) lateinit var emptyView: View

//    private val adapter: SearchAdapter = SearchAdapter(activity,this)

    private lateinit var adapter: SearchAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_search, container, false)
        ButterKnife.bind(this,view!!)
        adapter = SearchAdapter(activity,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.addItemDecoration(GridSpacingItemDecoration(2,
                resources.getDimensionPixelSize(R.dimen.grid_spacing), true))
        return view
    }

    override fun render(searchViewState: SearchViewState) {

    }

}