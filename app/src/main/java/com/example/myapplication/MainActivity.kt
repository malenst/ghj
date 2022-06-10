package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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


    private fun method() {

        val observer = Observer<Array<String?>> { array ->
            val listItems = array
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, listItems
            )
            listView.adapter = adapter
        }

        val model: ViewModelParse by viewModels()
        model.listItems.observe(this, observer)
        model.init(requestQueue)

        o = JSONParse(requestQueue, model)

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
        TODO("Not yet implemented")
    }

}