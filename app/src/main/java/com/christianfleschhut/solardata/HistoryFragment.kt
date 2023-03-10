package com.christianfleschhut.solardata

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.christianfleschhut.solardata.data.MeasurementRepository

class HistoryFragment : Fragment(R.layout.fragment_history) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val measurements = MeasurementRepository().getMeasurements(requireContext(),
            "measurements.json")

        Chart(
            chartView = view.findViewById(R.id.acChart),
            data = measurements
        )
    }

}
