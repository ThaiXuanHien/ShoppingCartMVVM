package com.hienthai.shoppingcartmvvm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.hienthai.shoppingcartmvvm.databinding.ProductItemBinding;
import com.hienthai.shoppingcartmvvm.models.Product;

public class ShopListAdapter extends ListAdapter<Product, ShopListAdapter.ShopViewHolder> {

    private ShopInterface shopInterface;

    public ShopListAdapter(ShopInterface shopInterface) {
        super(Product.itemCallback);

        this.shopInterface = shopInterface;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ProductItemBinding productItemBinding = ProductItemBinding.inflate(layoutInflater, parent, false);

        productItemBinding.setShopInterface(shopInterface);

        return new ShopViewHolder(productItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Product product = getItem(position);
        holder.productItemBinding.setProduct(product);
        holder.productItemBinding.executePendingBindings();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {

        ProductItemBinding productItemBinding;

        public ShopViewHolder(ProductItemBinding binding) {
            super(binding.getRoot());

            this.productItemBinding = binding;

        }
    }

    public interface ShopInterface {
        void addItem(Product product);

        void onItemClick(Product product);
    }
}
