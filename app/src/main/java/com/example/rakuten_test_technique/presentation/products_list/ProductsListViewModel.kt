package com.example.rakuten_test_technique.presentation.products_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rakuten_test_technique.data.list_products_entity.Product
import com.example.rakuten_test_technique.domain.ProductsListUseCase
import com.example.rakuten_test_technique.presentation.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(private val productsListUseCase: ProductsListUseCase) :
    ViewModel() {

    // indicates which product was clicked
    private val _productClicked = MutableLiveData<Product?>()
    val productClicked: LiveData<Product?>
        get() = _productClicked

    // contains the list of products
    private val _productsList = MutableLiveData<List<Product>>()
    val productsList: LiveData<List<Product>>
        get() = _productsList

    /**
     * Products list recuperate from [ProductsListUseCase] with [keyword].
     */
    /*suspend*/ fun getListResponse(keyword: String) = flow {
        Timber.i("Recuperate ProductsList with the keyword = $keyword")
        // show progress bar
        emit(State.LoadingState)
        try {
            // recovery ProductsList
            val searchProducts = productsListUseCase.invoke(keyword)
            _productsList.value = searchProducts.products
            Timber.i("ProductsList with keyword = $keyword was found")
            // hide ProgressBarre and show the ProductsList screen
            emit(State.DataState)
        } catch (e: Exception) {
            e.printStackTrace()
            Timber.i("Failed to retrieve ProductsList with keyword = $keyword")
            // show the error message with Snackbar
            emit(State.ErrorState)
        }
    }

    /**
     * The [product] has been clicked.
     */
    fun onProductClicked(product: Product) {
        _productClicked.value = product
    }

    /**
     * productClicked reset.
     */
    fun doneProductClicked() {
        _productClicked.value = null
    }
}