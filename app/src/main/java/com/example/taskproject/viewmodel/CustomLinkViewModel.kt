package com.example.taskproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskproject.model.CustomUrl

class CustomLinkViewModel: ViewModel() {
    private val _customUrls = MutableLiveData<List<CustomUrl>>(listOf(
        CustomUrl("https://pngimg.com/uploads/amazon/amazon_PNG5.png", "Amazon", "Rs 5000"),
        CustomUrl("https://www.freelogovectors.net/wp-content/uploads/2023/01/myntra-logo-freelogovectors.net_.png", "Myntra", "Rs 3000"),
        CustomUrl("https://logos-world.net/wp-content/uploads/2020/11/Flipkart-Emblem.png", "Flipkart", "Rs 4000"),
        CustomUrl("https://cdn.freelogovectors.net/wp-content/uploads/2023/11/meesho-logo-01_freelogovectors.net_.png", "Meesho", "Rs 2000"),
    ))
    val customUrls: LiveData<List<CustomUrl>> = _customUrls

    fun setCustomUrls(urls: List<CustomUrl>) {
        _customUrls.value = urls
    }

    fun addCustomUrl(url: CustomUrl) {
        val currentUrls = _customUrls.value.orEmpty().toMutableList()
        currentUrls.add(url)
        _customUrls.value = currentUrls
    }

}