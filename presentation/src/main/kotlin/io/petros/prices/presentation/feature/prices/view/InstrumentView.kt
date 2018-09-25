package io.petros.prices.presentation.feature.prices.view

import android.content.Context
import android.widget.LinearLayout
import io.petros.prices.R
import io.petros.prices.domain.model.price.Price
import io.petros.prices.presentation.feature.prices.PricesActivity
import io.petros.prices.presentation.inflate
import kotlinx.android.synthetic.main.view_instrument.view.*

class InstrumentView(context: Context) : LinearLayout(context) {

    companion object {

        private const val FORMAT_DOUBLE_TO_TWO_DECIMAL_PLACES = "%.2f"

    }

    init {
        inflate(R.layout.view_instrument)
        initView()
    }

    private fun initView() {
        orientation = HORIZONTAL
        initSubscribeButton()
        initUnsubscribeButton()
    }

    private fun initSubscribeButton() {
        btn_subscribe.setOnClickListener {
            (context as? PricesActivity)?.onSubscribe(et_isin.text.toString())
            et_isin.isEnabled = false
        }
    }

    private fun initUnsubscribeButton() {
        btn_unsubscribe.setOnClickListener {
            (context as? PricesActivity)?.onUnsubscribe(et_isin.text.toString())
            et_isin.isEnabled = true
        }
    }

    /* BIND */

    fun matchesIsin(price: Price): Boolean = et_isin.text.toString() == price.isin

    fun bind(price: Price) {
        tv_price.text = String.format(FORMAT_DOUBLE_TO_TWO_DECIMAL_PLACES, price.price)
    }

}
