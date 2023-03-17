package com.christianfleschhut.solardata

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.christianfleschhut.solardata.data.MeasurementRepository
import com.google.android.material.tabs.TabLayout

class HistoryFragment : Fragment(R.layout.fragment_history) {

    private lateinit var tabLayout: TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.findViewById(R.id.tlTabs)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        val tabs = listOf("Day", "Week", "Month", "Year", "Lifetime")
        tabs.forEach {
            tabLayout.addTab(tabLayout.newTab().setText(it))
        }

        val measurements = MeasurementRepository().getMeasurements(requireContext(),
            "measurements.json")
        Chart(
            chartView = view.findViewById(R.id.acChart),
            data = measurements
        )
    }

}
