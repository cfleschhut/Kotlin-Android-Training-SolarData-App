package com.christianfleschhut.solardata

import com.christianfleschhut.solardata.data.Measurement
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter

class Formatter : ValueFormatter() {

    override fun getBarLabel(barEntry: BarEntry?): String {
        val entryVal = barEntry?.y

        return if (entryVal!! > 0)
            if ((entryVal % 1.0) > 0) "$entryVal" else "${entryVal.toInt()}"
        else
            ""
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val intVal = value.toInt()
        return if (value < 10) "0${intVal}:00" else "${intVal}:00"
    }
}

class Chart(chartView: BarChart, data: List<Measurement>?) {

    init {
        val entries = arrayListOf<BarEntry>()

        data?.forEach {
            entries.add(BarEntry(it.timestamp, it.value))
        }

        val dataSet = BarDataSet(entries, "kW")

        val chartData = BarData(dataSet)
        chartData.setValueFormatter(Formatter())

        chartView.data = chartData
        chartView.xAxis.valueFormatter = Formatter()

        chartView.invalidate()
    }
}
