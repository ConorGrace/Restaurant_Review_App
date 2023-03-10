package ie.setu.restaurant_review_app.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.restaurant_review_app.R
import ie.setu.restaurant_review_app.adapters.RestaurantAdapter
import ie.setu.restaurant_review_app.adapters.RestaurantListener
import ie.setu.restaurant_review_app.databinding.ActivityRestaurantListBinding
import ie.setu.restaurant_review_app.main.MainApp
import ie.setu.restaurant_review_app.models.RestaurantModel

class RestaurantListActivity : AppCompatActivity(), RestaurantListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityRestaurantListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
        binding.recyclerView.setOnLongClickListener { true }

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = RestaurantAdapter(app.restaurants.findAll(),this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, RestaurantActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.restaurants.findAll().size)
            }
        }

    override fun onRestaurantClick(restaurant: RestaurantModel) {
        val launcherIntent = Intent(this, RestaurantActivity::class.java)
        launcherIntent.putExtra("restaurant_edit", restaurant)
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.restaurants.findAll().size)
            }
        }

    override fun onRestaurantLongClick(restaurant: RestaurantModel) {
        AlertDialog.Builder(this)
            .setTitle("Delete Restaurant")
            .setMessage("Are you sure you want to delete ${restaurant.title}?")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(android.R.string.yes) { _, _ ->
                app.restaurants.delete(restaurant)
                (binding.recyclerView.adapter as RestaurantAdapter).removeRestaurant(restaurant)
            }
            .setNegativeButton(android.R.string.no, null)
            .show()
    }
}