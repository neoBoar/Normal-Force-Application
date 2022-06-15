package com.example.myscienceapp

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.myscienceapp.databinding.FragmentFourthBlankBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FourthBlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FourthBlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : FragmentFourthBlankBinding
    private lateinit var navController: NavController
    var angle = 0.0 //holds the users input data
    var grav = 0.0 //holds the users input data
    var mass = 0.0 //holds the users input data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding.backButton2.setOnClickListener {
            navController.navigate(R.id.action_fourthBlankFragment_to_thirdBlankFragment) //Navigate from 4th page back to 3rd page on button click
        }
        /*
        Condition to check the user has the right answer
        Changes colours of answer text and prompt text to red or green depending on status of answer
        */
        binding.submitButton.setOnClickListener {
            var result = (mass * grav) * angle
            var resultText = "%.1f".format((mass * grav) * angle)
            binding.editN.text = resultText
            if(result in 29.0..30.0)
            {
                binding.editN.setTextColor(Color.GREEN)
                binding.submitText.setTextColor(Color.GREEN)
                binding.submitText.text = "Correct! Well Done!"
            }
            else
            {
                binding.editN.setTextColor(Color.RED)
                binding.submitText.setTextColor(Color.RED)
                binding.submitText.text = "Oops! Try Again"
            }
        }
        binding.editM.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //TODO("Not yet implemented")
            }
            /*
            Remove listener to change inputDecimal to string
             */
            override fun afterTextChanged(s: Editable?) {
                //TODO("Not yet implemented")
                if (s.toString().trim().isNotEmpty()) {
                    binding.editM.removeTextChangedListener(this)
                    mass = java.lang.Double.valueOf(binding.editM.getText().toString())
                    binding.editM.addTextChangedListener(this)
                }
            }
        })
        binding.editG.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //TODO("Not yet implemented")
            }
            /*
            Remove listener to change inputDecimal to string
             */
            override fun afterTextChanged(s: Editable?) {
                //TODO("Not yet implemented")
                if (s.toString().trim().isNotEmpty()) {
                    binding.editG.removeTextChangedListener(this)
                    grav = java.lang.Double.valueOf(binding.editG.getText().toString())
                    binding.editG.addTextChangedListener(this)
                }
            }
        })
        binding.editA.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //TODO("Not yet implemented")
            }
            /*
            Remove listener to change inputDecimal to string
             */
            override fun afterTextChanged(s: Editable?) {
                //TODO("Not yet implemented")
                if (s.toString().trim().isNotEmpty()) {
                    binding.editM.removeTextChangedListener(this)
                    angle = java.lang.Double.valueOf(binding.editA.getText().toString())
                    binding.editA.addTextChangedListener(this)
                }
            }
        })
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fourth_blank, container, false)
        binding = FragmentFourthBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FourthBlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FourthBlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}