package com.example.bitfit

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_add_detail)


        var item_t = findViewById<EditText>(R.id.food_name)
        var amt = findViewById<EditText>(R.id.claorie_count)

        var t1 = ""
        var t2  = ""

        val but = findViewById<Button>(R.id.record)

        but.setOnClickListener {
             t1 = item_t.text.toString()
             t2 = amt.text.toString()
            lifecycleScope.launch(IO) {
                (application as ArticleApplication).db.foodDAO().insert(
                    FoodEntity(
                        item = t1,
                        calories = t2
                    )
                )
            }
            finish()
        }


    }

}