package com.tlnacl.reactiveapp.ui.main

import com.tlnacl.reactiveapp.Repository

/**
 * Created by tomt on 29/05/17.
 */
data class MainViewState(val repositories: List<Repository> = emptyList(), val userName: String = "", val loading: Boolean = false, val errorMsg: String = "")