package pt.ua.cm.n114715.weatherapp.fragments.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pt.ua.cm.n114715.weatherapp.MainActivity
import pt.ua.cm.n114715.weatherapp.R
import pt.ua.cm.n114715.weatherapp.fragments.CitiesFragmentDirections
import pt.ua.cm.n114715.weatherapp.state.CityInfo

class CityListAdapter: RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.findViewById(R.id.cityName)
        val cityImage: ImageView = itemView.findViewById(R.id.cityImage)
    }

    var cities = mutableListOf<CityInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var twoPane = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.city_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityName.text = cities[position].name
        holder.cityName.setOnClickListener {

            // If two pane mode is enabled, call the oncityChosen method in the activity,
            // so that it creates the forecast fragment, when a city is chosen
            // Otherwise, if it's disabled, it means the navigation fragment is being used
            // and we need to navigate to the forecast fragment
            if (twoPane) {
                (it.context as MainActivity).onCityChosen(position)
            } else {
                it.findNavController().navigate(CitiesFragmentDirections.actionCitiesFragmentToDailyForecastFragment(position))
            }
        }

        // Get the drawable image corresponding to the city
        holder.cityImage.setImageDrawable(ContextCompat.getDrawable(holder.cityImage.context, holder.cityImage.context.resources.getIdentifier(cities[position].id, "drawable", holder.cityImage.context.packageName)))
    }

    override fun getItemCount() = cities.size
}