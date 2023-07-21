package cl.cat2814.a17currencyconverter

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import cl.cat2814.a17currencyconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val currencies = listOf<String>("USD/Dolar", "CLP/Peso", "EUR/Euro")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spinInputCurrency.adapter =
            ArrayAdapter(this, R.layout.spinner_item, currencies)
        binding.spinOutputCurrency.adapter =
            ArrayAdapter(this, R.layout.spinner_item, currencies)

        initListeners()
    }

    private fun initListeners() {
        binding.btConvert.setOnClickListener {
            val amount = binding.etAmountInput.text.toString().toDouble()
            val currencyIn = binding.spinInputCurrency.selectedItem.toString()
            val currencyOut = binding.spinOutputCurrency.selectedItem.toString()

            val exchangeAmount = converter(amount, currencyIn, currencyOut)
            binding.tvAmountOutput.text = exchangeAmount.toString()
        }

        binding.btReset.setOnClickListener {
            reset()
        }
    }

    fun converter(amount: Double, currencyIn: String, currencyOut: String): Double {
        var exchangedAmount = amount

        when (currencyIn) {
            "USD/Dolar" -> if (currencyOut == "CLP/Peso") {
                exchangedAmount = amount * 817

            } else if (currencyOut == "USD/Dolar") {
                exchangedAmount = amount * 1

            } else if (currencyOut == "EUR/Euro") {
                exchangedAmount = amount * 0.89
            }

            "CLP/Peso" -> if (currencyOut == "CLP/Peso") {
                exchangedAmount = amount * 1

            } else if (currencyOut == "USD/Dolar") {
                exchangedAmount = amount * 0.001

            } else if (currencyOut == "EUR/Euro") {
                exchangedAmount = amount * 0.001
            }

            "EUR/Euro" -> if (currencyOut == "CLP/Peso") {
                exchangedAmount = amount * 910

            } else if (currencyOut == "USD/Dolar") {
                exchangedAmount = amount * 1.11

            } else if (currencyOut == "EUR/Euro") {
                exchangedAmount = amount * 1
            }

            else -> {
                exchangedAmount = amount
            }
        }
        return exchangedAmount
    }

    fun reset() {
        binding.etAmountInput.text.clear()
        binding.tvAmountOutput.text = ""
    }
}
