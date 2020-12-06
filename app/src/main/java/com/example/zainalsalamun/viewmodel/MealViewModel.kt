package com.example.zainalsalamun.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zainalsalamun.api.MealApi
import com.example.zainalsalamun.model.Meal
import com.example.zainalsalamun.model.MealClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel : ViewModel() {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val mealByCategory: MutableLiveData<List<MealClass>> = MutableLiveData()
    val loadError: MutableLiveData<Boolean> = MutableLiveData()

    fun getLoading() : LiveData<Boolean> = loading
    fun getMealByCategory(): LiveData<List<MealClass>> = mealByCategory
    fun getLoadingError(): LiveData<Boolean> = loadError

    private val mealApi =  MealApi()

    fun loadMeal(category: String) {
        loading.value = true
       var apiCall = mealApi.getMealByCategory(category)
        apiCall.enqueue(object : Callback<Meal>{
            override fun onFailure(call: Call<Meal>, t: Throwable) {
                loading.value = false
                loadError.value = true
            }

            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
                loadError.value = false
                var meals = response.body()?.meals ?: emptyList()
                mealByCategory.value = meals
            }
        })
    }

}