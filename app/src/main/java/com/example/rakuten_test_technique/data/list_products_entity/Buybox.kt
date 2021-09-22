package com.example.rakuten_test_technique.data.list_products_entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Buybox(
    val advertQuality: String,
    val advertType: String,
    val isRefurbished: Boolean,
    val saleCrossedPrice: Double,
    val salePercentDiscount: Int,
    val salePrice: Double
) : Parcelable