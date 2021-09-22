package com.example.rakuten_test_technique.presentation.product_detail

import androidx.lifecycle.*
import com.example.rakuten_test_technique.data.product_detail_entity.ProductDetail
import com.example.rakuten_test_technique.domain.ProductDetailUseCase
import com.example.rakuten_test_technique.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel [ProductDetailViewModel] of the fragment [ProductDetailFragment].
 */
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productDetailUseCase: ProductDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // product identifier retrieved from navArgs of ProductDetailFragment
    private val productId =
        savedStateHandle.get<Long>(Constants.PRODUCT_ID) ?: Constants.ERROR_PRODUCT_ID

    // product detail recuperated
    private val _productDetail = MutableLiveData<ProductDetail>()
    val productDetail: LiveData<ProductDetail>
        get() = _productDetail

    // represent number of slider dots
    val nbDots = Transformations.map(productDetail) {
        it.images.size
    }

    // represent the state of the fragment ProductDetailFragment
    // to display either ProgressBar or the xml page
    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean>
        get() = _state

    // indicates if a problem has occurred
    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    init {
        // check if productId is retrieved from navArgs
        if (productId == Constants.ERROR_PRODUCT_ID) {
            Timber.e("Wrong productId : $productId")
            // show progress bar and error message with Toast
            _state.value = true
            _error.value = true
        } else {
            getDetail()
        }
    }

    /**
     * Product detail recuperate from [ProductDetailUseCase].
     */
    private fun getDetail() {
        // coroutine launch
        viewModelScope.launch {
            Timber.i("Recovery of ProductDetail with productId = $productId")
            // show progress bar
            _state.value = true
            try {
                // recovery ProductDetail
                _productDetail.value = productDetailUseCase.invoke(productId)
                Timber.i("ProductDetail with productId = $productId was found")
                // hide ProgressBarre and show the ProductDetail screen
                _state.value = false
            } catch (e: Exception) {
                e.printStackTrace()
                Timber.i("Failed to retrieve ProductDetail with productId = $productId")
                // show progress bar and error message with Toast
                _state.value = true
                _error.value = true
            }
        }
    }

    fun desactiveState() {
        _state.value = false
    }

}