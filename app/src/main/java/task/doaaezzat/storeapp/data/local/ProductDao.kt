package task.doaaezzat.storeapp.data.local

import androidx.room.*
import task.doaaezzat.storeapp.model.Product



@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAllProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Product>)


    @Query("DELETE  FROM Product")
    suspend fun deleteAllProducts()
}