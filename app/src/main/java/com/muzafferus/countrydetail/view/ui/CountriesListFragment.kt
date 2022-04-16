package com.muzafferus.countrydetail.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.muzafferus.countrydetail.databinding.FragmentCountriesListBinding
import com.muzafferus.countrydetail.view.adapter.CountryAdapter
import com.muzafferus.countrydetail.view.state.ViewState
import com.muzafferus.countrydetail.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CountriesListFragment : Fragment() {

    private lateinit var binding: FragmentCountriesListBinding
    private val countryAdapter by lazy { CountryAdapter() }
    private val viewModel by viewModels<CountryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountriesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.countriesRv.adapter = countryAdapter
        viewModel.getCountriesList()
        observeLiveData()

        countryAdapter.onItemClicked = { country ->
            country.let {
                if (country.code.isNotBlank()) {
                    findNavController().navigate(
                        CountriesListFragmentDirections.actionCountriesListFragmentToCountryDetailFragment(
                            id = country.code
                        )
                    )
                }
            }
        }
    }

    private fun observeLiveData() {
        viewModel.countriesList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    binding.countriesRv.visibility = View.GONE
                    binding.charactersFetchProgress.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    if (response.value?.size == 0) {
                        countryAdapter.submitList(emptyList())
                        binding.charactersFetchProgress.visibility = View.GONE
                        binding.countriesRv.visibility = View.GONE
                        binding.charactersEmptyText.visibility = View.VISIBLE
                    } else {
                        binding.countriesRv.visibility = View.VISIBLE
                        binding.charactersEmptyText.visibility = View.GONE
                    }
                    val results = response.value
                    countryAdapter.submitList(results)
                    binding.charactersFetchProgress.visibility = View.GONE
                }
                is ViewState.Error -> {
                    countryAdapter.submitList(emptyList())
                    binding.charactersFetchProgress.visibility = View.GONE
                    binding.countriesRv.visibility = View.GONE
                    binding.charactersEmptyText.visibility = View.VISIBLE
                }
            }
        }
    }

}