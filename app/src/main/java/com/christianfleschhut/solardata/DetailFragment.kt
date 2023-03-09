package com.christianfleschhut.solardata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.christianfleschhut.solardata.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var viewModel: MainViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(requireActivity())[MainViewModel::class.java]
        }

        viewModel?.selectedDevice?.observe(viewLifecycleOwner) { device ->
            with(device) {
                binding.ivDeviceImage.load(image) {
                    crossfade(400)
                }
                binding.tvDeviceName.text = name
                binding.tvDeviceOutput.text = getString(R.string.detail_power_output, output)
            }
        }
    }
}
