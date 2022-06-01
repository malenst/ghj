/*package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class Parse {
    public fun jsonParse() {
        var requestQueue: RequestQueue? = null
        lateinit var listView: ListView
        val url = "https://rickandmortyapi.com/api/character"
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val jsonArray = response.getJSONArray("results")
                val listItems = arrayOfNulls<String>(jsonArray.length())
                for (i in 0 until jsonArray.length()) {
                    val character = jsonArray.getJSONObject(i)
                    val name = character.getString("name")
                    val gender = character.getString("gender")
                    listItems[i] = "$name\n\nGender: $gender"
                }

                val adapter = ArrayAdapter(
                    My_Application.app.main,
                    android.R.layout.simple_list_item_1, listItems
                )
                listView.adapter = adapter
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}*/