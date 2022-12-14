package com.example.brain_station_assessment.fragment.home

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brain_station_assessment.adapter.RepositoriesAdapter
import com.example.brain_station_assessment.data.Resource
import com.example.brain_station_assessment.data.UserPreferences
import com.example.brain_station_assessment.data.dto.response.Item
import com.example.brain_station_assessment.data.dto.response.RepositoriesResponse
import com.example.brain_station_assessment.databinding.FragmentHomeBinding
import com.example.brain_station_assessment.fragment.base.BaseFragment
import com.example.brain_station_assessment.listeners.RecyclerRepositoriesItemListener
import com.example.brain_station_assessment.utils.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    var sort=""
    var sortList = arrayOf("Stars", "Updated")
    private lateinit var userPreferences: UserPreferences

    private var backState=false

    override fun observeViewModel() {
        observe(homeViewModel.repositoriesLiveData, ::handleRepositoriesResult)
        observeSnackBarMessages(homeViewModel.showSnackBar)
        observeToast(homeViewModel.showToast)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val recyclerItemListener: RecyclerRepositoriesItemListener = object :
        RecyclerRepositoriesItemListener {
        override fun onItemSelected(item: Item) {
            backState=true
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRepoDetailsFragment(item = item))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPreferences= UserPreferences(requireContext())
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager






        val aa: ArrayAdapter<*> = ArrayAdapter<Any?>(requireContext(), R.layout.simple_spinner_item, sortList)
        aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter=aa


        runBlocking {
            val data:String=userPreferences.sortKey.first().toString()
            Log.d("------------->",data)
            if (data=="null"){
                binding.spinner.setSelection(0)
            }else{
                if (data=="stars"){
                    binding.spinner.setSelection(0)
                }else{
                    binding.spinner.setSelection(1)
                }

            }
        }



        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {



                  val data=parent?.getItemAtPosition(position) as String
                  if (data=="Stars"){
                      sort="stars"
                      runBlocking {
                          userPreferences.saveSortKey("stars")
                      }
                      if (!backState){
                          homeViewModel.getRepositories("Android",sort,"desc")
                      }



                  }else{
                      sort="updated"
                      runBlocking {
                          userPreferences.saveSortKey("updated")
                      }
                      if (!backState){
                          homeViewModel.getRepositories("Android",sort,"desc")
                      }

                  }



            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        binding.spinner.setOnTouchListener(OnTouchListener { v, event ->
            backState=false
            false
        })

    }



    private fun handleRepositoriesResult(status: Resource<RepositoriesResponse>) {

        when (status) {
            is Resource.Loading -> {


            }
            is Resource.Success -> status.data?.let {

                binding.recyclerView.adapter=RepositoriesAdapter(it.items!!.toMutableList(),recyclerItemListener,requireContext())


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