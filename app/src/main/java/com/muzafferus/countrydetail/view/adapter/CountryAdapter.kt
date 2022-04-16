package com.muzafferus.countrydetail.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muzafferus.countrydetail.R
import com.muzafferus.countrydetail.databinding.ItemCountryBinding
import com.muzafferus.countrydetail.model.Country

class CountryAdapter :
    ListAdapter<Country, CountryViewHolder>(CountryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding: ItemCountryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_country,
            parent,
            false
        )
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.country = getItem(position)
    }
}

class CountryViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)

class CountryDiffUtil : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(
        oldItem: Country,
        newItem: Country
    ): Boolean {
        return oldItem.code == newItem.code
    }
    override fun areContentsTheSame(
        oldItem: Country,
        newItem: Country
    ): Boolean {
        return oldItem == newItem
    }
}
