package com.example.rakuten_test_technique.data.list_products_entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchList(
    val maxPageNumber: Int,
    val maxProductsPerPage: Int,
    val pageNumber: Int,
    val resultProductsCount: Int,
    val title: String,
    val totalResultProductsCount: Int,
    val products: List<Product>
) : Parcelable