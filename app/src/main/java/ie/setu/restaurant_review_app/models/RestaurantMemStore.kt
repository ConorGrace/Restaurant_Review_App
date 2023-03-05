package ie.setu.restaurant_review_app.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class RestaurantMemStore : RestaurantStore {

    val restaurants = ArrayList<RestaurantModel>()

    override fun findAll(): List<RestaurantModel> {
        return restaurants
    }

    override fun create(restaurant: RestaurantModel) {
        restaurants.id = getId()
        restaurants.add(restaurant)
        logAll()
    }

    override fun update(restaurant: RestaurantModel) {
        var foundRestaurant: RestaurantModel? = restaurants.find { r -> r.id == restaurant.id }
        if (foundRestaurant != null) {
            foundRestaurant.title = restaurant.title
            foundRestaurant.description = restaurant.description
            logAll()
        }
    }

    private fun logAll() {
        restaurants.forEach { i("$it") }
    }
}