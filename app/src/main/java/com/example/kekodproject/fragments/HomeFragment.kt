package com.example.kekodproject.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.kekodproject.R
import com.example.kekodproject.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bottomNavView: BottomNavigationView
    private var switchHistory = mutableListOf<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ego switch'i açık olarak ayarlanıyor
        binding.switchEgo.isChecked = true

        // MainActivity'deki BottomNavigationView'a erişim sağlanıyor
        val activity = activity as? AppCompatActivity
        bottomNavView = activity?.findViewById(R.id.bottom_nav)!!

        setupSwitchListeners()
        updateSwitchStates()
    }

    private fun setupSwitchListeners() {
        binding.switchEgo.setOnCheckedChangeListener { _, _ ->
            updateSwitchStates()
        }

        // Diğer switch'ler için listener'lar
        val otherSwitches = listOf(
            binding.switch1,
            binding.switch2,
            binding.switch3,
            binding.switch4,
            binding.switch5
        )

        otherSwitches.forEachIndexed { index, switch ->
            switch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    addSwitchToHistory(index + 1)
                } else {
                    removeSwitchFromHistory(index + 1)
                }
                updateBottomNavigation()
            }
        }
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun updateSwitchStates() {
        val isEgoSwitchOn = binding.switchEgo.isChecked
        val otherSwitches = listOf(
            binding.switch1,
            binding.switch2,
            binding.switch3,
            binding.switch4,
            binding.switch5
        )

        otherSwitches.forEach {
            it.isEnabled = !isEgoSwitchOn
            if (isEgoSwitchOn) {
                it.isChecked = false
            }
        }

        if (isEgoSwitchOn) {
            bottomNavView.visibility = View.GONE
            bottomNavView.menu.clear()  // BottomNavigationView'ı temizliyoruz
        } else {
            bottomNavView.visibility = View.VISIBLE
            updateBottomNavigation()
        }
    }

    private fun addSwitchToHistory(switchId: Int) {
        // Eğer 4 switch varsa en eskisini çıkar
        if (switchHistory.size >= 4) {
            switchHistory.removeAt(0)
        }
        switchHistory.add(switchId)
    }

    private fun removeSwitchFromHistory(switchId: Int) {
        switchHistory.remove(switchId)
    }

    private fun updateBottomNavigation() {
        val menu = bottomNavView.menu
        menu.clear()
        addMenuItem(0)  // Ana ekranı ekliyoruz

        switchHistory.forEach {
            addMenuItem(it)
        }

        bottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                0 -> findNavController().navigate(R.id.homeFragment)
                1 -> findNavController().navigate(R.id.detailFragment)
                2 -> findNavController().navigate(R.id.detailFragment)
                3 -> findNavController().navigate(R.id.detailFragment)
                4 -> findNavController().navigate(R.id.detailFragment)
                5 -> findNavController().navigate(R.id.detailFragment)
            }
            true
        }
    }

    // Menü öğesi ekleme fonksiyonu
    private fun addMenuItem(id: Int) {
        val menu = bottomNavView.menu
        val menuItem = menu.add(0, id, id, "Item $id")
        when (id) {
            0 -> menuItem.setIcon(R.drawable.baseline_home_24)  // Ana ekran ikonu
            1 -> menuItem.setIcon(R.drawable.baseline_home_24)
            2 -> menuItem.setIcon(R.drawable.baseline_home_24)
            3 -> menuItem.setIcon(R.drawable.baseline_home_24)
            4 -> menuItem.setIcon(R.drawable.baseline_home_24)
            5 -> menuItem.setIcon(R.drawable.baseline_home_24)
        }
    }
}