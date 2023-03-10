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
        restaurant.id = getId()
        restaurants.add(restaurant)
        logAll()
    }

    override fun update(restaurant: RestaurantModel) {
        var foundRestaurant: RestaurantModel? = restaurants.find { p -> p.id == restaurant.id }
        if (foundRestaurant != null) {
            foundRestaurant.title = restaurant.title
            foundRestaurant.description = restaurant.description
            foundRestaurant.type = restaurant.type
            foundRestaurant.rating = restaurant.rating
            foundRestaurant.telephone = restaurant.telephone
            logAll()
        }
    }

    override fun delete(restaurant: RestaurantModel) {
        restaurants.remove(restaurant)
    }
    private fun logAll() {
        restaurants.forEach { i("$it") }
    }
}