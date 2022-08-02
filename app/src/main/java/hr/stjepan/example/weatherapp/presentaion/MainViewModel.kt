package hr.stjepan.example.weatherapp.presentaion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val mutableSelectedItem = MutableLiveData<Pair<String, String>>()
    val selectedItem: LiveData<Pair<String,String>> get() = mutableSelectedItem

    fun selectItem(item: String, item1: String) {
        val items = Pair(item,item1)
        mutableSelectedItem.value = items
    }

}