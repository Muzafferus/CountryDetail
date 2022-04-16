package com.muzafferus.countrydetail.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.muzafferus.countrydetail.R
import com.muzafferus.countrydetail.databinding.FragmentCountryDetailBinding
import com.muzafferus.countrydetail.view.state.ViewState
import com.muzafferus.countrydetail.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CountryDetailFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailBinding
    private val args: CountryDetailFragmentArgs by navArgs()
    private val viewModel by viewModels<CountryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCountryDetail(args.id)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.country.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    binding.characterDetailsFetchProgress.visibility = View.VISIBLE
                    binding.characterDetailsNotFound.visibility = View.GONE
                }
                is ViewState.Success -> {
                    if (response.value == null) {
                        binding.characterDetailsFetchProgress.visibility = View.GONE
                        binding.characterDetailsNotFound.visibility = View.VISIBLE
                    } else {
                        binding.country = response.value
                        binding.characterDetailsFetchProgress.visibility = View.GONE
                        binding.characterDetailsNotFound.visibility = View.GONE
                    }
                }
                is ViewState.Error -> {
                    binding.characterDetailsFetchProgress.visibility = View.GONE
                    binding.characterDetailsNotFound.visibility = View.VISIBLE
                }
            }
        }
    }
}