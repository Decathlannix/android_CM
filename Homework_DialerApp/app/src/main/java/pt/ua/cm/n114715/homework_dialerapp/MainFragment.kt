package pt.ua.cm.n114715.homework_dialerapp

import android.Manifest.permission.CALL_PHONE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import pt.ua.cm.n114715.homework_dialerapp.databinding.FragmentMainBinding

class MainFragment : Fragment(), View.OnClickListener, View.OnLongClickListener {

    private var phoneNumber: String = ""
    private val model: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        // Setup click listeners for all buttons
        binding.number0Button.setOnClickListener(this)
        binding.number1Button.setOnClickListener(this)
        binding.number2Button.setOnClickListener(this)
        binding.number3Button.setOnClickListener(this)
        binding.number4Button.setOnClickListener(this)
        binding.number5Button.setOnClickListener(this)
        binding.number6Button.setOnClickListener(this)
        binding.number7Button.setOnClickListener(this)
        binding.number8Button.setOnClickListener(this)
        binding.number9Button.setOnClickListener(this)
        binding.clearNumberButton.setOnClickListener(this)
        binding.callNumberButton.setOnClickListener(this)
        binding.speedDial1Button.setOnClickListener(this)
        binding.speedDial2Button.setOnClickListener(this)
        binding.speedDial3Button.setOnClickListener(this)
        // Setup long click listeners for quick dial buttons only
        binding.speedDial1Button.setOnLongClickListener(this)
        binding.speedDial2Button.setOnLongClickListener(this)
        binding.speedDial3Button.setOnLongClickListener(this)
        // Update quick dial buttons' labels to either empty ("-") or a contact name
        binding.speedDial1Button.text = model.speedDialInfos[0].phoneLabel
        binding.speedDial2Button.text = model.speedDialInfos[1].phoneLabel
        binding.speedDial3Button.text = model.speedDialInfos[2].phoneLabel

        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.number0Button -> {
                phoneNumber = phoneNumber.plus("0")
                binding.phoneNumberVisor.text = phoneNumber
            }
            R.id.number1Button -> {
                phoneNumber = phoneNumber.plus("1")
                binding.phoneNumberVisor.text = phoneNumber
            }
            R.id.number2Button -> {
                phoneNumber = phoneNumber.plus("2")
                binding.phoneNumberVisor.text = phoneNumber
            }
            R.id.number3Button -> {
                phoneNumber = phoneNumber.plus("3")
                binding.phoneNumberVisor.text = phoneNumber
            }
            R.id.number4Button -> {
                phoneNumber = phoneNumber.plus("4")
                binding.phoneNumberVisor.text = phoneNumber
            }
            R.id.number5Button -> {
                phoneNumber = phoneNumber.plus("5")
                binding.phoneNumberVisor.text = phoneNumber
            }
            R.id.number6Button -> {
                phoneNumber = phoneNumber.plus("6")
                binding.phoneNumberVisor.text = phoneNumber
            }
            R.id.number7Button -> {
                phoneNumber = phoneNumber.plus("7")
                binding.phoneNumberVisor.text = phoneNumber
            }
            R.id.number8Button -> {
                phoneNumber = phoneNumber.plus("8")
                binding.phoneNumberVisor.text = phoneNumber
            }
            R.id.number9Button -> {
                phoneNumber = phoneNumber.plus("9")
                binding.phoneNumberVisor.text = phoneNumber
            }
            R.id.clearNumberButton -> {
                phoneNumber = phoneNumber.dropLast(1)
                binding.phoneNumberVisor.text = phoneNumber
            }
            R.id.callNumberButton -> {
                callPhoneNumber(phoneNumber)
            }
            R.id.speedDial1Button -> {
                if (!model.isContactEmpty(0)){
                    callPhoneNumber(model.speedDialInfos[0].phoneNumber.toString())
                }
            }
            R.id.speedDial2Button -> {
                if (!model.isContactEmpty(1)){
                    callPhoneNumber(model.speedDialInfos[1].phoneNumber.toString())
                }
            }
            R.id.speedDial3Button -> {
                if (!model.isContactEmpty(2)){
                    callPhoneNumber(model.speedDialInfos[2].phoneNumber.toString())
                }
            }
        }
    }

    override fun onLongClick(v: View?): Boolean {
        when(v?.id) {
            R.id.speedDial1Button -> model.currentSpeedDial = 0
            R.id.speedDial2Button -> model.currentSpeedDial = 1
            R.id.speedDial3Button -> model.currentSpeedDial = 2
            else -> return false
        }
        v.findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
        return true
    }

    fun callPhoneNumber(phoneNumber: String) {
        if (ContextCompat.checkSelfPermission(requireContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            if (intent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(intent)
            }
        } else {
            ActivityCompat.requestPermissions(activity as Activity,  arrayOf(CALL_PHONE), 1)
        }

    }
}