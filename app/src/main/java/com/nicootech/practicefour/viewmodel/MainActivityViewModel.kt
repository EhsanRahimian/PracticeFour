package com.nicootech.practicefour.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicootech.practicefour.models.RecyclerList
import com.nicootech.practicefour.network.RetroInstance
import com.nicootech.practicefour.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    lateinit var recyclerListLiveData : MutableLiveData<RecyclerList>
    init {
        recyclerListLiveData = MutableLiveData()
    }

    fun getRecyclerListObserver(): MutableLiveData<RecyclerList> {
        return  recyclerListLiveData
    }
    //responsible to call api for github search
    fun makeApiCall(){

        viewModelScope.launch(Dispatchers.IO){
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi("ny")
            recyclerListLiveData.postValue(response)
        }
    }
}