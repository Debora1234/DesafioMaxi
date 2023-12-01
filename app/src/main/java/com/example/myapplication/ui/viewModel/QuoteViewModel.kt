package com.example.myapplication.ui.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.data.database.dao.QuoteDao
import com.example.myapplication.domain.GetQuotesUseCase
import com.example.myapplication.domain.model.Quote
import kotlinx.coroutines.launch


//Para que nuestra clase sea un ViewModel, tenemos que hacer que la clase creada extienda de
// dicha clase y es por ello que ponemos : ViewModel() después del nombre de la clase.

class QuoteViewModel: ViewModel() {


    private val _dogImagesLiveData = MutableLiveData<List<Quote>?>()
    val dogImagesLiveData: MutableLiveData<List<Quote>?> get() = _dogImagesLiveData

    private val _errorLiveData = MutableLiveData<String>() /*para mostrar un error si introduce un nombre de raza que no existe*/
    val errorLiveData: LiveData<String> get() = _errorLiveData


    // LiveData para observar los cambios en la lista de citas
    val quoteModel = MutableLiveData<Quote>()

    val isLoading = MutableLiveData<Boolean>() //lo usamos para mostrar/ocultar el progress



    //para ir actualizando nuestra lista de citas
    private val _suggestions = MutableLiveData<List<String>>()
    val suggestions: LiveData<List<String>> get() = _suggestions


    fun onCreate(query: String, context: Context) {                     // Función para inicializar el ViewModel con un query
       Log.d("estado ", "1 VIEWMODEL")
       var getQuotesUseCase = GetQuotesUseCase()  // Instancia del caso de uso
       Log.d("estado ", "2 VIEWMODEL")
       isLoading.postValue(true)
       viewModelScope.launch {       // Lanzamos una corrutina en el hilo principal
            Log.d("estado", "entro en try $context")
            val respuesta = getQuotesUseCase(query, context)
           Log.d("estado", "respuesta $respuesta")
            _dogImagesLiveData.postValue(respuesta)
            if(respuesta.isNullOrEmpty()){
                _errorLiveData.postValue("Error: No se encontraron imágenes para la raza '$query'")
               }
            Log.d("estado", "entro en finally")
            isLoading.postValue(false)
        }

    }

    fun updateSuggestions(query: String) {
        val newSuggestions = query.split(" ")
        _suggestions.value = newSuggestions
    }


}




//ViewModel se encarga de manejar la lógica de presentación y de exponer datos a la Vista de
// una manera que sea consciente del ciclo de vida. Además, ayuda a separar la lógica de
// presentación de la Vista, lo que facilita la prueba y la mantenibilidad del código.
// En el ejemplo proporcionado, QuoteViewModel se utiliza para gestionar citas y comunicarse
// con la Vista para actualizar la interfaz de usuario en consecuencia.