package com.example.rakuten_test_technique.data.product_detail_entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImagesUrls(
    val entry: List<Entry>
) : Parcelable