@file:Suppress("DEPRECATION")

package com.example.a7minworkoutapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.example.a7minworkoutapp.databinding.ActivityExerciseBinding
import com.example.a7minworkoutapp.databinding.ActivityFinishactivityBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class finishactivity : AppCompatActivity() {
    private var binding :ActivityFinishactivityBinding ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbar)

        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbar?.setNavigationOnClickListener{1
            super.onBackPressed()
        }
        binding?.finishbtn?.setOnClickListener {
            finish()
        }
        val dao =(application as WorkOutApp).db.historyDao()
        addDateToDatabase(dao)
    }
    private fun addDateToDatabase(historyDao: HistoryDao){
        val c=Calendar.getInstance()
        val datetime=c.time
        Log.e("Date: ","" +datetime)
        val sdf= SimpleDateFormat("dd MMM yyyy HH:mm:ss",Locale.getDefault())
        val date= sdf.format(datetime)
        Log.e("Formatted Date : ",""+date)
        lifecycleScope.launch{historyDao.insert(com.example.a7minworkoutapp.historyentity(date))
        Log.e(
            "Date : ",
            "Added..."
        )
        }
    }
}