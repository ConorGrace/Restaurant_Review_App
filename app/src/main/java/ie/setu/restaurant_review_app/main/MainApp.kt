package ie.setu.restaurant_review_app.main


import android.app.Application
import ie.setu.restaurant_review_app.models.RestaurantMemStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    //val restaurants = ArrayList<RestaurantModel>()
    val restaurants = RestaurantMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Restaurant started")
//        restaurants.add(RestaurantModel("One", "About one..."))
//        restaurants.add(RestaurantModel("Two", "About two..."))
//        restaurants.add(RestaurantModel("Three", "About three..."))
    }
}