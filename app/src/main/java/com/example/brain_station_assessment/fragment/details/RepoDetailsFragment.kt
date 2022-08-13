package com.example.brain_station_assessment.fragment.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.brain_station_assessment.databinding.FragmentRepoDetailsBinding
import com.example.brain_station_assessment.utils.loadImageGlideUrl
import kotlinx.android.synthetic.main.fragment_repo_details.view.*
import java.text.SimpleDateFormat


class RepoDetailsFragment : Fragment() {

    private val args: RepoDetailsFragmentArgs by navArgs()

    private lateinit var binding:FragmentRepoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentRepoDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }


        if (!args.item!!.owner!!.avatarUrl.isNullOrEmpty()){
            binding.root.profile_image.loadImageGlideUrl(args.item!!.owner!!.avatarUrl!!,requireContext())
        }

        binding.ownerName.text="Owner Name:"+args.item!!.owner!!.login
        binding.description.text="Description:"+args.item!!.description
        binding.updateDate.text="Last Update Time:"+getDateTime(args.item!!.updatedAt!!)

    }


    @SuppressLint("SimpleDateFormat")
    fun getDateTime(dateTime: String): String {

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("MM-dd-yyyy HH:mm:ss")
        return formatter.format(parser.parse(dateTime))
    }

}