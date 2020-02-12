package com.project.utils

import android.view.View
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.MainThreadDisposable

class ViewClickOnSubscribe(private val view: View) : ObservableOnSubscribe<View> {


    override fun subscribe(emitter: ObservableEmitter<View>) {
        val listener = View.OnClickListener {
            if (!emitter.isDisposed)
                emitter.onNext(it)
        }

        view.setOnClickListener(listener)

        emitter.setDisposable(object : MainThreadDisposable() {
            override fun onDispose() {
                view.setOnClickListener(null)
            }
        })

    }
}