

package com.example.a7minworkoutapp

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minworkoutapp.databinding.ActivityExerciseBinding
import com.example.a7minworkoutapp.databinding.DilogboxBinding
import java.util.Locale


class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding : ActivityExerciseBinding?=null

    private var resttimer : CountDownTimer? =null
    private var restprogress : Int =0

    private var resttimerexercise : CountDownTimer? =null
    private var restprogressexercise : Int =0

    private  var exerciselist : ArrayList<exercisemodel>? =null
    private var currentexercise : Int =-1
    private var tts:TextToSpeech?=null
    private var player:MediaPlayer?=null
    private  var exerciseadapter:exrciseadapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbar)

        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbar?.setNavigationOnClickListener{
            customdilog()

        }
        exerciselist=Constants.defaultexerciseelist()

        tts = TextToSpeech(this,this)


        binding?.flready?.visibility=View.VISIBLE
        binding?.flexercise?.visibility=View.INVISIBLE
        settimer()
        setuprecyclerview()
    }

    override fun onBackPressed() {
        customdilog()
    }
    private fun customdilog(){
        val cdialog= Dialog(this)
        val dialogbinding = DilogboxBinding.inflate(layoutInflater)
        cdialog.setContentView(dialogbinding.root)
        cdialog.setCanceledOnTouchOutside(false)
        dialogbinding.tvYes.setOnClickListener(){
            this@ExerciseActivity.finish()
            cdialog.dismiss()
        }
        dialogbinding.tvNo.setOnClickListener(){
                cdialog.dismiss()
        }
        cdialog.show()
    }

    private fun setuprecyclerview(){
        binding?.rvexercisestatus?.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseadapter = exrciseadapter(exerciselist!!)
        binding?.rvexercisestatus?.adapter=exerciseadapter
    }

    private fun settimer(){

        try {
            val sounduri =Uri.parse("android.resource://com.example.a7minworkoutapp/"+R.raw.app_src_main_res_raw_press_start)
            player= MediaPlayer.create(applicationContext,sounduri)
            player?.isLooping =false
            player?.start()
        }catch (e : Exception){
            e.printStackTrace()
        }



        binding?.progressbar?.progress =restprogress
        binding?.flready?.visibility = View.VISIBLE
        binding?.textready?.visibility= View.VISIBLE
        binding?.tvexercisename?.visibility=View.INVISIBLE
        binding?.tvexerciseimage?.visibility=View.INVISIBLE
        binding?.flexercise?.visibility = View.INVISIBLE
        val ext= exerciselist!![currentexercise+1].getname()
        binding?.textready?.text = "Get ready for $ext"
        restprogress=0
        speakout("Rest and Get ready for $ext")
        resttimer= object :CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restprogress++
                binding?.progressbar?.progress=10-restprogress
                binding?.timer?.text =(10-restprogress).toString()
            }
            override fun onFinish() {
                currentexercise++
                exerciselist!![currentexercise].setisSelected(true)
                exerciseadapter!!.notifyDataSetChanged()
                setexerciseview()
            }


        }.start()
    }
    private fun setexerciseview() {

        binding?.flready?.visibility = View.INVISIBLE
        binding?.textready?.visibility= View.INVISIBLE
        binding?.tvexercisename?.visibility=View.VISIBLE
        binding?.tvexerciseimage?.visibility=View.VISIBLE
        binding?.flexercise?.visibility = View.VISIBLE
        if (resttimerexercise != null) {
            resttimerexercise?.cancel()
            restprogressexercise = 0
        }
        speakout(exerciselist!![currentexercise].getname())
        binding?.tvexerciseimage?.setImageResource(exerciselist!![currentexercise].getimage())
        binding?.tvexercisename?.text=exerciselist!![currentexercise].getname()
        settimerexercise()
    }
    private fun settimerexercise(){

        binding?.progressbarexercise?.progress =restprogressexercise
        resttimerexercise= object :CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restprogressexercise++
                binding?.progressbarexercise?.progress=30-restprogressexercise
                binding?.timerexercise?.text =(30-restprogressexercise).toString()
            }
            override fun onFinish() {

                if(currentexercise!=exerciselist!!.size-1){
                    exerciselist!![currentexercise].setisSelected(false)
                    exerciselist!![currentexercise].setisCompleted(true)
                    exerciseadapter!!.notifyDataSetChanged()
                    settimer()
                }
                else{
                    finish()
                    startActivity(Intent(this@ExerciseActivity,finishactivity::class.java))

                }

            }
        }.start()
    }
    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val  result = tts?.setLanguage(Locale.US)

            if(result==TextToSpeech.LANG_MISSING_DATA || result ==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","the language specified is not supported")
            }
        }
        else{
            Log.e("TTS","initialisation failed")
        }
    }
    private fun speakout(text : String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy() {
        super.onDestroy()
        if(resttimer != null){
            resttimer?.cancel()
            restprogress=0
        }
        if(resttimerexercise!=null){
            resttimerexercise?.cancel()
            restprogressexercise=0
        }


        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        if(player!=null){
            player?.stop()
        }


        binding=null
    }



}