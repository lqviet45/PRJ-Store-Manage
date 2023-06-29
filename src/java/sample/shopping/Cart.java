package sample.shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class Cart {

    private Map<String, Tea> cart;

    public Map<String, Tea> getCart() {
        return cart;
    }

    public Cart() {
    }

    public Cart(Map<String, Tea> cart) {
        this.cart = cart;
    }

    public void setCart(Map<String, Tea> cart) {
        this.cart = cart;
    }

    public boolean add(Tea tea) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(tea.getId())) {
                int currentQuantity = this.cart.get(tea.getId()).getQuantity();
                tea.setQuantity(currentQuantity + tea.getQuantity());
            }
            this.cart.put(tea.getId(), tea);
            check = true;
        } catch (Exception e) {
        }

        return check;
    }

    public boolean remove(String id) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(id)) {
                    this.cart.remove(id);
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }
    
    public boolean edit(String id, Tea tea) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(id)) {
                    this.cart.replace(id, tea);
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<String, Tea> c : this.cart.entrySet()) {
            Tea tea = c.getValue();
            total += tea.getQuantity() * tea.getPrice();
        }
        return total;
    }
    
}
