package pt.ua.cm.n114715.homework_dialerapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import pt.ua.cm.n114715.homework_dialerapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailsBinding>(inflater, R.layout.fragment_details, container, false)
        if (!model.isContactEmpty(model.currentSpeedDial)){
            binding.editTextName.setText(model.speedDialInfos[model.currentSpeedDial].phoneLabel)
            binding.editTextPhone.setText(model.speedDialInfos[model.currentSpeedDial].phoneNumber.toString())
        }
        binding.saveChangeButton.setOnClickListener { view : View ->
            if (binding.editTextName.text.isNotEmpty() && binding.editTextPhone.text.isNotEmpty()) {
                model.speedDialInfos[model.currentSpeedDial].phoneLabel = binding.editTextName.text.toString()
                model.speedDialInfos[model.currentSpeedDial].phoneNumber = binding.editTextPhone.text.toString().toInt()
                view.findNavController().navigate(R.id.action_detailsFragment_to_mainFragment)
            }
        }
        return binding.root
    }
}