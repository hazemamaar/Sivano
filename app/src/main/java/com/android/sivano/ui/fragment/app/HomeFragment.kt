package com.android.sivano.ui.fragment.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.android.sivano.R
import com.android.sivano.data.local.ComplexPreferences
import com.android.sivano.databinding.FragmentHomeBinding
import com.android.sivano.entities.AddOrRemoveFavoriteDto
import com.android.sivano.entities.Fav
import com.android.sivano.entities.MyResponse
import com.android.sivano.ui.adabters.BannersAdapter
import com.android.sivano.ui.adabters.CategoryRecyclerView
import com.android.sivano.ui.adabters.ProductRecyclerView
import com.android.sivano.ui.viewmodel.HomePageViewModel
import com.android.sivano.common.uitil.Resource
import com.android.sivano.common.uitil.toast
import com.android.sivano.domin.model.Banner
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
    var favresponse: List<MyResponse<AddOrRemoveFavoriteDto>>? = null
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
            val fav = Fav(response.id)
            homeViewMode.addToCart(fav)
            homeViewMode.addOrRemoveCartMutableLiveData.observe(viewLifecycleOwner,
                Observer { response ->
                    if (response.data?.id != 0) {
                        toast("Done Add To Cart")
                    }
                })

        }
        productRecyclerView.setOnImageHeartClickListener { response ->
            var fav: Fav = Fav(response.id)
            if (response.in_favorites) {
                homeViewMode.addToFavorite(fav)
                homeViewMode.addFavoriteMutableLiveData.observe(viewLifecycleOwner, Observer {
                    if(it.data?.id !=null)
                          toast("${it.data?.id}"+"delete")
                })
//                homeViewMode.deleteFromFavorite(complexPreferences.getString("token"), deletefav)
//                homeViewMode.deleteFavoriteMutableLiveData.observe(viewLifecycleOwner, Observer {
//                    toast("done delete")
//                })
            } else {
                homeViewMode.addToFavorite(fav)
                homeViewMode.addFavoriteMutableLiveData.observe(viewLifecycleOwner, Observer {
                    if(it.data?.id !=null)
                        toast("${it.data?.id}"+"add")

                })
            }
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
                        productRecyclerView.productList = homeResponse.products
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
    fun showProgress(){
        binding.spinKit.visibility=View.VISIBLE
    }
    fun hideProgress(){
        binding.spinKit.visibility=View.GONE
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