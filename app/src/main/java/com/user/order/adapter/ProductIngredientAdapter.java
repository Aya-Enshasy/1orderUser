package com.user.order.adapter;

import static com.user.order.ui.activivtes.productDetails.ProductDetailsActivity.quantity;
import static com.user.order.ui.activivtes.productDetails.ProductDetailsActivity.total;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.user.order.R;
import com.user.order.listeners.CheckBoxInterface;
import com.user.order.listeners.SelectExtraItemsListener;
import com.user.order.model.cart1.CartOrder;
import com.user.order.model.shop.meals.ExtraItem;
import com.user.order.ui.activivtes.productDetails.ProductDetailsActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import net.igenius.customcheckbox.CustomCheckBox;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductIngredientAdapter extends RecyclerView.Adapter<ProductIngredientAdapter.ProductIngredientHolder> {

    private Activity activity;
    private List<ExtraItem> listIngredients;
    private List<ExtraItem> listItems;
    private LayoutInflater inflater;
    private SelectExtraItemsListener extraItemsListener;
    double calorieSum = 0, caloriequt = 0, calorieid = 0;
    boolean isCheck = false;
    CheckBoxInterface checkBoxInterface;

    public ProductIngredientAdapter(Activity activity, List<ExtraItem> listIngredients, SelectExtraItemsListener extraItemsListener,CheckBoxInterface checkBoxInterface) {
        this.activity = activity;
        this.listIngredients = listIngredients;
        this.extraItemsListener = extraItemsListener;
        this.checkBoxInterface = checkBoxInterface;
        listItems = new ArrayList<>();
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ProductIngredientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductIngredientHolder(inflater.inflate(R.layout.row_product_ingredients, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductIngredientHolder holder, @SuppressLint("RecyclerView") int position) {

        ExtraItem extraItem = listIngredients.get(position);
        if (!listIngredients.get(position).getPrice().equals("") ) {
            holder.checkBox.setText(extraItem.getName());
            holder.price.setText(extraItem.getPrice()+" "+ HelperMethods.getCurrency(activity));
            listItems.clear();
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {

                        calorieSum += Double.parseDouble(extraItem.getPrice());
                        extraItem.setQty(String.valueOf(quantity));
                        calorieid += Double.parseDouble(String.valueOf(extraItem.getId()));
                        listItems.add(extraItem);
                        isCheck = true;
                    } else {

                        calorieSum -= Double.parseDouble(extraItem.getPrice());
                        caloriequt -= quantity;
                        calorieid -= Double.parseDouble(String.valueOf(extraItem.getId()));
                        listItems.remove(extraItem);
                        isCheck = false;
                    }
                    extraItemsListener.onExtraItemsSelected(calorieSum, caloriequt, extraItem, listItems, position, isCheck);

                }
            });
        }else {
            holder.checkboxRelative.setVisibility(View.GONE);
            checkBoxInterface.onClick(true);
        }

    }

    @Override
    public int getItemCount() {
        return listIngredients.size();
    }

    static class ProductIngredientHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox)
        CheckBox checkBox;

        @BindView(R.id.checkbox_relative)
        LinearLayoutCompat checkboxRelative;

        @BindView(R.id.price)
        TextView price;

        public ProductIngredientHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
