package com.hienthai.shoppingcartmvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.shoppingcartmvvm.databinding.CartItemBinding;
import com.hienthai.shoppingcartmvvm.models.CartItem;

public class CartListAdapter extends ListAdapter<CartItem, CartListAdapter.ViewHolder> {

    CartInterface cartInterface;

    public CartListAdapter(CartInterface cartInterface) {
        super(CartItem.itemCallback);

        this.cartInterface = cartInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CartItemBinding cartItemBinding = CartItemBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(cartItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cartItemBinding.setCartItem(getItem(position));
        holder.cartItemBinding.executePendingBindings();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CartItemBinding cartItemBinding;

        public ViewHolder(CartItemBinding cartItemBinding) {
            super(cartItemBinding.getRoot());

            this.cartItemBinding = cartItemBinding;


            cartItemBinding.deleteProductButton.setOnClickListener(v -> {
                cartInterface.deleteItem(getItem(getAdapterPosition()));
            });


            cartItemBinding.quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int quantity = position + 1;
                    if (quantity == getItem(getAdapterPosition()).getQuantity()) {
                        return;
                    }
                    cartInterface.changeQuantity(getItem(getAdapterPosition()), quantity);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }


    }

    public interface CartInterface {
        void deleteItem(CartItem cartItem);

        void changeQuantity(CartItem cartItem, int quantity);
    }
}
