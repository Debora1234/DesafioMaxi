package com.example.myapplication.data.network
import okhttp3.*
import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody


class SimuladorInterceptorUtils() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Simular una respuesta de éxito desde la API
        val simulatedResponseJsonString = """
                {
                    "message": {
                        "wolfhound": [
                            "irish"
                        ]
                    },
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


/*
Esta clase Interceptor, es una clase que implementa la interfaz Interceptor.
Esta se ejectuta cada vez que se realiza una petición HTTP.
Dentro de este método podria hacer acciones como:
    -agregar encabezados
    -modificar el cuerpo de la respuesta
 */