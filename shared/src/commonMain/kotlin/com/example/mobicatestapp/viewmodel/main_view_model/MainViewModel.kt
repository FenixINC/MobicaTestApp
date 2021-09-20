package com.example.mobicatestapp.viewmodel.main_view_model

import com.example.mobicatestapp.viewmodel.base.BaseVM

class MainViewModel :
    BaseVM<MainContract.MainEvent, MainContract.MainState, MainContract.MainEffect>() {

    override fun setInitialState() = MainContract.MainState(globalData = "")

    override fun handleEvents(event: MainContract.MainEvent) {
        when (event) {

        }
    }
}