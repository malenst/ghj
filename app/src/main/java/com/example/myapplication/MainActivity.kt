package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException


class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Rick and Morty"
        listView = findViewById(R.id.persons_list)
        requestQueue = Volley.newRequestQueue(this)
        jsonParse()

    }
    private fun jsonParse() {
        val url = "https://rickandmortyapi.com/api/character"
        val request = JsonObjectRequest(Request.Method.GET, url, null, {
                response ->try {
            val jsonArray = response.getJSONArray("results")
            val listItems = arrayOfNulls<String>(jsonArray.length())
            for (i in 0 until jsonArray.length()) {
                val character = jsonArray.getJSONObject(i)
                val name = character.getString("name")
                val gender = character.getString("gender")
                listItems[i] = "$name\n\nGender: $gender"
            }

            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, listItems
            )
            listView.adapter = adapter






           listView.onItemClickListener =
               OnItemClickListener { parent, itemClicked, position, id ->
                   Toast.makeText(
                       applicationContext, (itemClicked as TextView).text,
                       Toast.LENGTH_SHORT
                   ).show()
                   val intent = Intent(this@MainActivity, InfoActivity::class.java)
                   intent.putExtra("testId", position)

                   startActivity(intent)
               }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }


}
