package com.android.sivano.ui.fragment.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sivano.R
import com.android.sivano.databinding.FragmentCartBinding
import com.android.sivano.databinding.FragmentFavoritesBinding
import com.android.sivano.ui.adabters.CartsRecyclerView
import com.android.sivano.ui.viewmodel.CartViewModel
import com.android.sivano.ui.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var _binding: FragmentCartBinding
    private val binding get() = _binding
    private val cartViewModel: CartViewModel by viewModels()
    @Inject
    lateinit var cartsRecyclerView:CartsRecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel.getAllCarts()
        setUpCartRecyclerView()
        cartViewModel.cartsMutableLiveData.observe(viewLifecycleOwner, Observer {
            if (it.data?.cart_items != null && it.data.cart_items.isNotEmpty()) {
                cartsRecyclerView.cartList=it.data?.cart_items!!
            }
            binding.valueSubTotal.text="${it.data?.sub_total?.toInt()}EGP"
            binding.valueTotal.text="${it.data?.total?.toInt()}EGP"
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding =FragmentCartBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    private fun setUpCartRecyclerView() {
        binding.rvCarts.apply {
            adapter = cartsRecyclerView
            layoutManager = LinearLayoutManager(activity)
            //  addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
    }
}