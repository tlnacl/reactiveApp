package com.tlnacl.reactiveapp.ui.shop

import com.tlnacl.reactiveapp.businesslogic.model.Product

/**
 * Created by tomt on 21/06/17.
 */
sealed class SearchViewState {
    object SearchNotStartedYet : SearchViewState()
    object Loading : SearchViewState()
    data class EmptyResult(val searchQueryText: String) : SearchViewState()
    data class SearchResult(val searchQueryText: String, val result: List<Product>) : SearchViewState()
    data class Error(val searchQueryText: String, val error: Throwable) : SearchViewState()
}