package com.aasif.test.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aasif.test.R
import com.aasif.test.data.Restaurant

class RestaurantAdapter(val restClick: (Restaurant) -> Unit) :
    RecyclerView.Adapter<RestaurantAdapter.RestHolder>() {

    private val restaurant = mutableListOf<Restaurant>()

    fun loadRestaurants(rest: List<Restaurant>, query: String) {
        restaurant.clear()
        restaurant.addAll(rest.filter { it.name.contains(query, true) })
        notifyDataSetChanged()
    }

    inner class RestHolder(view: View) : RecyclerView.ViewHolder(view) {
        val restImage = view.findViewById<ImageView>(R.id.restImage)
        val restName = view.findViewById<TextView>(R.id.restName)
        val restaurant = view.findViewById<CardView>(R.id.restaurant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        return RestHolder(view)
    }

    override fun onBindViewHolder(holder: RestHolder, position: Int) {
        val data = restaurant[position]
        holder.restImage.load(data.image_url)
        holder.restName.text = data.name
        holder.restaurant.setOnClickListener { restClick(data) }
    }

    override fun getItemCount() = restaurant.size

}