package com.example.randomapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.calculator3.R;
import com.example.randomapp.model.Product;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Consumer<List<Object>> onClickRow;
    private Consumer<List<Object>> onClickToDelete;

    public ProductAdapter(Context context, List<Product> items, Consumer<List<Object>> onClickRow, Consumer<List<Object>> onClickToDelete) {
        super(context, 0, items);
        this.onClickRow = onClickRow;
        this.onClickToDelete = onClickToDelete;
    }


    @SuppressLint("NewApi")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        Product currentItem = getItem(position);

        TextView textName = listItemView.findViewById(R.id.text_name);
        TextView textBrand = listItemView.findViewById(R.id.text_brand);
        TextView textIsRegulated = listItemView.findViewById(R.id.text_is_regulated);
        TextView textRate = listItemView.findViewById(R.id.text_rate);
        TextView textCreatedAt = listItemView.findViewById(R.id.text_created_at);
        TextView textUpdatedAt = listItemView.findViewById(R.id.text_updated_at);
        Button deletebtn = listItemView.findViewById(R.id.btn_delete);

        textName.setText(currentItem.getName());
        textBrand.setText(currentItem.getBrand());
        textIsRegulated.setText(String.valueOf(currentItem.isRegulated()));
        textRate.setText(String.valueOf(currentItem.getRate()));
        textCreatedAt.setText(formatDate(currentItem.getCreatedAt()));
        textUpdatedAt.setText(formatDate(currentItem.getUpdatedAt()));
        deletebtn.setOnClickListener(v -> {
            this.onClickToDelete.accept(Arrays.asList(v.getContext(),position,currentItem));
        });

        listItemView.setOnClickListener(v->{
            this.onClickRow.accept(Arrays.asList(v.getContext(),position, currentItem));
        });

        return listItemView;
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(date);
    }

}
