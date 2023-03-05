package ie.setu.restaurant_review_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.setu.restaurant_review_app.databinding.CardRestaurantBinding
import ie.setu.restaurant_review_app.models.RestaurantModel
import ie.setu.restaurant_review_app.activities.RestaurantListActivity

interface RestaurantListener {
    fun onRestaurantClick(restaurant: RestaurantModel)
    fun onRestaurantLongClick(restaurant: RestaurantModel)
}

class RestaurantAdapter constructor(private var restaurants: List<RestaurantModel>,
                                    private val listener: RestaurantListener) :
    RecyclerView.Adapter<RestaurantAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRestaurantBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val restaurant = restaurants[holder.adapterPosition]
        holder.bind(restaurant, listener)
    }

    override fun getItemCount(): Int = restaurants.size

    class MainHolder(private val binding : CardRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: RestaurantModel, listener: RestaurantListener) {
            binding.restaurantTitle.text = restaurant.title
            binding.description.text = restaurant.description
            binding.description.text = restaurant.type
            binding.description.text = restaurant.rating.toString()
            binding.description.text = restaurant.telephone
            binding.root.setOnClickListener { listener.onRestaurantClick(restaurant) }
            binding.root.setOnLongClickListener {
                listener.onRestaurantLongClick(restaurant)
                true
            }
        }
    }

    fun removeRestaurant(restaurant: RestaurantModel) {
        restaurants = restaurants.filter { it.id != restaurant.id }
        notifyDataSetChanged()
    }
}
