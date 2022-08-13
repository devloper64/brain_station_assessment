package com.example.brain_station_assessment.adapter

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brain_station_assessment.data.dto.reponse.Item
import com.example.brain_station_assessment.databinding.RepositoriesLayoutBinding
import com.example.brain_station_assessment.listeners.RecyclerRepositoriesItemListener
import com.example.brain_station_assessment.viewHolder.RepositoriesViewHolder

class RepositoriesAdapter(private val recipes: MutableList<Item?>, private val recyclerItemListener: RecyclerRepositoriesItemListener,val context: Context) : RecyclerView.Adapter<RepositoriesViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val itemBinding = RepositoriesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoriesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        holder.bind(recipes[position]!!, recyclerItemListener,context)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}