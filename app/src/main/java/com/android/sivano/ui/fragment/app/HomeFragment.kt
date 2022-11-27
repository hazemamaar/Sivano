package com.android.sivano.ui.fragment.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.android.sivano.R
import com.android.sivano.common.uitil.Resource
import com.android.sivano.common.uitil.toast
import com.android.sivano.data.local.ComplexPreferences
import com.android.sivano.databinding.FragmentHomeBinding
import com.android.sivano.domin.model.Banner
import com.android.sivano.domin.model.ProductsHomePage
import com.android.sivano.data.entities.favorite.AddOrRemoveFavoriteDto
import com.android.sivano.data.entities.shared.FavOrCartOtd
import com.android.sivano.data.entities.shared.MyResponse
import com.android.sivano.ui.adabters.BannersAdapter
import com.android.sivano.ui.adabters.CategoryRecyclerView
import com.android.sivano.ui.adabters.ProductRecyclerView
import com.android.sivano.ui.viewmodel.HomePageViewModel
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding
    private val homeViewMode: HomePageViewModel by viewModels()

    @Inject
    lateinit var complexPreferences: ComplexPreferences

    @Inject
    lateinit var categoryRecyclerView: CategoryRecyclerView

    @Inject
    lateinit var productRecyclerView: ProductRecyclerView
    var favresponse: List<com.android.sivano.data.entities.shared.MyResponse<com.android.sivano.data.entities.favorite.AddOrRemoveFavoriteDto>>? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewMode.homePage()
        homeViewMode.categories()
        getProduct()
        getCategories()
//        productRecyclerView.setOnButtonSeeDetailsClickListener { response ->
//            CustomDialog.showDialogDetails(requireContext(), response)
////            val bundle = bundleOf("product" to response)
////            findNavController().navigate(
////                R.id.action_homeFragment_to_seeDetailsProduct, bundle
////            )
//        }
        productRecyclerView.setAddToCartClickListener { response ->
            val fav = com.android.sivano.data.entities.shared.FavOrCartOtd(response.id)
            homeViewMode.addToCart(fav)
            homeViewMode.addOrRemoveCartMutableLiveData.observe(viewLifecycleOwner) { response ->
                if (response.data?.id != 0) {
                    toast("Done Add To Cart")
                }
            }

        }
        productRecyclerView.setOnImageHeartClickListener { response ->
            Log.e("response", "onViewCreated: $response")
            var fav = com.android.sivano.data.entities.shared.FavOrCartOtd(response.id)
            homeViewMode.addToFavorite(fav)
            homeViewMode.addFavoriteMutableLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Error -> {
                        hideProgress()
                        toast(it.message ?: "Error")
                    }
                    is Resource.Loading -> showProgress()
                    is Resource.Success -> {
                        hideProgress()
                        if (it.data?.id != null) {
                            productRecyclerView.updateItem(response.apply {
                                in_favorites = !in_favorites
                            })
                        }
                    }
                }

            }
//                homeViewMode.deleteFromFavorite(complexPreferences.getString("token"), deletefav)
//                homeViewMode.deleteFavoriteMutableLiveData.observe(viewLifecycleOwner, Observer {
//                    toast("done delete")
//                })
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun getBanners(listBanner: List<Banner>) {
        homeViewMode.homeMutableLiveData.observe(viewLifecycleOwner, Observer { response ->
            binding.viewpager2.adapter = BannersAdapter(listBanner)
            initIndicators()
            subscribeToViewPagerBanners()

        })
    }

    fun getProduct() {
        setUpProductRecyclerView()
        homeViewMode.homeMutableLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgress()
                    response.data?.let { homeResponse ->
                        //  toast("${homeResponse.data.banners.size}")
                        productRecyclerView.productList =
                            homeResponse.products as ArrayList<ProductsHomePage>
                        getBanners(homeResponse.banners)
                    }
                }
                is Resource.Error -> {
                    hideProgress()
                    response.message?.let { message ->
                        toast(message)
                    }
                }
                is Resource.Loading -> {
                    showProgress()

                }
                else -> {}
            }
        })
    }

    fun showProgress() {
        binding.spinKit.visibility = View.VISIBLE
    }

    fun hideProgress() {
        binding.spinKit.visibility = View.GONE
    }

    fun getCategories() {
        setUpCategoryRecyclerView()
        homeViewMode.categoriesMutableLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    // hideProgress()
                    response.data?.let { categoryResponse ->
                        categoryRecyclerView.categoryList = categoryResponse.listCategory
                    }
                }
                is Resource.Error -> {
                    // hideProgress()
                    response.message?.let { message ->
                        toast(message)
                    }
                }
                is Resource.Loading -> {
                    // ShowProgress()

                }
                else -> {}
            }
        })
    }

    private fun subscribeToViewPagerBanners() {
        binding.viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                binding.indicator.onPageScrolled(position, positionOffset, positionOffsetPixels)

            }

            override fun onPageSelected(position: Int) {
                binding.indicator.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                /*empty*/
            }
        })
    }

    private fun initIndicators() {
        binding.indicator.apply {
            setSliderColor(
                R.color.gray,
                R.color.black
            )
            setSliderWidth(20F)
            setSliderHeight(20f)
            setCheckedColor(R.color.black)
            setNormalColor(R.color.gray)
            setSlideMode(IndicatorSlideMode.SMOOTH)
            setIndicatorStyle(IndicatorStyle.ROUND_RECT)
            setPageSize(binding.viewpager2.adapter!!.itemCount)
            notifyDataChanged()
        }
    }

    private fun setUpCategoryRecyclerView() {
        binding.rvCategories.apply {
            adapter = categoryRecyclerView
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            //  addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
    }

    private fun setUpProductRecyclerView() {
        binding.rvProducts.apply {
            adapter = productRecyclerView
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            //  addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
    }

    private fun addToFavorite() {
        homeViewMode.addFavoriteMutableLiveData.observe(viewLifecycleOwner, Observer {
            toast("${it.data?.id}")
        })

    }
}