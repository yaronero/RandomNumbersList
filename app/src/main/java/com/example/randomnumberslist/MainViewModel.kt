package com.example.randomnumberslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val _randomNumberList = MutableLiveData<List<Int>>()
    val randomNumberList: LiveData<List<Int>>
        get() = _randomNumberList

    private var disposes = CompositeDisposable()

    private var thread: Thread? = null

    init {
        getNumbersWithThread()

//        getNumbersWithCoroutine()

//        disposes.add(
//            getNumbersWithRx()
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                val list = _randomNumberList.value ?: emptyList()
//                _randomNumberList.value = list + it
//            }, {
//
//            })
//        )
    }

    private fun getNumbersWithCoroutine() {
        viewModelScope.launch(Dispatchers.IO){
            while (true) {
                delay(500)
                val list = _randomNumberList.value ?: emptyList()
                withContext(Dispatchers.Main) {
                    _randomNumberList.value = list + Random.nextInt(100)
                }
            }
        }
    }

    private fun getNumbersWithRx(): Observable<Int> {
       return Observable.interval(1, TimeUnit.SECONDS)
            .map {
                Random.nextInt(100)
            }
    }

    private fun getNumbersWithThread() {
        thread = Thread {
            while (true) {
                Thread.sleep(500)
                val list = _randomNumberList.value ?: emptyList()
                _randomNumberList.postValue(list + Random.nextInt(100))
            }
        }
        thread?.start()
    }

    override fun onCleared() {
        super.onCleared()
        disposes.clear()
        thread?.interrupt()
    }
}