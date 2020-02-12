package com.project.utils

import android.view.View
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

object RxView {

    private const val LOCK_TIEM = 1000L
    private val TIME_UNIT = TimeUnit.MILLISECONDS


    private fun createObservableView(view: View) = Observable.create(ViewClickOnSubscribe(view))


    fun click(view: View, onNext: (View) -> Unit): Disposable {
        return createObservableView(view)
            .throttleFirst(LOCK_TIEM, TIME_UNIT, AndroidSchedulers.mainThread())
            .subscribe(onNext)
    }


}