package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelParse : ViewModel() {

    val listItems: MutableLiveData<Array<String?>> by lazy {
        MutableLiveData<Array<String?>>()
    }

    fun update(array: Array<String?>){
        listItems.value = array
    }
}