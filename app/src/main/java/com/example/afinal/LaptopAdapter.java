package com.example.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class LaptopAdapter extends BaseAdapter {
    private Context context;
    private List<Laptop> laptops;
    private LayoutInflater inflater;

    public LaptopAdapter(Context context, List<Laptop> laptops) {
        this.context = context;
        this.laptops = laptops;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return laptops.size();
    }

    @Override
    public Object getItem(int position) {
        return laptops.get(position);
    }

    @Override
    public long getItemId(int position) {
        return laptops.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.laptop_list_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.laptop_image);
            holder.nameTextView = convertView.findViewById(R.id.laptop_name);
            holder.priceTextView = convertView.findViewById(R.id.laptop_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Laptop laptop = laptops.get(position);
        holder.nameTextView.setText(laptop.getName());
        holder.priceTextView.setText("$" + String.format("%.2f", laptop.getPrice()));
        
        // Load image from drawable
        int imageId = context.getResources().getIdentifier(
            laptop.getImageResource(), 
            "drawable", 
            context.getPackageName()
        );
        if (imageId != 0) {
            holder.imageView.setImageResource(imageId);
        } else {
            // Default image if not found
            holder.imageView.setImageResource(android.R.drawable.ic_menu_report_image);
        }

        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView priceTextView;
    }
}

