package com.example.hardwaresensors

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : AppCompatActivity(), SensorEventListener {
//    lateinit var sensorEventListener: SensorEventListener
    lateinit var sensorManager:SensorManager
    lateinit var proxSensor:Sensor
    lateinit var accelSensor:Sensor
    val colors= arrayOf(Color.RED,Color.GREEN,Color.BLUE,Color.CYAN,Color.YELLOW,Color.MAGENTA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager=getSystemService<SensorManager>()!!

        proxSensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        accelSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
//        sensorEventListener=this
//        sensorEventListener = object :SensorEventListener{
//            override fun onSensorChanged(p0: SensorEvent?) {
//                Log.d("XMSENS","""
//                    onSensorChanged: ${p0!!.values[0]}
//                """.trimIndent())            }
//
//            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
//
//            }
//
//        }
//        if(sensorManager==null)
//        {
//            Toast.makeText(this,"Couln not access sensors!",Toast.LENGTH_SHORT).show()
//            finish()
//        }
//        else
//        {
//            val sensors=sensorManager.getSensorList(Sensor.TYPE_ALL)
//            sensors.forEach{
//                Log.d("XMSENS","""
//                    ${it.name} | ${it.stringType} | ${it.vendor}
//                """.trimIndent())
//            }
//        }
    }

    override fun onResume() {
        super.onResume()
//        sensorManager.registerListener(this,proxSensor,1000*1000)
        sensorManager.registerListener(this,accelSensor,1000*1000)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
//        Log.d("XMSENS","""
//                    onSensorChanged: ${p0!!.values[0]}
//                """.trimIndent())

//        if(p0.values[0]>0)
//        {
//            prox.setBackgroundColor(colors[Random.nextInt(6)])
//        }
        Log.d("XMSENS","""
            ----
            ax = ${p0!!.values[0]}
            ay = ${p0.values[1]}
            az = ${p0.values[2]}
        """.trimIndent())

        val bgcolor=Color.rgb(
                onaccelColor(p0.values[0]),
                onaccelColor(p0.values[1]),
                onaccelColor(p0.values[2])
        )
        acc.setBackgroundColor(bgcolor)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    private fun onaccelColor(accel:Float) = (((accel+12)/24)*255).roundToInt()

}