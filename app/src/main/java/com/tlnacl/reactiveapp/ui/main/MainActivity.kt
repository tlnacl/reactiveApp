package com.tlnacl.reactiveapp.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import com.jakewharton.rxrelay2.PublishRelay
import com.tlnacl.reactiveapp.AndroidApplication
import com.tlnacl.reactiveapp.GithubApiService
import com.tlnacl.reactiveapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by tomt on 29/05/17.
 */
class MainActivity : AppCompatActivity() {
    @BindView(R.id.etUsername) lateinit var etUsername: EditText
    @BindView(R.id.errorMsg) lateinit var errorMsg: TextView
    @BindView(R.id.pbLoading) lateinit var pbLoading: ProgressBar
    @BindView(R.id.btnShowRepositories) lateinit var btnShowRepositories: Button
    @BindView(R.id.rvRepositories) lateinit var rvRepositories: RecyclerView

    private val layoutManager = LinearLayoutManager(this)
    private val adapter = RepositoryAdapter(this)

    private val changeRequestRelay = PublishRelay.create<MainChange>()
    private var startDisposables = CompositeDisposable()

    @Inject lateinit var apiService: GithubApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        (application as AndroidApplication).appComponent.inject(this)

        rvRepositories.layoutManager = layoutManager
        rvRepositories.adapter = adapter
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        btnShowRepositories.clicks().map { MainChange.Submit(etUsername.text.toString()) }.subscribe(changeRequestRelay)
        etUsername.textChanges()
                .filter { it.length > 3 }
                .debounce(500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .map { MainChange.CheckName(it.toString()) }
                .subscribe { changeRequestRelay }
    }

    override fun onStart() {
        super.onStart()

        startDisposables.add(changeRequestRelay
//                .observeOn(Schedulers.io())
                .subscribe(this::onMainChangeRequested))
    }

    fun onMainChangeRequested(mainChange: MainChange) {
        when (mainChange) {
            is MainChange.Submit -> getRepositories(mainChange.name)
            is MainChange.CheckName -> getUser(mainChange.name)
        }
    }

    private fun getUser(name: String) {
//        apiService.getUser()
    }

    fun getRepositories(name: String) {
        apiService.getUsersRepositories(name)
                .map { items -> MainViewState(items, loading = false) }
                .onErrorReturn { error -> MainViewState(emptyList(), loading = false, errorMsg = error.message!!) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(MainViewState(loading = true))
                .subscribe { render(it) }

    }

    private fun render(viewstate: MainViewState?) {
        pbLoading.visibility = if (viewstate!!.loading) View.VISIBLE else View.GONE
        adapter.setRepositories(viewstate.repositories)
        errorMsg.text = viewstate.errorMsg
    }
}