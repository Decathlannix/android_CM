package pt.ua.cm.n114715.homework_dialerapp

import androidx.lifecycle.ViewModel

// ViewModel class to be shared between "MainFragment" and "DetailsFragment" fragments
class SharedViewModel : ViewModel() {
    var speedDialInfos = listOf(ConactInfo(), ConactInfo(), ConactInfo())
    var currentSpeedDial = -1

    fun isContactEmpty(n: Int): Boolean {
        return (speedDialInfos[n].phoneLabel == "-" && speedDialInfos[n].phoneNumber == -1)
    }
}

// Information for each speed dial contact (name and phone number)
class ConactInfo {
    var phoneLabel : String = "-"
    var phoneNumber : Int = -1
}