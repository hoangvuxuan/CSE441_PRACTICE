package com.example.prac03;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blongho.country_data.Country;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Country> countries;

    public Adapter(Context context, List<Country> countries) {
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.countryName.setText(countries.get(position).getName());
        holder.imageView.setImageResource(countries.get(position).getFlagResource());
        holder.capital.setText(countries.get(position).getCapital());

        Country country = countries.get(position);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("flag", country.getFlagResource());
            intent.putExtra("name", country.getName());
            intent.putExtra("capital", country.getCapital());
            intent.putExtra("area", country.getArea());
            intent.putExtra("density", country.getAlpha2());
            intent.putExtra("population", country.getPopulation());
            intent.putExtra("worldShare", country.getAlpha3());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView countryName, capital;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_item_image);
            countryName = itemView.findViewById(R.id.list_item_country_name);
            capital = itemView.findViewById(R.id.list_item_capital);

        }
    }
}
