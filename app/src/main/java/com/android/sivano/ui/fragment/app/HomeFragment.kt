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
import com.android.sivano.common.dialog.CustomDialog
import com.android.sivano.data.local.ComplexPreferences
import com.android.sivano.databinding.FragmentHomeBinding
import com.android.sivano.model.AddorRemoveFavoriteDto
import com.android.sivano.model.Fav
import com.android.sivano.model.MyResponse
import com.android.sivano.ui.adabters.BannersAdapter
import com.android.sivano.ui.adabters.CategoryRecyclerView
import com.android.sivano.ui.adabters.ProductRecyclerView
import com.android.sivano.ui.viewmodel.HomePageViewModel
import com.android.sivano.common.uitil.Resource
import com.android.sivano.common.uitil.toast
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
    var favresponse:List<MyResponse<AddorRemoveFavoriteDto>> ?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewMode.homePage(complexPreferences.getString("token"))
        homeViewMode.categories()
        getBanners()
        getCategories()
        getProduct()
        productRecyclerView.setOnButtonSeeDetailsClickListener { response ->
            CustomDialog.showDialogDetails(requireContext(),response)
//            val bundle = bundleOf("product" to response)
//            findNavController().navigate(
//                R.id.action_homeFragment_to_seeDetailsProduct, bundle
//            )
        }
        productRecyclerView.setAddToCartClickListener {
            response->
            val fav=Fav(response.id)
            homeViewMode.addToCart(fav,complexPreferences.getString("token"))
            homeViewMode.addOrRemoveCartMutableLiveData.observe(viewLifecycleOwner, Observer {
                response->
                if(response.data?.id!=0){
                    toast("Done Add To Cart")
                }
            })

        }
        productRecyclerView.setOnImageHeartClickListener { response ->
            if(response.in_favorites) {
                var fav: Fav = Fav(response.id)
                var deletefav: Int = 1
                homeViewMode.addToFavorite(complexPreferences.getString("token"),fav)

                homeViewMode.addFavoriteMutableLiveData.observe( viewLifecycleOwner, Observer {
                    toast("${it.data?.data?.id}")
                    deletefav = it.data?.data?.id!!
                })
                homeViewMode.deleteFromFavorite(complexPreferences.getString("token"),deletefav)
                homeViewMode.deleteFavoriteMutableLiveData.observe(viewLifecycleOwner, Observer {
                    toast("done delete")
                })
            }else{
            var fav: Fav = Fav(response.id)
            homeViewMode.addToFavorite(complexPreferences.getString("token"),fav)
            addToFavorite()
            }
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    fun getBanners(){
        homeViewMode.homeMutableLiveData.observe(viewLifecycleOwner, Observer { response ->
            toast("${response.data?.status}")
            when (response) {
                is Resource.Success -> {
                    // hideProgress()
                    response.data?.let { homeResponse ->
                      //  toast("${homeResponse.data.banners.size}")
                        binding.viewpager2.adapter = BannersAdapter(homeResponse.data.banners)
                        initIndicators()
                        subscribeToViewPagerBanners()
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
                    toast("loading")
                }
                else -> {}
            }
        })
    }
    fun getProduct(){
        setUpProductRecyclerView()
        homeViewMode.homeMutableLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    // hideProgress()
                    response.data?.let { homeResponse ->
                        //  toast("${homeResponse.data.banners.size}")
                        productRecyclerView.productList = homeResponse.data.products
                        toast("${homeResponse.data.products.get(homeResponse.data.products.size - 1 ).discount}")
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
                    toast("loading")
                }
                else -> {}
            }
        })
    }
    fun getCategories(){
        setUpCategoryRecyclerView()
        homeViewMode.categoriesMutableLiveData.observe(viewLifecycleOwner, Observer {
            response->
            when (response) {
                is Resource.Success -> {
                    // hideProgress()
                    response.data?.let { homeResponse ->
                        categoryRecyclerView.categoryList =homeResponse.data.data
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
                    toast("loading")
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
                positionOffsetPixels: Int
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
    private fun setUpCategoryRecyclerView(){
        binding.rvCategories.apply {
            adapter = categoryRecyclerView
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
          //  addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
    }
    private fun setUpProductRecyclerView(){
        binding.rvProducts.apply {
            adapter = productRecyclerView
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            //  addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
    }
    private fun addToFavorite(){
            homeViewMode.addFavoriteMutableLiveData.observe( viewLifecycleOwner, Observer {
                toast("${it.data?.data?.id}")

            })

    }
}