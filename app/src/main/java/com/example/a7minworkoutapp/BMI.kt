package com.example.a7minworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.a7minworkoutapp.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.zip.Inflater

class BMI : AppCompatActivity() {
    private var binding:ActivityBmiBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarBMI)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="CALCULATE BMI"
        }
        binding?.toolbarBMI?.setNavigationOnClickListener{
         onBackPressed()
        }
        binding?.btnbmi?.setOnClickListener{
            if(Validmetric()){
                val weight:Float=binding?.emetricweight?.text.toString().toFloat()
                val height:Float=binding?.emetricheight?.text.toString().toFloat()/100
                val bmi=weight/(height*height)
                displaybmiresult(bmi)
            }
            else{
                Toast.makeText(this, "Please Enter Valid Values.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun displaybmiresult(bmi : Float){
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                bmi,
                30f
            ) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }
        binding?.llbmi?.visibility=View.VISIBLE
        binding?.bmidescription?.text=bmiDescription
        binding?.bmitype?.text=bmiLabel
        binding?.bmivalue?.text= BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
    }
    private fun Validmetric():Boolean{
        var isvalid=true
        if(binding?.emetricweight?.text.toString().isEmpty()){
            isvalid=false
        }
        else if(binding?.emetricheight?.text.toString().isEmpty()){
            isvalid=false
        }
        return isvalid
    }
}