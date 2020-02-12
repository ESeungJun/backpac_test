package com.project.network

import com.project.network.response.BaseApiResponse
import io.reactivex.functions.Consumer

class NetworkResponse(
    private var nextResponse: NextResponse
) : Consumer<BaseApiResponse> {

    interface NextResponse {
        fun <T: BaseApiResponse> onNext(apiResponse: T)
    }


    override fun accept(response: BaseApiResponse?) {
        response?.let {
            nextResponse.onNext(it)
            return
        }
    }
}


class NetworkError(
    private var errorResponse: ErrorResponse
) : Consumer<Throwable> {

    interface ErrorResponse {
        fun onError(throwable: Throwable)
    }

    override fun accept(throwable: Throwable?) {
        throwable?.let {
            errorResponse.onError(throwable)
            return
        }
    }
}