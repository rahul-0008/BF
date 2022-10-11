package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val foods = mutableListOf<FoodEntity>()
    private lateinit var foodsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        foodsRecyclerView = findViewById(R.id.items)
        val foodAdapter = FoodAdapter(this, foods)
        foodsRecyclerView.adapter = foodAdapter

        foodsRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            foodsRecyclerView.addItemDecoration(dividerItemDecoration)
        }


        lifecycleScope.launch {
            (application as ArticleApplication).db.foodDAO().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodEntity(
                        entity.id,
                        entity.item,
                        entity.calories
                    )
                }.also { mappedList ->
                    foods.clear()
                    foods.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }


        val button = findViewById<Button>(R.id.add_new_food)
        button.setOnClickListener{
            val intent = Intent(this, Detail::class.java)
            this.startActivity(intent)
            foodAdapter.notifyDataSetChanged()
        }
//        Log.i("op",foods.last().item.toString())
    }
}