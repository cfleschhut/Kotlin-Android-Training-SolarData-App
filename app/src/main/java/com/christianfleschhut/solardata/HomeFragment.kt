package com.christianfleschhut.solardata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.christianfleschhut.solardata.data.Device
import com.christianfleschhut.solardata.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var viewModel: MainViewModel? = null

    private val onItemClick: (target: Device) -> Unit = { device ->
        println("onItemClick: $device")
        viewModel?.storeSelectedDevice(device)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this)[MainViewModel::class.java]
        }

        viewModel?.isLoading?.observe(viewLifecycleOwner) { isLoading ->
            binding.pbDeviceList.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel?.errorMessage?.observe(viewLifecycleOwner) { errorMsg ->
            if (errorMsg != null) {
                binding.vgErrorMsg.visibility = View.VISIBLE
                Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
            } else {
                binding.vgErrorMsg.visibility = View.GONE
            }
        }

        viewModel?.devices?.observe(viewLifecycleOwner) { devices ->
            binding.rvDeviceList.adapter = DeviceAdapter(devices, onItemClick)
        }

//        binding.btnRetryFetch.setOnClickListener {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
