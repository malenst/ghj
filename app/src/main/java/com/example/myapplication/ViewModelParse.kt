package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue

class ViewModelParse : ViewModel() {

    val listItems: MutableLiveData<Array<String?>> by lazy {
        MutableLiveData<Array<String?>>()
    }

    fun init(requestQueue: RequestQueue?) {
        val service = JSONParse(requestQueue, this)
        service.jsonParse()
    }

    fun update(array: Array<String?>){
        listItems.value = array
    }
}