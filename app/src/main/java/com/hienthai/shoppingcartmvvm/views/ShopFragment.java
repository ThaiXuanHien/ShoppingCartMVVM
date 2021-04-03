package com.hienthai.shoppingcartmvvm.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.hienthai.shoppingcartmvvm.R;
import com.hienthai.shoppingcartmvvm.adapters.ShopListAdapter;
import com.hienthai.shoppingcartmvvm.databinding.FragmentShopBinding;
import com.hienthai.shoppingcartmvvm.models.Product;
import com.hienthai.shoppingcartmvvm.viewmodels.ShopViewModel;

import java.util.List;


public class ShopFragment extends Fragment implements ShopListAdapter.ShopInterface {

    private static final String TAG = "ShopFragment";

    private FragmentShopBinding fragmentShopBinding;

    private ShopListAdapter shopListAdapter;

    private ShopViewModel shopViewModel;

    private NavController navController;

    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentShopBinding = FragmentShopBinding.inflate(inflater, container, false);
        return fragmentShopBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopListAdapter = new ShopListAdapter(this);
        fragmentShopBinding.shopRecyclerView.setAdapter(shopListAdapter);


        fragmentShopBinding.shopRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        fragmentShopBinding.shopRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL));


        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getProducts().observe(getViewLifecycleOwner(), products -> shopListAdapter.submitList(products));

        navController = Navigation.findNavController(view);

    }


    @Override
    public void addItem(Product product) {
        boolean isAdded = shopViewModel.addItemToCart(product);
        if (isAdded) {
            Snackbar.make(requireView(), product.getName() + "added to cart", Snackbar.LENGTH_SHORT)
                    .setAction("Checkout", v -> {
                        navController.navigate(R.id.action_shopFragment_to_cartFragment);

                    }).show();
        } else {
            Snackbar.make(requireView(), product.getName() + "Already have the max quantity in cart", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(Product product) {
        Log.d(TAG, product.toString());

        shopViewModel.setProduct(product);

        navController.navigate(R.id.action_shopFragment_to_productDetailFragment);
    }
}