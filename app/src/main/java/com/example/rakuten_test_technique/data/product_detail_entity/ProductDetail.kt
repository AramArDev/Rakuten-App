package com.example.rakuten_test_technique.data.product_detail_entity

import android.os.Parcelable
import com.example.rakuten_test_technique.utils.AdvertQualityFilter
import com.example.rakuten_test_technique.utils.AdvertTypeFilter
import com.example.rakuten_test_technique.utils.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetail(
    val categories: List<String>,
    val description: String,
    val globalRating: GlobalRating,
    val headline: String,
    val images: List<Image>,
    val newBestPrice: Double,
    val productId: Long,
    val quality: String,
    val salePrice: Double,
    val seller: Seller,
    val sellerComment: String,
    val type: String,
    val usedBestPrice: Int
) : Parcelable {

    // boolean that shows a product is new or used
    val isNew: Boolean
        get() = type == Constants.NEW_UPPER

    // test if product is new and send "New" or "Used" string
    val advType: String
        get() = if (isNew) AdvertTypeFilter.NEW.value else AdvertTypeFilter.USED.value

    // send advertQuality in a more readable string (exemple: LIKE_NEW -> Like new)
    val advQuality: String
        get() =
            when (quality) {
                Constants.NEW_UPPER -> AdvertQualityFilter.NEW.value
                Constants.LIKE_NEW_UPPER -> AdvertQualityFilter.LIKE_NEW.value
                Constants.VERY_GOOD_UPPER -> AdvertQualityFilter.VERY_GOOD.value
                else -> AdvertQualityFilter.UNKNOWN_QUALITY.value
            }
}