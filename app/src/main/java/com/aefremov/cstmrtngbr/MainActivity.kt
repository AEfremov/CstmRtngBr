package com.aefremov.cstmrtngbr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aefremov.rtngbr.SimpleRatingBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rating_bar.setOnRatingChangeListener(object : SimpleRatingBar.RatingChangeListener {
            override fun onRatingChange(rating: Float) {
                Toast.makeText(this@MainActivity, rating.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        rating_bar.setRating(3f)
    }
}
