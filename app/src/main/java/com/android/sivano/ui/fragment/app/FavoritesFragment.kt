package com.android.sivano.ui.fragment.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sivano.data.local.ComplexPreferences
import com.android.sivano.databinding.FragmentFavoritesBinding
import com.android.sivano.ui.adabters.FavoriteRecyclerView
import com.android.sivano.ui.viewmodel.FavoriteViewModel
import com.android.sivano.common.uitil.toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var _binding: FragmentFavoritesBinding
    private val binding get() = _binding
    private val favViewModel:FavoriteViewModel by viewModels()
    @Inject
    lateinit var complexPreferences: ComplexPreferences
     @Inject
     lateinit var favoriteRecyclerView:FavoriteRecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favViewModel.getFavorites(complexPreferences.getString("token"))
        setUpFavoriteRecyclerView()
        favViewModel.favoriteMutableLiveData.observe(viewLifecycleOwner, Observer {
            toast("${it.data?.data?.current_page}")
            favoriteRecyclerView.favoriteList= it.data?.data?.favoriteData!!
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root

    }

    private fun setUpFavoriteRecyclerView(){
        binding.rvFavorites.apply {
            adapter = favoriteRecyclerView
            layoutManager = LinearLayoutManager(activity)
            //  addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
    }
 }