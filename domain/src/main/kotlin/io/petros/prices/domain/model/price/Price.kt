package io.petros.prices.domain.model.price

data class Price(
    val isin: String,
    val price: Double,
    val bid: Double,
    val ask: Double
)
