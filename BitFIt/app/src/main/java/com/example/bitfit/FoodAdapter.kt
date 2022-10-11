package com.example.bitfit

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private val context: Context, private val foods: List<FoodEntity>) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val food = foods[position]
        holder.itm.text = food.item.toString()
        holder.amt.text = food.calories.toString()

        Log.i("op",food.item.toString()+"er")

    }
    override fun getItemCount() = foods.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val itm: TextView
            val amt: TextView
            init{
                itm = itemView.findViewById<TextView>(R.id.item)
                amt = itemView.findViewById<TextView>(R.id.amount)
            }

        }


}