package com.android.sivano.ui.fragment.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sivano.R
import com.android.sivano.common.uitil.toast
import com.android.sivano.data.entities.cart.UpdateCartsOtd
import com.android.sivano.databinding.FragmentCartBinding
import com.android.sivano.databinding.FragmentFavoritesBinding
import com.android.sivano.ui.adabters.CartsRecyclerView
import com.android.sivano.ui.viewmodel.CartViewModel
import com.android.sivano.ui.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var _binding: FragmentCartBinding
    private val binding get() = _binding
    private val cartViewModel: CartViewModel by viewModels()
    @Inject
    lateinit var cartsRecyclerView:CartsRecyclerView
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpCartRecyclerView()
//        cartViewModel.cartsMutableLiveData.observe(viewLifecycleOwner, Observer {
//            if (it.data?.cart_items != null && it.data.cart_items.isNotEmpty()) {
//                binding.shimmer.visibility=View.GONE
//                cartsRecyclerView.cartList=it.data?.cart_items!!
//            }
//            binding.valueSubTotal.text="${it.data?.sub_total?.toInt()}EGP"
//            binding.valueTotal.text="${it.data?.total?.toInt()}EGP"
//        })

        binding.back.setOnClickListener {
            findNavController().popBackStack()

        }
        getAllCarts()
       cartsRecyclerView.setOnAddClickListener {
           Log.e("donefrag", "bind: done" )
       }
        cartsRecyclerView.setOnRemoveClickListener {
            Log.e("hazz", "onViewCreated:$it ", )
            cartViewModel.deleteCart(it)
            cartViewModel.deleteCartSharedFlow.onEach {
                Log.e("hazz", "onViewCreated:${it.data?.cart?.id} ", )
                if(it.data != null)
                      toast("done")

            }.launchIn(lifecycleScope)
        }
        binding.checkOutBtn.setOnClickListener {

//            cartViewModel.updateCart(it.id, UpdateCartsOtd( quantity))
//            cartViewModel.updateCartSharedFlow.onEach {
//                toast("done")
//                cartsRecyclerView.notifyDataSetChanged()
//            }.launchIn(lifecycleScope)
            findNavController().navigate(R.id.action_cartFragment_to_orderesFragmentFragment)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun setUpCartRecyclerView() {
        binding.rvCarts.apply {
            adapter = cartsRecyclerView
            layoutManager = LinearLayoutManager(activity)
            //  addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
    }
    fun getAllCarts(){
        cartViewModel.getAllCarts()
        cartViewModel.cardSharedFlow.onEach {
            if (it.data?.cart_items != null && it.data.cart_items.isNotEmpty()) {
                Log.e("getshared", "onViewCreated: "+it.data.toString() )
                binding.shimmer.visibility=View.GONE
                cartsRecyclerView.cartList=it.data?.cart_items!!
            }
            binding.valueSubTotal.text = "EGP${it.data?.sub_total?.toInt()}"
            binding.valueTotal.text = "EGP${it.data?.total?.toInt()}"
        }.launchIn(lifecycleScope)
    }
}