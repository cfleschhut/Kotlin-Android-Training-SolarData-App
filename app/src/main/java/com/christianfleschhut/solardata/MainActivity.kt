package com.christianfleschhut.solardata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chart = findViewById<LineChart>(R.id.chart)

        val entries = arrayListOf<Entry>()

        val exampleData = listOf<Map<String, Float>>(
            mapOf("x" to 0.0f, "y" to 0.0f),
            mapOf("x" to 1.0f, "y" to 2.0f),
            mapOf("x" to 2.0f, "y" to 1.0f),
            mapOf("x" to 3.0f, "y" to 3.0f),
            mapOf("x" to 4.0f, "y" to 0.0f),
        )

        exampleData.forEach {
            entries.add(Entry(it["x"]!!, it["y"]!!))
        }

        val dataSet = LineDataSet(entries, "Label")
        val lineData = LineData(dataSet)

        chart.data = lineData
        chart.invalidate()
    }
}
