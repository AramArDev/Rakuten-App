package com.example.rakuten_test_technique.domain

import com.example.rakuten_test_technique.data.APIs
import com.example.rakuten_test_technique.data.list_products_entity.SearchList
import javax.inject.Inject

/**
 * The [ProductsListUseCase] class that offers the services for [SearchList].
 */
class ProductsListUseCase @Inject constructor(
    private val apis: APIs
) {

    suspend operator fun invoke(keyword: String): SearchList {
        return apis.getSearchList(keyword)
    }

}