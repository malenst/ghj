package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity(), Listener {

    private lateinit var listView: ListView
    var requestQueue: RequestQueue? = null
    lateinit var o: JSONParse
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

        o = JSONParse(requestQueue)
        o.addListener(this)
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

    override fun parsingFinished() {
        val listItems = o.array
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, listItems
        )
        listView.adapter = adapter
    }
}