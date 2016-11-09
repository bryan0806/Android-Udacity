/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 *
 */
package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    int numberOfCoffees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);
        display(numberOfCoffees);
        //displayPrice(priceMessage);
        displayMessage(priceMessage);
    }

    public void increment(View view){

        numberOfCoffees += 1;
        display(numberOfCoffees);
    }

    public void decrement(View view){
        numberOfCoffees -= 1;
        display(numberOfCoffees);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private int calculatePrice(){
        int price = numberOfCoffees * 5;
        return price;
    }

    private String createOrderSummary(int priceOfOrder){
        String orderSummary = "Name: Kaptain Kunal\n" + "Quantity: " + numberOfCoffees + "\nTotal: " + priceOfOrder
 + "\nThank you!";
        return orderSummary;
    }
}