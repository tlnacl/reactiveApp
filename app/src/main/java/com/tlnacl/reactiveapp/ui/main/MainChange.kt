package com.tlnacl.reactiveapp.ui.main

/**
 * Created by tomt on 29/05/17.
 */
sealed class MainChange {
    data class Submit(val name: String = "") : MainChange()
    data class CheckName(val name: String = "") : MainChange()
}