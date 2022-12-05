package pt.ua.cm.n114715.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import pt.ua.cm.n114715.weatherapp.R
import pt.ua.cm.n114715.weatherapp.databinding.FragmentCitiesBinding
import pt.ua.cm.n114715.weatherapp.fragments.adapters.CityListAdapter
import pt.ua.cm.n114715.weatherapp.state.WeatherViewModel

//Main fragment, that shows a recycler view with a list of portuguese cities, defined in the viewmodel
class CitiesFragment : Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCitiesBinding>(inflater, R.layout.fragment_cities, container, false)
        val adapter = CityListAdapter()
        binding.citiesList.adapter = adapter
        adapter.cities = viewModel.cities
        adapter.twoPane = viewModel.twoPane

        return binding.root
    }
}