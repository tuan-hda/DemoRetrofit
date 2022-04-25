package com.example.demoretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductCustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Product> products;

    public ProductCustomAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.product_item, viewGroup, false);

        // get current product
        Product product = products.get(i);

        // init views
        ImageView productImage = view.findViewById(R.id.productImage);
        TextView productTitle = view.findViewById(R.id.productTitle),
                productPrice = view.findViewById(R.id.productPrice);

        productTitle.setText(product.getTitle());
        productPrice.setText("$" + product.getPrice() + "");

        // add loading animation
        Picasso.get().load(product.getImage()).into(productImage);

        return view;
    }
}
