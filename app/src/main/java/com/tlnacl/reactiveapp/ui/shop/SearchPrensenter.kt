package com.tlnacl.reactiveapp.ui.shop

import com.jakewharton.rxrelay2.PublishRelay
import com.tlnacl.reactiveapp.businesslogic.searchengine.SearchEngine
import com.tlnacl.reactiveapp.ui.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by tomt on 23/06/17.
 */
class SearchPrensenter
    @Inject constructor(val searchEngine: SearchEngine): BasePresenter<SearchView>(){
    private var startDisposables = CompositeDisposable()
    private val changeRequestRelay = PublishRelay.create<String>()
    //TODO init state is in all presenter due to presenter is for ui move to BasePresenter
    fun initState(){
        mvpView?.render(SearchViewState.SearchNotStartedYet)
//        startDisposables.add(changeRequestRelay
////                .observeOn(Schedulers.io())
//                .subscribe(this::search))
    }

    //query can be wrap by SearchEvent if it gets complecated
    //TODO handle ui event is in all presenter move to BasePresenter
    fun handleUiEvent(query: Observable<String>) {
        query.flatMap {
            searchEngine.searchFor(it)
                    .doOnNext { Timber.d("2:" + Thread.currentThread().toString()) }
                    .map<SearchViewState> { products -> SearchViewState.SearchResult(products) }
                    .doOnNext { Timber.d(it.toString()) }
                    .onErrorReturn { error -> SearchViewState.Error(error) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(SearchViewState.Loading)
        }
                .subscribe { mvpView?.render(it) }
    }

//    fun search (query:String){
//
//        Timber.d("1:" + Thread.currentThread().toString())
//        //Add <SearchViewState> on map for generic issue
//        searchEngine.searchFor(query)
//                .doOnNext {  Timber.d("2:" + Thread.currentThread().toString()) }
//                .map<SearchViewState> { products -> SearchViewState.SearchResult( products) }
//                .doOnNext { Timber.d(it.toString()) }
//                .onErrorReturn { error -> SearchViewState.Error(error) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .startWith(SearchViewState.Loading)
//                .doOnNext {  Timber.d("3:" + Thread.currentThread().toString()) }
//                .subscribe { mvpView?.render(it) }
//    }
}