package com.myaxa.network

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class CallInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //If requested endpoint matched to targeted endpoint, we will return an mocked response.

        val requestString = chain.request().url.toUri().toString()
        val responseString = when {
            requestString.endsWith("offers") -> {
                offersResponse
            }

            requestString.endsWith("tickets_offers") -> {
                ticketsOffersResponse
            }

            requestString.endsWith("tickets") -> {
                ticketsResponse
            }

            else -> return chain.proceed(chain.request())
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                responseString
                    .toByteArray()
                    .toResponseBody(
                        "application/json".toMediaTypeOrNull()
                    )
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}