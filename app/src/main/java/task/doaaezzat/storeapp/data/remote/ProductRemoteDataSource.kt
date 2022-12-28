package task.doaaezzat.storeapp.data.remote

import retrofit2.Response
import retrofit2.Retrofit
import task.doaaezzat.storeapp.model.Product
import task.doaaezzat.storeapp.network.ProductService
import javax.inject.Inject
import task.doaaezzat.storeapp.model.Result
import task.doaaezzat.storeapp.util.ErrorUtils


class ProductRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {


// get data from server and pass it to result class to know the status
    suspend fun fetchProducts(): Result<List<Product>> {
        val productService = retrofit.create(ProductService::class.java);
        return getResponse(
            request = { productService.getProducts() },
            defaultErrorMessage = "Error fetching product list")
    }



    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
        return try {
            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("No Internet Connection", null)
        }
    }
}