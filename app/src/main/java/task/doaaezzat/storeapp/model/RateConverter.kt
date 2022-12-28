package task.doaaezzat.storeapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class RateConverter {

    @TypeConverter
    fun fromRate(rate: Rate): String {
        val gson = Gson()
        return gson.toJson(rate)
    }

    @TypeConverter
    fun fromString(string: String):Rate{
        val rateType: Type = object : TypeToken<Rate>() {}.type
        return Gson().fromJson(string, rateType)
    }

}