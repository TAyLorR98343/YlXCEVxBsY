// 代码生成时间: 2025-09-17 23:59:09
package com.example.cart;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Managed bean for shopping cart functionality.
 * This class represents a shopping cart and provides methods to add, remove items, and calculate totals.
 */
@ManagedBean
@SessionScoped
public class ShoppingCart implements Serializable {

    private List<CartItem> items = new ArrayList<>();
    private double total = 0.0;

    /**
     * Adds an item to the cart.
     * 
     * @param item The item to be added.
     */
    public void addItem(CartItem item) {
        if (item != null) {
            int index = findItemIndex(item);
            if (index == -1) {
                items.add(item);
            } else {
                CartItem existingItem = items.get(index);
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            }
            recalculateTotal();
        }
    }

    /**
     * Removes an item from the cart.
     * 
     * @param item The item to be removed.
     */
    public void removeItem(CartItem item) {
        if (item != null) {
            items.remove(item);
            recalculateTotal();
        }
    }

    /**
     * Finds the index of an item in the cart.
     * 
     * @param item The item to find.
     * @return The index of the item, or -1 if not found.
     */
    private int findItemIndex(CartItem item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(item.getId())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Recalculates the total price of items in the cart.
     */
    private void recalculateTotal() {
        total = 0.0;
        for (CartItem item : items) {
            total += item.getQuantity() * item.getPrice();
        }
    }

    /**
     * Gets the total price of items in the cart.
     * 
     * @return The total price.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Gets the list of items in the cart.
     * 
     * @return The list of cart items.
     */
    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Represents an item in the shopping cart.
     */
    public static class CartItem {
        private String id;
        private String name;
        private double price;
        private int quantity;

        public CartItem(String id, String name, double price, int quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
