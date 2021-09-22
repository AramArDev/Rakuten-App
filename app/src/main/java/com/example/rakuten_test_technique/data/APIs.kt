package com.example.rakuten_test_technique.data

import com.example.rakuten_test_technique.data.list_products_entity.SearchList
import com.example.rakuten_test_technique.data.product_detail_entity.ProductDetail
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The interface that offers the APIs.
 */
interface APIs {

    /**
     * Retrieve the list of products searched [SearchList] par by [keyword] search.
     */
    @GET("search")
    suspend fun getSearchList(@Query("keyword") keyword: String): SearchList

    /**
     * Retrieve the details of a product [ProductDetail] searched by its [id].
     */
    @GET("details")
    suspend fun getDetail(@Query("id") productId: Long): ProductDetail
}