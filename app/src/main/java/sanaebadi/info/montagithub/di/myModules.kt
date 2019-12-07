package sanaebadi.info.montagithub.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import sanaebadi.info.montagithub.viewModel.MainViewModel

val myModule = module {

    viewModel { MainViewModel() }
}