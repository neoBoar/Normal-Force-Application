package com.example.myscienceapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.myscienceapp.databinding.FragmentThirdBlankBinding
import kotlin.math.cos
import kotlin.math.sqrt

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdBlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdBlankFragment : Fragment(), SensorEventListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : FragmentThirdBlankBinding
    private lateinit var navController: NavController
    lateinit var sensorManager:SensorManager
    lateinit var accelerometer:Sensor
    var mass = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding.backButton.setOnClickListener {
            navController.navigate(R.id.action_thirdBlankFragment_to_secondBlankFragment) //Navigate from 3rd page back to 2nd page on button click
        }
        binding.nextButton3.setOnClickListener {
            navController.navigate(R.id.action_thirdBlankFragment_to_fourthBlankFragment) //Navigate from 3rd page to 4th page on button click
        }
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) //get accelerometer data and attribute this to accelerometer variable
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_third_blank, container, false)
        binding = FragmentThirdBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThirdBlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThirdBlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        //TODO("Not yet implemented")
        if(event==null)
            return
        if(event.sensor.type==Sensor.TYPE_ACCELEROMETER) {
            var x = event.values[0] //tilt value applied to the movement of the weight image
            /*
            calculate the angle based off of the accelerometer data
             */
            var x2 = event.values[0] * event.values[0]
            var y2 = event.values[1] * event.values[1]
            var z2 = event.values[2] * event.values[2]
            /*
            calculate the normal force
             */
            var grav = 9.8
            var sum = sqrt(y2 + z2)
            var result = x2 / sum //cos(angle)
            var weight = mass * grav
            var weightOutput = "%.2f".format(weight)
            var normForce = "%.2f".format(weight * cos(result))

            /*
            assign values to textviews on fragment page
             */
            binding.weightTV.text = ("Weight = " + weightOutput)
            binding.angleTV.text = ("Angle = " + "%.2f".format(result))
            binding.normForceTV.text = ("Normal Force = " + normForce)

            /*
            TextWatcher to find the mass that the user has input
             */
            binding.setMass.addTextChangedListener(object : TextWatcher {
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
                        binding.setMass.removeTextChangedListener(this)
                        mass = java.lang.Double.valueOf(binding.setMass.getText().toString())
                        binding.setMass.addTextChangedListener(this)
                    }
                }
            })
            /*
            condition for the weight image to stop movement if the boundary is hit or the user has not input a value to the mass field
             */
            if (binding.weight.translationX <= 250.0f && binding.weight.translationX >= -250.0f && mass != 0.0) {
                binding.weight.translationX -= x
                binding.setMass.translationX -= x
                Log.d("MyTag", binding.weight.translationX.toString())
            } else if (binding.weight.translationX > 250.0f && binding.setMass.translationX > 250.0f) {
                binding.weight.translationX = 250.0f
                binding.setMass.translationX = 250.0f
            } else if (binding.weight.translationX < -100.0f && binding.setMass.translationX < -250.0f) {
                binding.weight.translationX = -250.0f
                binding.setMass.translationX = -250.0f
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //TODO("Not yet implemented")
    }
}