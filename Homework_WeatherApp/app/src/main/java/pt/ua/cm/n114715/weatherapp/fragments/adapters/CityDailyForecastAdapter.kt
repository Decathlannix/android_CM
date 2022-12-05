package pt.ua.cm.n114715.weatherapp.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pt.ua.cm.n114715.weatherapp.R
import pt.ua.cm.n114715.weatherapp.api_response_model.List
import pt.ua.cm.n114715.weatherapp.utils.NIGHT_SYMBOL

class CityDailyForecastAdapter: RecyclerView.Adapter<CityDailyForecastAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.cityImage)
        val temp: TextView = itemView.findViewById(R.id.cityTemp)
        val feels_like: TextView = itemView.findViewById(R.id.cityFeelsLike)
        val max: TextView = itemView.findViewById(R.id.cityTempMax)
        val min: TextView = itemView.findViewById(R.id.cityTempMin)
        val description: TextView = itemView.findViewById(R.id.cityWeatherDescription)
        val pressure: TextView = itemView.findViewById(R.id.cityPressure)
        val humidity: TextView = itemView.findViewById(R.id.cityHumidity)
        val visibility: TextView = itemView.findViewById(R.id.cityVisibility)
        val wind_speed: TextView = itemView.findViewById(R.id.cityWindSpeed)
        val rain_probability: TextView = itemView.findViewById(R.id.cityRainProb)
        val day_part: TextView = itemView.findViewById(R.id.cityPartDay)
    }

    var forecast = mutableListOf<List>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.city_daily_forecast_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // The Glide external library is used to obtain an image from the internet (in this case,
        // an icon from the OpenWeather site corresponding to the current weather for that day)
        Glide.with(holder.itemView).load(holder.itemView.context.getString(R.string.icon_link, forecast[position].weather[0].icon)).into(holder.image)
        holder.temp.text = holder.itemView.context.getString(R.string.main_temp_unit, forecast[position].main.temp)
        holder.feels_like.text = holder.itemView.context.getString(R.string.sec_temp_unit, forecast[position].main.feelsLike)
        holder.max.text = holder.itemView.context.getString(R.string.sec_temp_unit, forecast[position].main.tempMax)
        holder.min.text = holder.itemView.context.getString(R.string.sec_temp_unit, forecast[position].main.tempMin)
        holder.description.text = forecast[position].weather[0].description.uppercase()
        holder.pressure.text = holder.itemView.context.getString(R.string.pressure_unit, forecast[position].main.pressure)
        holder.humidity.text = holder.itemView.context.getString(R.string.humidity_rain_probability_unit, forecast[position].main.humidity)
        holder.visibility.text = holder.itemView.context.getString(R.string.visibility_unit, forecast[position].visibility)
        holder.wind_speed.text = holder.itemView.context.getString(R.string.wind_speed_unit, forecast[position].wind.speed)
        holder.rain_probability.text = holder.itemView.context.getString(R.string.humidity_rain_probability_unit, (forecast[position].pop * 100).toInt())
        holder.day_part.text = if (forecast[position].sys.pod.equals(NIGHT_SYMBOL)) holder.itemView.context.getString(
            R.string.night) else holder.itemView.context.getString(R.string.day)
    }

    override fun getItemCount() = forecast.size
}