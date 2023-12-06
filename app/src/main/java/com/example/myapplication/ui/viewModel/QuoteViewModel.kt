package com.example.myapplication.ui.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.data.database.dao.QuoteDao
import com.example.myapplication.domain.GetQuotesUseCase
import com.example.myapplication.domain.GetRazasUseCase
import com.example.myapplication.domain.model.Quote
import com.example.myapplication.domain.model.Raza
import kotlinx.coroutines.launch


//Para que nuestra clase sea un ViewModel, tenemos que hacer que la clase creada extienda de
// dicha clase y es por ello que ponemos : ViewModel() después del nombre de la clase.

class QuoteViewModel: ViewModel() {


    private val _dogImagesLiveData = MutableLiveData<List<Quote>?>()
    val dogImagesLiveData: MutableLiveData<List<Quote>?> get() = _dogImagesLiveData

    private val _errorLiveData = MutableLiveData<String>() /*para mostrar un error si introduce un nombre de raza que no existe*/
    val errorLiveData: LiveData<String> get() = _errorLiveData


    // LiveData para observar los cambios en la lista de citas
    private val _lidstadoDeRazasLiveData= MutableLiveData<List<Raza>?>()
    val lidstadoDeRazasLiveData: MutableLiveData<List<Raza>?> get() =  _lidstadoDeRazasLiveData


    val isLoading = MutableLiveData<Boolean>() //lo usamos para mostrar/ocultar el progress


/*
    //para ir actualizando nuestra lista de citas
    private val _suggestions = MutableLiveData<List<String>>()
    val suggestions: LiveData<List<String>> get() = _suggestions
*/

    fun listadoRazasOnCreate (context: Context) {
        var getRazasUseCase = GetRazasUseCase()  // Instancia del caso de uso
     //   isLoading.postValue(true)
        Log.d("estados", " entro al caso de uso ")
        viewModelScope.launch {
            val respuestaRazas = getRazasUseCase(context)
            Log.d("estados", " entro al caso de uso $respuestaRazas")
           _lidstadoDeRazasLiveData.postValue(respuestaRazas)
          //  isLoading.postValue(false)
        }
    }
    fun onCreateImagenes(query: String, context: Context) {                     // Función para inicializar el ViewModel con un query
       var getQuotesUseCase = GetQuotesUseCase()  // Instancia del caso de uso
       isLoading.postValue(true)
       viewModelScope.launch {       // Lanzamos una corrutina en el hilo principal
           val respuesta = getQuotesUseCase(query, context)
           Log.d("estados3", " entro al caso de uso $respuesta")
            _dogImagesLiveData.postValue(respuesta)
            if(respuesta.isNullOrEmpty()){
               _errorLiveData.postValue("Error: No se encontraron imágenes para la raza '$query'")
              }
            isLoading.postValue(false)
        }
    }


/*
    fun updateSuggestions(query: String) {
        val newSuggestions = query.split(" ")
        _suggestions.value = newSuggestions
    }
*/

}




//ViewModel se encarga de manejar la lógica de presentación y de exponer datos a la Vista de
// una manera que sea consciente del ciclo de vida. Además, ayuda a separar la lógica de
// presentación de la Vista, lo que facilita la prueba y la mantenibilidad del código.
// En el ejemplo proporcionado, QuoteViewModel se utiliza para gestionar citas y comunicarse
// con la Vista para actualizar la interfaz de usuario en consecuencia.