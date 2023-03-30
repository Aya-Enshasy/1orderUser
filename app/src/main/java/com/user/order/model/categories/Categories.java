package com.user.order.model.categories;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Categories implements Serializable {

    @SerializedName("categories")
    public List<CategoryData> categories;

    public List<CategoryData> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryData> categories) {
        this.categories = categories;
    }
}
