package com.example.myapplication.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.QuoteModel
import com.example.myapplication.domain.GetQuotesUseCase
import kotlinx.coroutines.launch


//Para que nuestra clase sea un ViewModel, tenemos que hacer que la clase creada extienda de
// dicha clase y es por ello que ponemos : ViewModel() después del nombre de la clase.

class QuoteViewModel : ViewModel(){
    val quoteModel = MutableLiveData<QuoteModel>()
    var getQuotesUseCase = GetQuotesUseCase()
    fun onCreate() {
        viewModelScope.launch{
            //creamos una corrutina q se controla automaticamente
            val result = getQuotesUseCase()
            if(!result.isNullOrEmpty()){
                quoteModel.postValue(result[0])
            }
        }

    }
    // el livedata, permite suscribir nuestra activity a un modelo de datos, y que se llame automaticamente cuando se realice un cambio en el modelo

    fun randomQuote() {
  //      val currentQuote = QuoteProvider.random()
  //      quoteModel.postValue(currentQuote)
    }


}


//ViewModel se encarga de manejar la lógica de presentación y de exponer datos a la Vista de
// una manera que sea consciente del ciclo de vida. Además, ayuda a separar la lógica de
// presentación de la Vista, lo que facilita la prueba y la mantenibilidad del código.
// En el ejemplo proporcionado, QuoteViewModel se utiliza para gestionar citas y comunicarse
// con la Vista para actualizar la interfaz de usuario en consecuencia.