package task.doaaezzat.storeapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import task.doaaezzat.storeapp.model.Result
import task.doaaezzat.storeapp.data.ProductRepository
import task.doaaezzat.storeapp.model.Product

import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    private val _productList = MutableLiveData<Result<List<Product>>>()
    val productList = _productList

    private val _navigateToProductDetails = MutableLiveData<Product?>()
    val navigateToProductDetails
        get() = _navigateToProductDetails

    init {
        fetchProducts()
    }

    fun onProductClicked(product: Product) {
        _navigateToProductDetails.value = product
    }

    fun onProductClickedNavigated() {
        _navigateToProductDetails.value = null
    }
// fetching data by using view model scope
    private fun fetchProducts() {
        viewModelScope.launch {
            productRepository.fetchingData().collect {
                _productList.value = it
            }
        }
    }
}