package com.zzz.dishesapp.feature_recipes.di

import com.zzz.dishesapp.feature_recipes.data.DishesSourceImpl
import com.zzz.dishesapp.feature_recipes.data.util.HttpClientFactory
import com.zzz.dishesapp.feature_recipes.domain.source.DishesSource
import com.zzz.dishesapp.feature_recipes.presentation.viewmodel.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dishModule = module {

    single<HttpClient> {
        HttpClientFactory.create(
            engine = get()
        )
    }
    single<HttpClientEngine> {
        OkHttp.create()
    }

    viewModel<HomeViewModel> {
        HomeViewModel(
            dishesSource = get()
        )
    }
    single<DishesSource> {
        DishesSourceImpl(
            client = get()
        )
    }

}