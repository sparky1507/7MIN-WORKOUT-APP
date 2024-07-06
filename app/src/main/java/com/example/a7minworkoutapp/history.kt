package com.example.a7minworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minworkoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

class history : AppCompatActivity() {
    private var binding:ActivityHistoryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarHistory)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="History"
        }
        binding?.toolbarHistory?.setNavigationOnClickListener {
            onBackPressed()
        }
        val dao= (application as WorkOutApp).db.historyDao()
        getAllcompletedates(dao)
    }

    private fun getAllcompletedates(historyDao: HistoryDao){
        lifecycleScope.launch{
            historyDao.fetchalldates().collect{
                getAllcompletedates->
                if(getAllcompletedates.isNotEmpty()){
                    binding?.tvHistory?.visibility= View.VISIBLE
                    binding?.rvHistory?.visibility=View.VISIBLE
                    binding?.tvNoDataAvailable?.visibility=View.INVISIBLE
                    binding?.rvHistory?.layoutManager=LinearLayoutManager(
                        this@history
                    )
                    val dates=ArrayList<String>()
                    for(date in getAllcompletedates){
                       dates.add(date.date)
                    }
                    val historyAdaptar=HistoryAdaptar(dates)

                    binding?.rvHistory?.adapter=historyAdaptar
                }
                else{
                    binding?.tvHistory?.visibility= View.INVISIBLE
                    binding?.rvHistory?.visibility=View.INVISIBLE
                    binding?.tvNoDataAvailable?.visibility=View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}