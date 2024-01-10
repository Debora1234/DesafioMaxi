package com.example.myapplication.data.network
import okhttp3.*
import android.content.Context
import com.example.myapplication.data.modelApi.ListaRazasModel
import com.example.myapplication.domain.model.Raza
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody



class SimuladorInterceptorUtils(private val context: Context) : Interceptor {

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

        val gson = Gson()
        // convertimos la cadena de string en un objeto Json
        val simulatedResponseJsonObject = gson.fromJson(simulatedResponseJsonString, ListaRazasModel::class.java)

        // Construir una respuesta con código 200 (OK)
        return Response.Builder()
            .code(200)
            .message(simulatedResponseJsonString)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(ResponseBody.create("application/json".toMediaTypeOrNull(), simulatedResponseJsonString))
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