package io.petros.prices.domain.model.instrument

data class Instrument(
    val isin: String,
    val price: Double,
    val bid: Double,
    val ask: Double
)
