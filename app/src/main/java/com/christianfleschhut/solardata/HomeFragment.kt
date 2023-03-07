package com.christianfleschhut.solardata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.christianfleschhut.solardata.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = activity?.run {
            ViewModelProvider(this)[MainViewModel::class.java]
        }

        viewModel?.devices?.observe(viewLifecycleOwner) { devices ->
//            devices.forEach { println(it) }
            binding.rvDeviceList.adapter = DeviceAdapter(devices)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
