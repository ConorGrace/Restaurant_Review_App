package ie.setu.restaurant_review_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import ie.setu.restaurant_review_app.R
import ie.setu.restaurant_review_app.databinding.ActivityRestaurantBinding
import ie.setu.restaurant_review_app.main.MainApp
import ie.setu.restaurant_review_app.models.RestaurantModel
import timber.log.Timber
import timber.log.Timber.i

class RestaurantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestaurantBinding
    var restaurant = RestaurantModel()
    //val restaurant = ArrayList<RestaurantModel>()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        i("Restaurant Activity started...")

        if (intent.hasExtra("restaurant_edit")) {
            restaurant = intent.extras?.getParcelable("restaurant_edit")!!
            binding.restaurantTitle.setText(restaurant.title)
            binding.description.setText(restaurant.description)
        }

        binding.btnAdd.setOnClickListener() {
            restaurant.title = binding.restaurantTitle.text.toString()
            restaurant.description = binding.description.text.toString()
            if (restaurant.title.isNotEmpty()) {
                app.restaurants.create(restaurant.copy())
                i("add Button Pressed: ${restaurant}")
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_restaurant, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}