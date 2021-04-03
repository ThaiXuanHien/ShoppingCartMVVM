package com.hienthai.shoppingcartmvvm.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hienthai.shoppingcartmvvm.R;
import com.hienthai.shoppingcartmvvm.databinding.FragmentOderBinding;
import com.hienthai.shoppingcartmvvm.viewmodels.ShopViewModel;


public class OderFragment extends Fragment {


    private NavController navController;

    private FragmentOderBinding fragmentOderBinding;

    ShopViewModel shopViewModel;

    public OderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentOderBinding = FragmentOderBinding.inflate(inflater, container, false);


        return fragmentOderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);



        fragmentOderBinding.continueShoppingButton.setOnClickListener(v -> {
            shopViewModel.resetCart();
            navController.navigate(R.id.action_oderFragment_to_shopFragment);
        });

    }
}