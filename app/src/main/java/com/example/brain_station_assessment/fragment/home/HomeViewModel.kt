package com.example.brain_station_assessment.fragment.home

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.brain_station_assessment.baseViewModel.BaseViewModel
import com.example.brain_station_assessment.data.DataRepository
import com.example.brain_station_assessment.data.Resource
import com.example.brain_station_assessment.data.dto.reponse.RepositoriesResponse
import com.example.brain_station_assessment.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val dataRepository: DataRepository) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val repositoriesLiveDataPrivate = MutableLiveData<Resource<RepositoriesResponse>>()
    val repositoriesLiveData: LiveData<Resource<RepositoriesResponse>> get() = repositoriesLiveDataPrivate


    /** Error handling as UI **/
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun getRepositories(q: String,sort:String,order:String) {

        viewModelScope.launch {
            repositoriesLiveDataPrivate.value = Resource.Loading()
            dataRepository.getRepositories(q, sort, order).collect {
                repositoriesLiveDataPrivate.value=it
            }
        }

    }



    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }

    fun showToastMessage(msg: String) {
        showToastPrivate.value = SingleEvent(msg)
    }

}
