package hr.stjepan.example.weatherapp.presentaion.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.stjepan.example.weatherapp.data.model.Cities

class SearchViewModel : ViewModel() {

    private val mutableSelectedItem = MutableLiveData<String>()
    private val mutableSelectedCity = MutableLiveData<Cities>()
    val selectedItem: LiveData<String> get() = mutableSelectedItem
    val selectedCity: LiveData<Cities> get() = mutableSelectedCity

    fun selectedItem(item:String){
        mutableSelectedItem.value = item
    }

    fun selectedCity(item: Cities){
        mutableSelectedCity.value = item
    }

}