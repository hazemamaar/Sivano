package com.android.sivano.domin.model

import com.android.sivano.entities.CategoriesDto

data class CategoryModel(
    val current_page: Int,
    val listCategory: List<CategoryItemModel>,
    val first_page_url: String,


)
