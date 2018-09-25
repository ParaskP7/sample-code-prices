package io.petros.prices.presentation.feature.prices

import android.arch.lifecycle.Observer
import android.os.Bundle
import io.petros.prices.R
import io.petros.prices.presentation.feature.BaseActivity
import kotlinx.android.synthetic.main.activity_prices.*

class PricesActivity : BaseActivity<PricesActivityViewModel>() {

    companion object {

        private const val INSTRUMENT_1 = "DE000BASF111"
        private const val INSTRUMENT_2 = "US68389X1054"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initButtons()
        initObservers()
        subscribeForUpdates()
    }

    /* VIEWS */

    private fun initButtons() {
        subscribe.setOnClickListener {
            viewModel.subscribeToInstrument(INSTRUMENT_1)
            viewModel.subscribeToInstrument(INSTRUMENT_2)
        }
        unsubscribe.setOnClickListener {
            viewModel.unsubscribeFromInstrument(INSTRUMENT_1)
            viewModel.unsubscribeFromInstrument(INSTRUMENT_2)
        }
    }

    /* OBSERVERS */

    private fun initObservers() {
        observePrices()
    }

    private fun observePrices() {
        viewModel.pricesObservable.observe(this, Observer { it ->
            it?.let { output.text = it.toString() }
        })
    }

    /* DATA LOADING */

    private fun subscribeForUpdates() {
        viewModel.subscribeForPriceUpdates()
    }

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_prices

    override fun constructViewModel() = PricesActivityViewModel::class.java

}
