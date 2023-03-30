package com.user.order.listeners;

import com.user.order.model.categories.CategoryData;

public interface SelectCategoryListener {

    void onSelectCategory(CategoryData category, int position);
}
