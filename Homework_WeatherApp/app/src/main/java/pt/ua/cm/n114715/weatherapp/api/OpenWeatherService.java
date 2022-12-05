package pt.ua.cm.n114715.weatherapp.api;

import pt.ua.cm.n114715.weatherapp.api_response_model.WeatherInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Example of API call: https://api.openweathermap.org/data/2.5/forecast?lat=41.6946&lon=-8.8302&units=metric&cnt=1&appid=ac37d3ade2c411ae425ab69000220364
// Example of JSON response:
//                            {
//                                "cod":"200",
//                                "message":0,
//                                "cnt":1,
//                                "list":[
//                                    {
//                                    "dt":1670198400,
//                                    "main":{
//                                        "temp":9.63,
//                                        "feels_like":7.6,
//                                        "temp_min":9.63,
//                                        "temp_max":9.63,
//                                        "pressure":1009,
//                                        "sea_level":1009,
//                                        "grnd_level":1008,
//                                        "humidity":84,
//                                        "temp_kf":0
//                                    },
//                                    "weather":[
//                                        {
//                                        "id":802,
//                                        "main":"Clouds",
//                                        "description":"scattered clouds",
//                                        "icon":"03n"
//                                        }
//                                    ],
//                                    "clouds":{
//                                        "all":25
//                                    },
//                                    "wind":{
//                                        "speed":3.88,
//                                        "deg":94,
//                                        "gust":4.89
//                                    },
//                                    "visibility":10000,
//                                    "pop":0.02,
//                                    "sys":{
//                                        "pod":"n"
//                                    },
//                                    "dt_txt":"2022-12-05 00:00:00"
//                                    }
//                                ],
//                                "city":{
//                                    "id":2732773,
//                                    "name":"Viana do Castelo",
//                                    "coord":{
//                                        "lat":41.6946,
//                                        "lon":-8.8302
//                                    },
//                                    "country":"PT",
//                                    "population":15555,
//                                    "timezone":0,
//                                    "sunrise":1670139956,
//                                    "sunset":1670173523
//                                }
//                            }

// Java interface that represents the parameters that are sent in the API queue
public interface OpenWeatherService {
    @GET("forecast")
    Call<WeatherInfo> getWeather(
            @Query("lat") String latitude,
            @Query("lon") String longitude,
            @Query("units") String units,
            @Query("cnt") String timestamps,
            @Query("appid") String apikey
    );
}
