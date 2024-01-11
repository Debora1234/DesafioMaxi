package com.example.myapplication.data.network

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class SimuladorInterceptorQuotes() : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        // Simular una respuesta de éxito desde la API
        val simulatedResponseJsonString = """
             {
                "message": "https://images.dog.ceo/breeds/whippet/n02091134_15876.jpg",
                "status": "success"
             }
            """.trimIndent()

        // Construir una respuesta con código 200 (OK)
        return Response.Builder()
            .code(200)
            .message("OK")
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(simulatedResponseJsonString.toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }
}

