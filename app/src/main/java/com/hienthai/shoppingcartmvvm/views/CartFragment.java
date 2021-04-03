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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hienthai.shoppingcartmvvm.R;
import com.hienthai.shoppingcartmvvm.adapters.CartListAdapter;
import com.hienthai.shoppingcartmvvm.databinding.FragmentCartBinding;
import com.hienthai.shoppingcartmvvm.models.CartItem;
import com.hienthai.shoppingcartmvvm.viewmodels.ShopViewModel;

import java.util.List;


public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    ShopViewModel shopViewModel;

    FragmentCartBinding fragmentCartBinding;

    NavController navController;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        fragmentCartBinding = FragmentCartBinding.inflate(inflater, container, false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        CartListAdapter cartListAdapter = new CartListAdapter(this);
        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getCart().observe(getViewLifecycleOwner(),
                cartItems -> {
                    cartListAdapter.submitList(cartItems);
                    fragmentCartBinding.placeOrderButton.setEnabled(cartItems.size() > 0);

                });


        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(),
                aDouble -> fragmentCartBinding.orderTotalTextView.setText("Total: $ " + aDouble.toString()));


        fragmentCartBinding.placeOrderButton.setOnClickListener(v -> {
            navController.navigate(R.id.action_cartFragment_to_oderFragment);
        });


    }

    @Override
    public void deleteItem(CartItem cartItem) {
        shopViewModel.removeItemFromCart(cartItem);
    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {
        shopViewModel.changeQuantity(cartItem, quantity);
    }
}