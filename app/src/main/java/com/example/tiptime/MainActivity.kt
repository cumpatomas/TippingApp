@file:Suppress("UnusedImport")

package com.example.tiptime

import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calcular.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.veinte -> 0.15
            R.id.dieciocho -> 0.10
            R.id.diez -> 0.05
            else -> 0.0
        }

        var tip = tipPercentage * cost
        if (binding.redondeo.isChecked) {
            tip = kotlin.math.floor(tip)
        }
        java.text.NumberFormat.getCurrencyInstance()
        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = java.text.NumberFormat.getCurrencyInstance().format(tip)
        binding.resultado.text = getString(R.string.propina, formattedTip)
    }


}