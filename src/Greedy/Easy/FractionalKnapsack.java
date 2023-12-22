package Greedy.Easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Item {

    int weight;
    int profit;

    public Item(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
    }
}

class MyComparator1 implements Comparator<Item> {
    @Override
    public int compare(Item i1, Item i2) {
        Double profit_by_wt1 = (double)i1.profit / i1.weight;
        Double profit_by_wt2 = (double)i2.profit / i2.weight;

        return profit_by_wt2.compareTo(profit_by_wt1);
    }
}
public class FractionalKnapsack {
    public static void solution1(int W, List<Item> items) {
        double maximized_profit = 0.0;
        List<Item> items_inserted = new ArrayList<>();

        // 1 Sort the items in decreasing order of p/w ratio
        Collections.sort(items, new MyComparator1());

        // 2 Put each item in the Knapsack in a Greedy manner
        for(Item item : items) {
            
            // If current item can be fully inserted into the Knapsack, Insert it
            // Decrease the Knapsack capacity
            // Increase the net profit
            if (item.weight <= W) {
                W = W - item.weight;
                maximized_profit = maximized_profit + item.profit;
            }
            // Insert fractional part of the current item into the Knapsack
            // Update the net profit
            else {
                double frac_item_inserted = (double)W / item.weight;
                maximized_profit = maximized_profit + (frac_item_inserted*item.profit);
                break;
            }
        }

        System.out.println("Max profit obtained = "+maximized_profit);
    }
    public static void main(String[] args) {
        // Capacity of the Knapsack
        int W = 60;

        // Items to be inserted into the Knapsack
        List<Item> items = new ArrayList<>();
        items.add(new Item(5,30));
        items.add(new Item(10,40));
        items.add(new Item(15,45));
        items.add(new Item(22,77));
        items.add(new Item(25,90));

        // Solution 1
        solution1(W, items);
    }
}
