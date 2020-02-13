package com.project.network

import com.project.network.response.BaseApiResponse
import io.reactivex.functions.Consumer

class NetworkResponse(
    private var nextResponse: OnNext
) : Consumer<BaseApiResponse> {

    interface OnNext {
        fun <T: BaseApiResponse> onNext(apiResponse: T)
    }


    override fun accept(response: BaseApiResponse?) {
        response?.let {
            nextResponse.onNext(it)
            return
        }
    }
}

class NetworkListResponse(
    private var nextListResponse: OnNext
) : Consumer<List<BaseApiResponse>> {

    interface OnNext {
        fun <T: BaseApiResponse> onNext(apiResponse: List<T>)
    }


    override fun accept(response: List<BaseApiResponse>?) {
        response?.let {
            nextListResponse.onNext(it)
            return
        }
    }
}

class NetworkError(
    private var errorResponse: OnError
) : Consumer<Throwable> {

    interface OnError {
        fun onError(throwable: Throwable)
    }

    override fun accept(throwable: Throwable?) {
        throwable?.let {
            errorResponse.onError(throwable)
            return
        }
    }
}