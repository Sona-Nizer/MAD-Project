package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminViewOrdersActivity extends AppCompatActivity {

    ListView orders_list;
    TextView name;
    ArrayList<String> names = new ArrayList<>();

    ArrayList<String> nameArray, discriptionsArray, idsArray, priceArray;

    String pname, pdescription, pprice, pcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_orders);

        orders_list = findViewById(R.id.orders_list);
        name = findViewById(R.id.name);

        nameArray = new ArrayList<>();
        discriptionsArray = new ArrayList<>();
        idsArray = new ArrayList<>();
        priceArray = new ArrayList<>();

        DatabaseReference redRef = FirebaseDatabase.getInstance().getReference().child("Products");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        orders_list.setAdapter(arrayAdapter);

        redRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                names.clear();
                nameArray.clear();
                discriptionsArray.clear();
                priceArray.clear();
                idsArray.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String Category = dataSnapshot.child("category").getValue(String.class);
                    String Date = dataSnapshot.child("date").getValue(String.class);
                    pdescription = dataSnapshot.child("description").getValue(String.class);
                    pcode = dataSnapshot.child("pid").getValue(String.class);
                    pname = dataSnapshot.child("pname").getValue(String.class);
                    String time = dataSnapshot.child("time").getValue(String.class);
                    pprice = dataSnapshot.child("price").getValue(String.class);
//                names.add("Category: "+Category+" Date: "+ Date +" Descrip: "+ Description+" pid: "+ pid +" pname: "+ pname+" time: "+ time+" price: "+ price);
                    names.add(pname + " - " + pcode);
                    nameArray.add(pname);
                    discriptionsArray.add(pdescription);
                    priceArray.add(pprice);
                    idsArray.add(pcode);
                    arrayAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        orders_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AdminViewOrdersActivity.this, AdminEditDeleteProduct.class);
                intent.putExtra("product_code", idsArray.get(i));
                intent.putExtra("product_name", nameArray.get(i));
                intent.putExtra("product_description", discriptionsArray.get(i));
                intent.putExtra("product_price", priceArray.get(i));
                startActivity(intent);
                Log.d("mydata", nameArray.get(i)+" "+priceArray.get(i)+" "+discriptionsArray.get(i));
            }
        });








    }
}