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

        val exampleData = listOf(
            Pair(0.0f, 0.0f),
            Pair(1.0f, 2.0f),
            Pair(2.0f, 1.0f),
            Pair(3.0f, 3.0f),
            Pair(4.0f, 0.0f),
        ).forEach { (x, y) ->
            entries.add(Entry(x, y))
        }

        val dataSet = LineDataSet(entries, "Label")
        val lineData = LineData(dataSet)

        chart.data = lineData
        chart.invalidate()
    }
}
