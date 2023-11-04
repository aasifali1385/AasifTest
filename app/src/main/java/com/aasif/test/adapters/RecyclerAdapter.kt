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

class RecyclerAdapter(val restClick: (Restaurant) -> Unit) :
    RecyclerView.Adapter<RecyclerAdapter.RestHolder>() {

    private val restaurant = mutableListOf<Restaurant>()

    fun loadRestaurants(rest: List<Restaurant>, query: String) {
        restaurant.clear()
        restaurant.addAll(rest.filter { it.name.contains(query, true) })
        notifyDataSetChanged()
    }

    inner class RestHolder(view: View) : RecyclerView.ViewHolder(view) {
        val restImage: ImageView = view.findViewById(R.id.restImage)
        val restName: TextView = view.findViewById(R.id.restName)
        val restaurant: CardView = view.findViewById(R.id.restaurant)
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