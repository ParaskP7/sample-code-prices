package io.petros.prices.presentation.feature.prices

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.petros.prices.domain.interactor.price.LoadPricesUseCase
import io.petros.prices.domain.model.price.Price
import io.petros.prices.presentation.feature.prices.subscriber.PricesSubscriber
import javax.inject.Inject

class PricesActivityViewModel @Inject constructor(
    private val loadPricesUseCase: LoadPricesUseCase
) : ViewModel() {

    val pricesObservable = MutableLiveData<Price>()

    fun loadPrices() {
        loadPricesUseCase.execute(PricesSubscriber(pricesObservable))
    }

    override fun onCleared() {
        super.onCleared()
        loadPricesUseCase.dispose()
    }

}
