/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 *
 */
package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    int numberOfCoffees = 0;
    //Boolean hasWhippedCream = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText name_field = (EditText) findViewById(R.id.name_edit);
        String username = name_field.getText().toString();


        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean hasWhippedCream = whippedCream.isChecked();
        boolean hasChocolate = chocolate.isChecked();
        //Log.v("MainActivity", "Has the whipped cream: "+ hasWhippedCream);

        int price = calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage = createOrderSummary(price,hasWhippedCream,hasChocolate,username);
        display(numberOfCoffees);
        //displayPrice(priceMessage);
        //displayMessage(priceMessage);
        String subjectMessage = "Coffee Order from " + username;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subjectMessage);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        //intent.putExtra(Intent.EXTRA_STREAM, attachment);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view){
        numberOfCoffees += 1;
        if(numberOfCoffees > 100){
            numberOfCoffees = 100;
            display(numberOfCoffees);
            Toast.makeText(this, "Order number can not be over 100 !", Toast.LENGTH_SHORT).show();
        }else {

            display(numberOfCoffees);
        }
    }

    public void decrement(View view){
        numberOfCoffees -= 1;
        if(numberOfCoffees < 1 ){
            numberOfCoffees = 1;
            display(numberOfCoffees);
            Toast.makeText(this, "Order number can not be less than 1 !", Toast.LENGTH_SHORT).show();
        }else {
            display(numberOfCoffees);
        }
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

    private int calculatePrice(boolean addCream,boolean addChocolate){
        int price0fEachCup = 5;
        if(addCream){
            price0fEachCup += 1;
        }
        if(addChocolate){
            price0fEachCup +=2;
        }

        int price = numberOfCoffees * price0fEachCup;
        return price;
    }

    /**
     *
     * @param priceOfOrder : price of the order
     * @param addWhippedCream : add whipped cream or not
     * @param addChocolate : add chocolate or not
     *                     @param name      foruser
     *
     * @return : order Summary text
     */

    private String createOrderSummary(int priceOfOrder,Boolean addWhippedCream,boolean addChocolate,String nameforuser){
        String orderSummary = "Name: " + nameforuser + "\n" + "Has the whipped cream: "+ addWhippedCream + "\nAdd Chocolate ?" + addChocolate + "\nQuantity: " + numberOfCoffees + "\nTotal: " + priceOfOrder
 + "\nThank you!";
        return orderSummary;
    }
}