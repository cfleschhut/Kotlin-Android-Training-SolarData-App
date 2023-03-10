package com.christianfleschhut.solardata

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.christianfleschhut.solardata.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private var viewModel: MainViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(requireActivity())[MainViewModel::class.java]
        }

        viewModel?.userInfo?.observe(viewLifecycleOwner) {
            binding.tvUserInfo.text = getString(R.string.settings_username, it)
        }

        viewModel?.selectedDevice?.observe(viewLifecycleOwner) {
            binding.tvDeviceInfo.text = getString(R.string.settings_device, it?.name)
            binding.tvDeviceInfoPowerOutput.text = getString(
                R.string.settings_device_output, it.output ?: "")
        }

        binding.btnLogout.setOnClickListener {
            viewModel?.resetUserInfo()

            activity?.run {
                startActivity(
                    Intent(this, LoginActivity::class.java)
                )
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
