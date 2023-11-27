package com.example.myapplication.ui.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.myapplication.data.model.QuoteModel
import com.example.myapplication.domain.GetQuotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//Para que nuestra clase sea un ViewModel, tenemos que hacer que la clase creada extienda de
// dicha clase y es por ello que ponemos : ViewModel() después del nombre de la clase.

class QuoteViewModel : ViewModel() {

    private val _dogImagesLiveData = MutableLiveData<List<String>>()
    val dogImagesLiveData: LiveData<List<String>> get() = _dogImagesLiveData

    /*para mostrar un error si introduce un nombre de raza que no existe*/
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData
    /*fin para mostrar un error si introduce un nombre que no existe*/

    // LiveData para observar los cambios en la lista de citas
    val quoteModel = MutableLiveData<QuoteModel?>()
    val isLoading = MutableLiveData<Boolean>() //lo usamos para mostrar/ocultar el progress


    // LiveData para manejar mensajes de error
    val errorMessage = MutableLiveData<String>()

    private var getQuotesUseCase = GetQuotesUseCase()   // Instancia del caso de uso
    fun onCreate(query: String) {                     // Función para inicializar el ViewModel con un query
        Log.d("estado ", "2) view model")
        viewModelScope.launch {       // Lanzamos una corrutina en el hilo principal
            isLoading.postValue(true)           //cargo el loding
                Log.d("estado", "entro en try")
                val images = getQuotesUseCase(query)
                _dogImagesLiveData.postValue(images)
                if(images.isNullOrEmpty()){
                    Log.d("estado", "valor de images $images")
                    Log.d("estado", "entro en exception")
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