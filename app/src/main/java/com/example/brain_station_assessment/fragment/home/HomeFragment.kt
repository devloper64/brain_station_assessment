package com.example.brain_station_assessment.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.example.brain_station_assessment.R
import com.example.brain_station_assessment.data.Resource
import com.example.brain_station_assessment.data.dto.reponse.RepositoriesResponse
import com.example.brain_station_assessment.databinding.FragmentHomeBinding
import com.example.brain_station_assessment.fragment.base.BaseFragment
import com.example.brain_station_assessment.utils.SingleEvent
import com.example.brain_station_assessment.utils.observe
import com.example.brain_station_assessment.utils.setupSnackbar
import com.example.brain_station_assessment.utils.showToast
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()


    override fun observeViewModel() {
        observe(homeViewModel.repositoriesLiveData, ::handleRepositoriesResult)
        observeSnackBarMessages(homeViewModel.showSnackBar)
        observeToast(homeViewModel.showToast)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getRepositories("Android","stars","desc")
    }



    private fun handleRepositoriesResult(status: Resource<RepositoriesResponse>) {

        when (status) {
            is Resource.Loading -> {
            }
            is Resource.Success -> status.data?.let {

                Log.d("-------------------->",""+ it.items!![0]!!.description)

            }
            is Resource.DataError -> {
                if (status.errorMsg!!.isNotBlank()) {
                    status.errorMsg.let { homeViewModel.showToastMessage(it) }
                } else {
                    status.errorCode?.let { homeViewModel.showToastMessage(it) }
                }
            }


        }

    }


    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }


}