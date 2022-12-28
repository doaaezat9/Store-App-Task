package task.doaaezzat.storeapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rate(
    val rate : Double,
    val count : Long
): Parcelable
