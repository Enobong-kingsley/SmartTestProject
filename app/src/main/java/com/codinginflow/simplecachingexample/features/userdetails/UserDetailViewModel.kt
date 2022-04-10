package com.codinginflow.simplecachingexample.features.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codinginflow.simplecachingexample.api.UserDetailApi
import com.codinginflow.simplecachingexample.data.SlugCategoryResponse
import com.codinginflow.simplecachingexample.data.UserDetail
import com.codinginflow.simplecachingexample.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    api : UserDetailApi
): ViewModel() {

    private val _userDetailsMutableLiveData = MutableLiveData<Resource<SlugCategoryResponse>>()
    val userDetails : LiveData<Resource<SlugCategoryResponse>> = _userDetailsMutableLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val userDetails = api.getUserDetails()
                _userDetailsMutableLiveData.postValue(Resource.Success(userDetails))

            }catch (e : Exception){
                _userDetailsMutableLiveData.postValue(Resource.Error(e.message ?: ""))
            }
        }
    }
}