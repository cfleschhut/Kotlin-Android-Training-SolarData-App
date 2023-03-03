package com.christianfleschhut.solardata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chart = findViewById<LineChart>(R.id.chart)

        val entries = arrayListOf<Entry>()

        val exampleData: List<Measurement> = generateExampleData()
        exampleData.forEach { (timestamp, value) ->
            entries.add(Entry(timestamp, value))
        }

        val dataSet = LineDataSet(entries, "Label")
        val lineData = LineData(dataSet)

        chart.data = lineData
        chart.invalidate()
    }

    private fun generateExampleData(hours: Int = 24): List<Measurement> {
        return (0 until hours).mapTo(mutableListOf()) {
            Measurement(it.toFloat(), Random.nextFloat())
        }
    }
}

data class Measurement(
    val timestamp: Float = 0f,
    val value: Float = 0f
)
