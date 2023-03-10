package com.christianfleschhut.solardata

import com.christianfleschhut.solardata.data.Measurement
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import kotlin.random.Random

class Chart(chartView: BarChart, data: List<Measurement>?) {

    init {
        val entries = arrayListOf<BarEntry>()

        data?.forEach {
            entries.add(BarEntry(it.timestamp, it.value))
        }

//        val exampleData = generateExampleData()
//        exampleData.forEach { (time, value) ->
//            entries.add(Entry(time, value))
//        }

        val dataSet = BarDataSet(entries, "kW")
        val chartData = BarData(dataSet)

        chartView.data = chartData
        chartView.invalidate()
    }

//    private fun generateExampleData(hours: Int = 24): List<Measurement> {
//        return (0 until hours).mapTo(mutableListOf()) {
//            Measurement(it.toFloat(), Random.nextFloat() * 20)
//        }
//    }
}
