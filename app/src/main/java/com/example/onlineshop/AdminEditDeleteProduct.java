package com.example.onlineshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.nio.file.Files;

public class AdminEditDeleteProduct extends AppCompatActivity {

    EditText pname, pdescription, pprice;
    Button updateBtn, deleteBtn;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_delete_product);

        pname = (EditText) findViewById(R.id.productname_editText);
        pdescription = (EditText) findViewById(R.id.discription_editText);
        pprice = (EditText) findViewById(R.id.price_editText);
        updateBtn = (Button) findViewById(R.id.update_btn);
        deleteBtn = (Button) findViewById(R.id.delete_btn);

        firebaseDatabase = FirebaseDatabase.getInstance();

        final String productCode = getIntent().getStringExtra("product_code").toString();
        String productName = getIntent().getStringExtra("product_name").toString();
        String productDescription = getIntent().getStringExtra("product_description").toString();
        String productPrice = getIntent().getStringExtra("product_price").toString();

        pname.setText(productName);
        pdescription.setText(productDescription);
        pprice.setText(productPrice);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textValidation()){
                    try {
                        firebaseDatabase.getReference().child("Products").child(productCode).child("pname").setValue(pname.getText().toString());
                        firebaseDatabase.getReference().child("Products").child(productCode).child("description").setValue(pdescription.getText().toString());
                        firebaseDatabase.getReference().child("Products").child(productCode).child("price").setValue(pprice.getText().toString());
                        Toast.makeText(AdminEditDeleteProduct.this, "Product update successfully", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(AdminEditDeleteProduct.this, "Update Error", Toast.LENGTH_SHORT).show();
                    }finally {
                        goBacktoAllProducts();
                    }
                }

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AdminEditDeleteProduct.this);
                builder1.setMessage("If you want to delete this item");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                firebaseDatabase.getReference().child("Products").child(productCode).removeValue();
                                Toast.makeText(AdminEditDeleteProduct.this, "product remove successfully", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                                goBacktoAllProducts();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

    }

    public void goBacktoAllProducts(){
        startActivity(new Intent(AdminEditDeleteProduct.this, AdminViewOrdersActivity.class));
        finish();
    }

    private boolean textValidation(){
        if (pname.getText().toString().equals("") || pdescription.getText().toString().equals("") || pprice.getText().toString().equals("")){
            Toast.makeText(this, "Pleasr enter all feilds", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}