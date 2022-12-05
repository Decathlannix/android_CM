package pt.ua.cm.n114715.weatherapp.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pt.ua.cm.n114715.weatherapp.api_response_model.List

class CityInfo(cityName: String, latitude: Double, longitude: Double, drawableID: String){
    private val _name: String
    val name: String
        get() = _name
    private val _lat: Double
    val lat: Double
        get() = _lat
    private val _long: Double
    val long: Double
        get() = _long
    private val _id: String
    val id: String
        get() = _id
    private val _forecast = MutableLiveData<MutableList<List>>()
    val forecast: LiveData<MutableList<List>>
        get() = _forecast

    init {
        _name = cityName
        _lat = latitude
        _long = longitude
        _id = drawableID
    }

    fun setForecast(forecast: MutableList<List>) {
        this._forecast.value?.clear()
        this._forecast.value = forecast
    }
}