package com.mapper.videoreel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mapper.videoreel.model.reels
import com.mapper.videoreel.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ListViewModel : ViewModel() {

    private val myRepository = Repository()

    private val _myResponse : MutableLiveData<Response<reels>> = MutableLiveData()
    val myResponse : LiveData<Response<reels>>
        get() = _myResponse

    fun getCall(){
        viewModelScope.launch {
            //getting responce
            val response = myRepository.getCall()
            //mathod to take a responce
            _myResponse.value = response
        }
    }

}