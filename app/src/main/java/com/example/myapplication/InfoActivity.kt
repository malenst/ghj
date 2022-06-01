package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONException

class InfoActivity : Activity() {
    private var requestQueue: RequestQueue? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_info)
        requestQueue = Volley.newRequestQueue(this)
        var test: TextView = findViewById(R.id.test)
        val status: TextView = findViewById(R.id.status)
        val species: TextView = findViewById(R.id.species)
        val gender: TextView = findViewById(R.id.gender)
        val name: TextView = findViewById(R.id.name)
        val imag: ImageView = findViewById(R.id.imageView)

        var testText = intent.getIntExtra("testId", 0)
        test.text = testText.toString()



        val url = "https://rickandmortyapi.com/api/character"
        val request = JsonObjectRequest(Request.Method.GET, url, null, {
                response ->try {
            val jsonArray = response.getJSONArray("results")
            val listItems = arrayOfNulls<String>(jsonArray.length())

            val character = jsonArray.getJSONObject(testText)
            val img = character.getString("image")
            name.text = character.getString("name")
            status.text = character.getString("status")
            species.text = character.getString("species")
            gender.text = character.getString("gender")
            Picasso.get().load(img).into(imag)

          /*  for (i in 0 until jsonArray.length()) {
                val character = jsonArray.getJSONObject(i)
                val name = character.getString("name")
                val gender = character.getString("gender")
                listItems[i] = "$name\n\nGender: $gender"
            }*/

            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, listItems
            )


        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}

