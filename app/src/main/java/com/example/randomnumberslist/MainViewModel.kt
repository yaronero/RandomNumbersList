package com.example.randomnumberslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val _randomNumberList = MutableLiveData<List<Int>>()
    val randomNumberList: LiveData<List<Int>>
        get() = _randomNumberList

    init {
        getNumbersWithThread()

//        getNumbersWithCoroutine()
//
//        getNumbersWithRx()
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                val list = _randomNumberList.value?.toMutableList() ?: mutableListOf()
//                list.add(it)
//                _randomNumberList.value = list
//            }
    }

    private fun getNumbersWithCoroutine() {
        viewModelScope.launch {
            while (true) {
                delay(500)
                val list = _randomNumberList.value?.toMutableList() ?: mutableListOf()
                list.add(Random.nextInt(100))
                _randomNumberList.value = list
            }
        }
    }

    private fun getNumbersWithRx(): Observable<Int> {
        return Observable.create {
            while (true) {
                Thread.sleep(500)
                it.onNext(Random.nextInt(100))
            }
        }
    }

    private fun getNumbersWithThread() {
        val thread = Thread {
            while (true) {
                Thread.sleep(500)
                val list = _randomNumberList.value?.toMutableList() ?: mutableListOf()
                list.add(Random.nextInt(100))
                _randomNumberList.postValue(list)
            }
        }
        thread.start()
    }
}