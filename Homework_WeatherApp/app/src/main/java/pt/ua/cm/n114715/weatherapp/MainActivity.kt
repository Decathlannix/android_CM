package pt.ua.cm.n114715.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import pt.ua.cm.n114715.weatherapp.databinding.ActivityMainBinding
import pt.ua.cm.n114715.weatherapp.fragments.DailyForecastFragment
import pt.ua.cm.n114715.weatherapp.state.WeatherViewModel
import pt.ua.cm.n114715.weatherapp.utils.FRAGMENT_ARGUMENT_NAME
import pt.ua.cm.n114715.weatherapp.utils.MINIMUM_WIDTH

class MainActivity : AppCompatActivity(), ChosenCityListener {

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // If the device's minimum width is 600 or more (generally a tablet) two pane mode is enabled, else it isn't
        // Enabled -> both fragments in the same screen
        // Disabled -> one per screen
        if (resources.configuration.smallestScreenWidthDp >= MINIMUM_WIDTH) {
            viewModel.enableTwoPaneMode(true)
        } else { // The navigation host fragment only exists with two pane mode disabled
            // Get the navigation controller object
            val navController = this.findNavController(R.id.myNavHostFragment)
            // Associate the controller with the app bar
            NavigationUI.setupActionBarWithNavController(this, navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    // Create a fragment to show the weather details on the dedicated container
    // in the layout (forecastFragmentContainer) and send the chosen city's index to it
    override fun onCityChosen(position: Int) {
        val cityForecastFragment = DailyForecastFragment()
        val bundle = Bundle()
        bundle.putInt(FRAGMENT_ARGUMENT_NAME, position)
        cityForecastFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.forecastFragmentContainer, cityForecastFragment)
            .commit()
    }
}