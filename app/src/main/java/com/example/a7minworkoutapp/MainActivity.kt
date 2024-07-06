package com.example.a7minworkoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.widget.FrameLayout
import android.widget.Toast
import com.example.a7minworkoutapp.databinding.ActivityHistoryBinding
import com.example.a7minworkoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root) 

        binding?.flstrt?.setOnClickListener{
           startActivity(Intent(this,ExerciseActivity::class.java))
        }
       binding?.flBMI?.setOnClickListener{
            startActivity(Intent(this,BMI::class.java))
        }
        binding?.flhistory?.setOnClickListener{
            startActivity(Intent(this,history::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}

