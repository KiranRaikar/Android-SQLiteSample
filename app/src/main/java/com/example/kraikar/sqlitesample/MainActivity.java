package com.example.kraikar.sqlitesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PRODUCT_TAG";

    private EditText editTextInput;
    private TextView textViewOutput;
    private MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextInput = (EditText) findViewById(R.id.editTextInput);
        textViewOutput = (TextView) findViewById(R.id.textViewOutput);
        try {
            //dbHandler = new MyDBHandler(this, null, null, 1);
            //listItems();
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Log.i(TAG, errors.toString());
        }
        Log.i(TAG, "Product App Started...");
    }

    // To print all items in the database
    public void listItems() {
        try {
            String dbString = dbHandler.getAllItemsFromDatabase();
            textViewOutput.setText(dbString);
            editTextInput.setText("");
            Log.i(TAG, "Invoked: List Items Method. ");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Add a product to the database
    public void addButtonClicked(View view) {
        try {
            Products product = new Products(editTextInput.getText().toString());
            dbHandler.addItem(product);
            Log.i(TAG, "Invoked: Add Item To Database. ");
            listItems();
            showToast("Successfully Added.");
        } catch (Exception e){
            e.printStackTrace();
            showToast("Error adding the item!");
        }
    }

    // Remove a product from the database
    public void deleteButtonClicked(View view) {
        String productName = editTextInput.getText().toString();
        dbHandler.removeItem(productName);
        Log.i(TAG, "Invoked: Delete Item From Database. ");
        listItems();
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
