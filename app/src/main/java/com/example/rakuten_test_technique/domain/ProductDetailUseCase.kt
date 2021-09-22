package com.example.rakuten_test_technique.domain

import com.example.rakuten_test_technique.data.APIs
import com.example.rakuten_test_technique.data.product_detail_entity.ProductDetail
import javax.inject.Inject

/**
 * The [ProductDetailUseCase] class that offers the services for ProductDetail.
 */
class ProductDetailUseCase @Inject constructor(
    private val apis: APIs
) {

    suspend operator fun invoke(productId: Long): ProductDetail {
        return apis.getDetail(productId)
    }

}
