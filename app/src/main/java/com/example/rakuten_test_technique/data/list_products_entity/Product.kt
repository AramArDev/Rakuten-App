package com.example.rakuten_test_technique.data.list_products_entity

import android.os.Parcelable
import com.example.rakuten_test_technique.utils.AdvertQualityFilter
import com.example.rakuten_test_technique.utils.AdvertTypeFilter
import com.example.rakuten_test_technique.utils.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val buybox: Buybox,
    val categoryRef: Int,
    val headline: String,
    val id: Long,
    val imagesUrls: List<String>,
    val nbReviews: Int,
    val newBestPrice: Double,
    val reviewsAverageNote: Float,
    val usedBestPrice: Double
) : Parcelable {

    // boolean that shows a product is new or used
    val isNew: Boolean
        get() = buybox.advertType == Constants.NEW_UPPER

    // test if product is new and send "New" or "Used" string
    val advType: String
        get() = if (isNew) AdvertTypeFilter.NEW.value else AdvertTypeFilter.USED.value

    // send advertQuality in a more readable string (exemple: LIKE_NEW -> Like new)
    val advQuality: String
        get() =
            when (buybox.advertQuality) {
                Constants.NEW_UPPER -> AdvertQualityFilter.NEW.value
                Constants.LIKE_NEW_UPPER -> AdvertQualityFilter.LIKE_NEW.value
                Constants.VERY_GOOD_UPPER -> AdvertQualityFilter.VERY_GOOD.value
                else -> AdvertQualityFilter.UNKNOWN_QUALITY.value
            }
}