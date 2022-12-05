package pt.ua.cm.n114715.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import pt.ua.cm.n114715.weatherapp.R
import pt.ua.cm.n114715.weatherapp.api.OpenWeatherClient
import pt.ua.cm.n114715.weatherapp.databinding.FragmentDailyForecastBinding
import pt.ua.cm.n114715.weatherapp.fragments.adapters.CityDailyForecastAdapter
import pt.ua.cm.n114715.weatherapp.state.WeatherViewModel
import pt.ua.cm.n114715.weatherapp.utils.FRAGMENT_ARGUMENT_NAME

//Fragment that shows a list of weather reports for the next 5 days for a chosen city, using a recycler view
class DailyForecastFragment : Fragment() {

    private val client = OpenWeatherClient()
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDailyForecastBinding>(inflater, R.layout.fragment_daily_forecast, container, false)
        val adapter = CityDailyForecastAdapter()

        // Depending on the layout mode being used, the argument might
        // come from a navigation action or through a dedicated bundle
        val position: Int = if (viewModel.twoPane) {
            this.requireArguments().getInt(FRAGMENT_ARGUMENT_NAME)
        } else {
            val args = DailyForecastFragmentArgs.fromBundle(requireArguments())
            args.position
        }

        binding.cityDailyForecastList.adapter = adapter

        // Observe the live data for the forecast list of the specific city,
        // to check for changes and update recycler view
        viewModel.cities[position].forecast.observe(viewLifecycleOwner) {
            it.let {
                adapter.forecast.clear()
                adapter.forecast = it
            }
        }

        // Make a call to the OpenWeather API for a forecast report
        client.retrieveWeather(position, viewModel)

        return binding.root
    }
}