package com.christianfleschhut.solardata

import android.view.View
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.random.Random

class Chart(private val container: View) {

    fun display() {
        val chart = container.findViewById<LineChart>(R.id.chart)

        val entries = arrayListOf<Entry>()

        val exampleData = generateExampleData(12)
        exampleData.forEach { (time, value) ->
            entries.add(Entry(time, value))
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
