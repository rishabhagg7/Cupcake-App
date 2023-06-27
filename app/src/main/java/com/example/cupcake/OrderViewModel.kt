package com.example.cupcake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OrderViewModel: ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>()
    val flavor:LiveData<String> = _flavor

    private val _date = MutableLiveData<String>()
    val date:LiveData<String> = _date

    private val _price =MutableLiveData<Double>()
    val price:LiveData<Double> = _price

    val dateOptions = getPickOptions()

    init {
        resetOrder()
    }

    fun setQuantity(numberCupcakes: Int){
        _quantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor:String){
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate:String){
        _date.value = pickupDate
    }

    fun hasNoFlavorSet():Boolean{
        return _flavor.value.isNullOrEmpty()
    }

    fun getPickOptions():List<String>{
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale("en","in"))
        val calendar = Calendar.getInstance()
        repeat(4){
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE,1)
        }
        return options
    }

    fun resetOrder(){
        setQuantity(0)
        setFlavor(R.string.vanilla.toString())
        setDate(dateOptions[0])
        _price.value = 0.0
    }
}