package com.aasif.test.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aasif.test.MyApplication
import com.aasif.test.R
import com.aasif.test.databinding.ActivityHomeBinding
import com.aasif.test.data.FoodsCate
import com.aasif.test.adapters.RecyclerAdapter
import com.aasif.test.viewmodels.MainViewModel
import com.aasif.test.viewmodels.MainViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var bind: ActivityHomeBinding
    private lateinit var foods: List<FoodsCate>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val repository = (application as MyApplication).repository

        val viewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
//        val viewModel: MainViewModel by viewModels { MainViewModelFactory(repository) }

        viewModel.getCategories()

        viewModel.foods.observe(this) {
            bind.progressBar.isVisible = false
            loadFoods(it)
            foods = it
        }

        bind.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                loadFoods(foods, p0.toString())
                return true
            }
        })


    }


    private fun loadFoods(foodsCate: List<FoodsCate>, query: String = "") {
        bind.linear.removeAllViews()

        foodsCate.forEach {
            val view = layoutInflater.inflate(R.layout.item_foodscate, bind.root, false)

            view.findViewById<TextView>(R.id.cateName).text = it.name
            val recycler = view.findViewById<RecyclerView>(R.id.recycler)

            val recyclerAdapter = RecyclerAdapter { restaurant ->
                val intent = Intent(this, RestaurantActivity::class.java)
                intent.putExtra("restaurant", restaurant)
                startActivity(intent)
            }

            recycler.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recycler.adapter = recyclerAdapter

            recyclerAdapter.loadRestaurants(it.restaurant, query)

            bind.linear.addView(view)
        }
    }


}