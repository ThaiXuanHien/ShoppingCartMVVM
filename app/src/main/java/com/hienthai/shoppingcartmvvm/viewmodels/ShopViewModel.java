package com.hienthai.shoppingcartmvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hienthai.shoppingcartmvvm.models.CartItem;
import com.hienthai.shoppingcartmvvm.models.Product;
import com.hienthai.shoppingcartmvvm.repositories.CartRepo;
import com.hienthai.shoppingcartmvvm.repositories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel {


    ShopRepo shopRepo = new ShopRepo();
    CartRepo cartRepo = new CartRepo();

    private MutableLiveData<Product> mutableProduct = new MutableLiveData<>();


    public LiveData<List<Product>> getProducts() {
        return shopRepo.getProducts();
    }

    public void setProduct(Product product) {
        mutableProduct.setValue(product);
    }

    public LiveData<Product> getProduct() {
        return mutableProduct;
    }

    public LiveData<List<CartItem>> getCart() {
        return cartRepo.getCart();
    }

    public boolean addItemToCart(Product product) {
        return cartRepo.addItemToCart(product);
    }

    public void removeItemFromCart(CartItem cartItem) {
        cartRepo.removeItemFromCart(cartItem);
    }

    public void changeQuantity(CartItem cartItem, int quantity) {
        cartRepo.changeQuantity(cartItem, quantity);
    }

    public LiveData<Double> getTotalPrice() {
        return cartRepo.getTotalPrice();
    }

    public void resetCart() {
        cartRepo.initCart();
    }
}
