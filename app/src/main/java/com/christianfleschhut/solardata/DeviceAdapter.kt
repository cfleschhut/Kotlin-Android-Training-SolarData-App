package com.christianfleschhut.solardata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.christianfleschhut.solardata.data.Device
import com.christianfleschhut.solardata.databinding.DeviceItemBinding

class DeviceAdapter(private val items: List<Device>, private val onItemClick: (target: Device) -> Unit) :
    RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    inner class ViewHolder(val viewItem: DeviceItemBinding) : RecyclerView.ViewHolder(viewItem.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DeviceItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val device = items[position]
        with(holder.viewItem) {
            ivDeviceImage.load(device.image) {
                crossfade(400)
            }
            tvDeviceName.text = device.name
        }

        holder.itemView.setOnClickListener {
            onItemClick(device)
        }
    }
}
