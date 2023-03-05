package ie.setu.restaurant_review_app.models

interface RestaurantStore {
    fun findAll(): List<RestaurantModel>
    fun create(restaurant: RestaurantModel)
    fun update(restaurant: RestaurantModel)
}