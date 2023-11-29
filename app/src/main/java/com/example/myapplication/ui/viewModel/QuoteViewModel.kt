package com.example.myapplication.ui.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.myapplication.data.model.QuoteModel
import com.example.myapplication.domain.GetQuotesUseCase
import com.example.myapplication.domain.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//Para que nuestra clase sea un ViewModel, tenemos que hacer que la clase creada extienda de
// dicha clase y es por ello que ponemos : ViewModel() después del nombre de la clase.

class QuoteViewModel(application: Application) : AndroidViewModel(application) {

    private val _dogImagesLiveData = MutableLiveData<List<String>>()
    val dogImagesLiveData: LiveData<List<String>> get() = _dogImagesLiveData

    private val _errorLiveData = MutableLiveData<String>() /*para mostrar un error si introduce un nombre de raza que no existe*/
    val errorLiveData: LiveData<String> get() = _errorLiveData

    val isLoading = MutableLiveData<Boolean>() //lo usamos para mostrar/ocultar el progress

    private var getQuotesUseCase = GetQuotesUseCase(application)   // Instancia del caso de uso
    fun onCreate(query: String) {                     // Función para inicializar el ViewModel con un query
       isLoading.postValue(true)
       viewModelScope.launch {       // Lanzamos una corrutina en el hilo principal
            Log.d("estado", "entro en try")
           val quotes = getQuotesUseCase(query)
           val images = quotes.flatMap { it.message }
           _dogImagesLiveData.postValue(images)
            if(images.isNullOrEmpty()){
                _errorLiveData.postValue("Error: No se encontraron imágenes para la raza '$query'")
                }
            Log.d("estado", "entro en finally")
            isLoading.postValue(false)
        }

    }
}




//ViewModel se encarga de manejar la lógica de presentación y de exponer datos a la Vista de
// una manera que sea consciente del ciclo de vida. Además, ayuda a separar la lógica de
// presentación de la Vista, lo que facilita la prueba y la mantenibilidad del código.
// En el ejemplo proporcionado, QuoteViewModel se utiliza para gestionar citas y comunicarse
// con la Vista para actualizar la interfaz de usuario en consecuencia.