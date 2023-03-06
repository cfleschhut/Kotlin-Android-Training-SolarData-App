package com.christianfleschhut.solardata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.christianfleschhut.solardata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) return

        supportFragmentManager.commit {
//            setReorderingAllowed(true)
            add<HomeFragment>(R.id.fragmentContainer)
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> setFragment(HomeFragment())
                R.id.action_history -> setFragment(HistoryFragment())
                R.id.action_settings -> setFragment(SettingsFragment())
                else -> false
            }
        }
    }

    private fun setFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, fragment).commit()
        }
        return true
    }
}
