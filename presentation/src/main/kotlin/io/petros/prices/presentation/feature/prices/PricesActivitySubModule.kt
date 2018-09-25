package io.petros.prices.presentation.feature.prices

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.petros.prices.presentation.di.dagger.activity.SubModuleBinding
import io.petros.prices.presentation.di.dagger.viewmodel.ViewModelKey

@Module
abstract class PricesActivitySubModule : SubModuleBinding<PricesActivity> {

    @Binds
    @IntoMap
    @ViewModelKey(PricesActivityViewModel::class)
    abstract fun bindPricesActivityViewModel(pricesActivityViewModel: PricesActivityViewModel): ViewModel

}
