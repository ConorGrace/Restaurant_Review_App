package ie.setu.restaurant_review_app.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantModel(var id: Long = 0,
                          var title: String = "",
                          var description: String = "") : Parcelable