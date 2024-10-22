package com.easytuto.realtimeweather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easytuto.realtimeweather.api.NetworkResponses
import com.easytuto.realtimeweather.api.RetrofitInstance
import com.easytuto.realtimeweather.api.WeatherModel
import kotlinx.coroutines.launch
import java.lang.Exception

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherModel
    private val _weatherResult = MutableLiveData<NetworkResponses<WeatherModel>>()
    val weatherResult : LiveData<NetworkResponses<WeatherModel>> = _weatherResult

    fun getData(city : String){
        _weatherResult.value = NetworkResponses.Loading
        viewModelScope.launch {
            try {
                val response  = weatherApi.getWeather(Constant.keyapi,city)
                if (response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value = NetworkResponses.Success(it)
                    }
                }else{
                    _weatherResult.value = NetworkResponses.Error("Failed to Load Data")
                }
            }catch ( e : Exception){
                _weatherResult.value = NetworkResponses.Error("Failed to Load Data")
            }

        }
    }

}