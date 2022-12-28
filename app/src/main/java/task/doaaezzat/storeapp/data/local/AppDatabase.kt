package task.doaaezzat.storeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import task.doaaezzat.storeapp.model.Product
import task.doaaezzat.storeapp.model.RateConverter


// using room data base to caching data when no internet connection state
@Database(entities = [Product::class], version = 2 ,  exportSchema = false)
@TypeConverters(RateConverter::class)
abstract class AppDatabase : RoomDatabase()  {
    abstract fun productDao(): ProductDao
}