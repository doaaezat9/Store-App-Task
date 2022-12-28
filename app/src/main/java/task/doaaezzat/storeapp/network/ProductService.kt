package task.doaaezzat.storeapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import task.doaaezzat.storeapp.model.Product


interface ProductService {
    @GET("/products")
    suspend fun getProducts() : Response<List<Product>>
}