package pt.ua.cm.n114715.weatherapp.api;

import static pt.ua.cm.n114715.weatherapp.utils.UtilsKt.API_KEY;
import static pt.ua.cm.n114715.weatherapp.utils.UtilsKt.N_DAYS;
import static pt.ua.cm.n114715.weatherapp.utils.UtilsKt.N_TIMESTAMPS;
import static pt.ua.cm.n114715.weatherapp.utils.UtilsKt.TIMESTAMP_JUMP;
import static pt.ua.cm.n114715.weatherapp.utils.UtilsKt.UNITS;

import java.util.ArrayList;
import java.util.List;

import pt.ua.cm.n114715.weatherapp.state.WeatherViewModel;
import pt.ua.cm.n114715.weatherapp.api_response_model.WeatherInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

// Java class that represents the client that does the
// API request and receives the response asynchronously
public class OpenWeatherClient {
    private final OpenWeatherService service;

    public OpenWeatherClient() {
        Retrofit retrofitInstance = RetrofitInstance.getRetrofitInstance();
        this.service = retrofitInstance.create(OpenWeatherService.class);
    }

    public void retrieveWeather(int position, WeatherViewModel viewModel) {
        Call<WeatherInfo> call = service.getWeather(String.valueOf(viewModel.getCities().get(position).getLat()), String.valueOf(viewModel.getCities().get(position).getLong()), UNITS, N_TIMESTAMPS, API_KEY);
        call.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                WeatherInfo info = response.body();
                List<pt.ua.cm.n114715.weatherapp.api_response_model.List> forecast = new ArrayList<>();
                int i = 0;
                if (info != null) {
                    while (i < N_DAYS) {

                        // The API returns 40 forecast entries (8 per day, for 5 days). For the
                        // purpose of the app and to save time, only 1 per day is needed. So
                        // an entry is saved every 8 occurrences.
                        forecast.add(info.getList().get(i * TIMESTAMP_JUMP));
                        i++;
                    }

                    // Update the live data
                    viewModel.getCities().get(position).setForecast(forecast);
                }
            }
            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
