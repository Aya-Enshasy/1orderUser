package com.user.order.listeners;

import com.user.order.model.shop.meals.ExtraItem;

import java.util.List;

public interface SelectExtraItemsListener {

//    void onExtraItemsSelected(ExtraItem extraItem, List<ExtraItem> items, int pos, boolean isCheck);
void onExtraItemsSelected(double price,double caloriequt,ExtraItem extraItem, List<ExtraItem> items, int pos, boolean isCheck);

}
