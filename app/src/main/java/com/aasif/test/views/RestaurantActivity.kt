package com.aasif.test.views

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.aasif.test.databinding.ActivityRestaurentBinding
import com.aasif.test.data.Restaurant


class RestaurantActivity : AppCompatActivity() {

    private lateinit var bind: ActivityRestaurentBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityRestaurentBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.back.setOnClickListener { finish() }

        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("restaurant", Restaurant::class.java)
        } else {
            intent.getParcelableExtra("restaurant")
        }

        bind.apply {
            image.load(data!!.image_url)
            name.text =
                "Name : ${data.name}\nPhone No. : ${data.phone_number}\nAddress : ${data.address}"
        }


    }
}