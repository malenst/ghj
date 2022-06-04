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
        method()
    }

   // JSONParse.jsonParse()

private fun method() {
    val o = JSONParse(listView, this)
    o.jsonParse()


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


}

}
