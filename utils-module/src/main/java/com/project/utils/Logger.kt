package com.project.utils

import android.util.Log

/**
 * 로그를 쉽게 관리하기위한 클래스
 *
 */
object Logger {
    private val isDebug = BuildConfig.DEBUG

    fun log(tag: String, message: String) {
        if (isDebug)
            Log.d("[$tag] ", message)
    }

    fun error(tag: String, message: String) {
        if (isDebug)
            Log.e("[$tag] ", message)
    }

    fun error(tag: String, message: String, tr: Throwable) {
        if (isDebug)
            Log.e("[$tag] ", message, tr)
    }

}
