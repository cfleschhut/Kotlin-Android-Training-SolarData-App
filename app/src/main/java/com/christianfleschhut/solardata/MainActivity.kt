package com.christianfleschhut.solardata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        entries.add(Entry(0.0f, 0.0f))
        entries.add(Entry(2.0f, 4.0f))

        val dataSet = LineDataSet(entries, "Label")
        val lineData = LineData(dataSet)

        chart.data = lineData
        chart.invalidate()
    }
}