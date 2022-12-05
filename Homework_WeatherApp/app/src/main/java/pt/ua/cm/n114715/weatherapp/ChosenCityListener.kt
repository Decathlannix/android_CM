package pt.ua.cm.n114715.weatherapp

// Interface used by the activity to implement the method to create and
// send data to a forecast fragment, and called by the main fragment, CitiesFragment
interface ChosenCityListener {
    fun onCityChosen (position: Int)
}