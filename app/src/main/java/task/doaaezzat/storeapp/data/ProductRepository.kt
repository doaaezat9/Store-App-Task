package task.doaaezzat.storeapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response
import task.doaaezzat.storeapp.data.local.ProductDao
import task.doaaezzat.storeapp.data.remote.ProductRemoteDataSource
import task.doaaezzat.storeapp.model.Product
import javax.inject.Inject
import task.doaaezzat.storeapp.model.Result

class ProductRepository @Inject constructor(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productDao: ProductDao) {

    // at first fetching data from a server ,
    // if successful remove all data from room data base , and add the new data
    // if fail get cached data


    suspend fun fetchingData(): Flow<Result<List<Product>>> {
        return flow {
            emit(Result.loading())
            var result = productRemoteDataSource.fetchProducts()
            //Cache to database if response is successful
            if (result.status == Result.Status.SUCCESS) {
                clearAllProducts()
                result.data?.let { it ->
                    insertAllProducts(it)
                }
            }
            else {
                result = getAllCachedProducts()
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun clearAllProducts(){
        productDao.deleteAllProducts()
    }
    private suspend fun insertAllProducts(products :List<Product>){
        productDao.insertAll(products)
    }

    private suspend fun getAllCachedProducts():Result<List<Product>>{

       return productDao.getAllProducts().let {
           Result.success(it)
        }
    }

}