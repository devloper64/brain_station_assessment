package com.example.brain_station_assessment.viewHolder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.brain_station_assessment.data.dto.response.Item
import com.example.brain_station_assessment.databinding.RepositoriesLayoutBinding
import com.example.brain_station_assessment.listeners.RecyclerRepositoriesItemListener


class RepositoriesViewHolder(private val itemBinding: RepositoriesLayoutBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: Item, recyclerItemListener: RecyclerRepositoriesItemListener, context: Context) {

        itemBinding.repoName.text=item.name
        itemBinding.ownerName.text=item.owner!!.login
        itemBinding.starCount.text=item.stargazersCount.toString()+" Star"
        itemBinding.mainContainer.setOnClickListener {
            recyclerItemListener.onItemSelected(item)
        }


    }






}