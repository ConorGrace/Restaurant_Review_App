package ie.setu.restaurant_review_app.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ie.setu.restaurant_review_app.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        val addButton = findViewById<Button>(R.id.addButton)
    }
}