package pt.ua.cm.n114715.weatherapp.state

import androidx.lifecycle.ViewModel

// View model class for the app, contains the layout mode and the
// list of portuguese cities, with their respective information
class WeatherViewModel: ViewModel() {
    private var _twoPane = false
    val twoPane: Boolean
        get() = _twoPane
    private var _cities = mutableListOf(
        CityInfo("Viana do Castelo", 41.694569, -8.830160, "viana_do_castelo"),
        CityInfo("Braga", 41.5472695, -8.4464405, "braga"),
        CityInfo("Vila Real", 41.301037, -7.742235, "vila_real"),
        CityInfo("Bragança", 41.806114, -6.756738, "braganca"),
        CityInfo("Porto", 41.1621376, -8.6569729, "porto"),
        CityInfo("Aveiro", 40.6393801, -8.6533827, "aveiro"),
        CityInfo("Viseu", 40.6699073, -7.930557, "viseu"),
        CityInfo("Guarda", 40.5407011, -7.3901101, "guarda"),
        CityInfo("Coimbra", 40.203316, -8.410257, "coimbra"),
        CityInfo("Castelo Branco", 39.8114956, -7.5468265, "castelo_branco"),
        CityInfo("Leiria", 39.7540562, -8.840978, "leiria"),
        CityInfo("Santarém", 39.2278235, -8.7220036, "santarem"),
        CityInfo("Portalegre", 39.2889395, -7.4505053, "portalegre"),
        CityInfo("Lisboa", 38.7251727, -9.1792837, "lisboa"),
        CityInfo("Setúbal", 38.5305821, -8.8872955, "setubal"),
        CityInfo("Évora", 38.569815, -7.9460284, "evora"),
        CityInfo("Beja", 38.0223503, -7.9236463, "beja"),
        CityInfo("Faro", 37.0178, -7.9399308, "faro")
    )
    val cities: MutableList<CityInfo>
        get() = _cities

    fun enableTwoPaneMode(enabled: Boolean) {
        _twoPane = enabled
    }
}

