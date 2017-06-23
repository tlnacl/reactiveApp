package com.tlnacl.reactiveapp.ui.shop

import com.tlnacl.reactiveapp.businesslogic.http.ProductBackendApi
import com.tlnacl.reactiveapp.ui.BasePresenter
import javax.inject.Inject

/**
 * Created by tomt on 23/06/17.
 */
class SearchPrensenter
    @Inject constructor(api: ProductBackendApi): BasePresenter<SearchView>(){
    //TODO init state is in all presenter due to presenter is for ui move to BasePresenter
    fun initState(){
        mvpView?.render(SearchViewState.SearchNotStartedYet)
    }

    /**
     * class TimerPresenter
    @Inject constructor(override var view: TimerContract.TimerView)
    : TimerContract.UserActionsListener {
    ...
    }
     */
}