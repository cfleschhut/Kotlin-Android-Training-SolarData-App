package com.christianfleschhut.solardata

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class HistoryFragment : Fragment(R.layout.fragment_history) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Chart(container = view).display()
    }

}