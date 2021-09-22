package com.example.rakuten_test_technique.utils

/**
 * Enum class to differentiate advert type.
 */
enum class AdvertTypeFilter(val value: String) {
    NEW(Constants.NEW),
    USED(Constants.USED)
}

/**
 * Enum class to differentiate advert quality.
 */
enum class AdvertQualityFilter(val value: String) {
    NEW(Constants.NEW),
    LIKE_NEW(Constants.LIKE_NEW),
    VERY_GOOD(Constants.VERY_GOOD),
    UNKNOWN_QUALITY(Constants.UNKNOWN_QUALITY)
}