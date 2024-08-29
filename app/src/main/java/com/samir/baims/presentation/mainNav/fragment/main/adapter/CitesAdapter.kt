package com.samir.baims.presentation.mainNav.fragment.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samir.baims.presentation.mainNav.fragment.main.clickEvent.CitiesClickListener
import com.samir.baims.R
import com.samir.baims.databinding.ItemCityBinding
import com.samir.baims.domain.model.remote.main.Cities

class CitesAdapter(private var originalList: List<Cities.City> = listOf()) : RecyclerView.Adapter<CitesAdapter.ViewHolder>() {

    private var filteredList: MutableList<Cities.City> = originalList.toMutableList()
    private var selectedBranch: Cities.City? = null
    var cityItemClickListener: CitiesClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemCityBinding = ItemCityBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredList[position] )
    }

    fun submitMyList(newList: List<Cities.City>, clickListener: CitiesClickListener) {
        originalList = newList
        filteredList = newList.toMutableList()
        this.cityItemClickListener = clickListener
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            originalList.toMutableList()
        } else {
            originalList.filter {
                it.cityNameEn.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }

    fun getSelectedBranch(): Cities.City? {
        return selectedBranch
    }

    inner class ViewHolder(private val bindingItem: ItemCityBinding) : RecyclerView.ViewHolder(bindingItem.root) {

        fun bind(branch: Cities.City ) {
            bindingItem.tvBranchValue.text = branch.cityNameEn


            bindingItem.root.setOnClickListener {
                val previousSelectedPosition = selectedBranch
                selectedBranch = branch
                notifyItemChanged(filteredList.indexOf(previousSelectedPosition))
                notifyItemChanged(adapterPosition)
                cityItemClickListener?.onItemClicked(branch)
            }
        }
    }
}
