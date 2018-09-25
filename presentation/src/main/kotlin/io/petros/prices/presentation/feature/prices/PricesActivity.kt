package io.petros.prices.presentation.feature.prices

import android.arch.lifecycle.Observer
import android.os.Bundle
import io.petros.prices.R
import io.petros.prices.domain.model.price.Price
import io.petros.prices.presentation.feature.BaseActivity
import io.petros.prices.presentation.feature.prices.listener.SubscriptionCallback
import io.petros.prices.presentation.feature.prices.view.InstrumentView
import kotlinx.android.synthetic.main.activity_prices.*

@Suppress("TooManyFunctions")
class PricesActivity : BaseActivity<PricesActivityViewModel>(), SubscriptionCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInstruments()
        initObservers()
        subscribeForUpdates()
    }

    /* VIEWS */

    private fun initInstruments() {
        btn_add.setOnClickListener { addInstrument() }
        addInstrument()
    }

    private fun addInstrument() {
        ll_instruments.addView(InstrumentView(this))
    }

    /* OBSERVERS */

    private fun initObservers() {
        observePrices()
    }

    private fun observePrices() {
        viewModel.pricesObservable.observe(this, Observer { it ->
            it?.let { checkAndDisplayPrice(it) }
        })
    }

    @Suppress("NestedBlockDepth")
    private fun checkAndDisplayPrice(price: Price) {
        for (index in 0..ll_instruments.childCount) {
            val instrumentView = ll_instruments.getChildAt(index) as? InstrumentView
            instrumentView?.let { if (it.matchesIsin(price)) it.bind(price) }
        }
    }

    /* DATA LOADING */

    private fun subscribeForUpdates() {
        viewModel.subscribeForPriceUpdates()
    }

    /* CALLBACKS */

    override fun onSubscribe(isin: String) {
        viewModel.subscribeToInstrument(isin)
        addInstrument()
    }

    override fun onUnsubscribe(isin: String) {
        viewModel.unsubscribeFromInstrument(isin)
        addInstrument()
    }

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_prices

    override fun constructViewModel() = PricesActivityViewModel::class.java

}
