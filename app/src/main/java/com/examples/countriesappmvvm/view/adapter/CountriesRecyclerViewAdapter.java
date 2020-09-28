package com.examples.countriesappmvvm.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.countriesappmvvm.R;
import com.examples.countriesappmvvm.model.CountryModel;
import com.examples.countriesappmvvm.view.util.Util;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountriesRecyclerViewAdapter extends RecyclerView.Adapter<CountriesRecyclerViewAdapter.CountryViewHolder>{

    private List<CountryModel> countries;

    public CountriesRecyclerViewAdapter(List<CountryModel> countries) {
        this.countries = countries;
    }

    public void updateCountries(List<CountryModel> newCountries){
        countries.clear();
        countries.addAll(newCountries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountriesRecyclerViewAdapter.CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imageView)
        AppCompatImageView countryImageView;

        @BindView(R.id.nameMaterialTextView)
        MaterialTextView countryName;

        @BindView(R.id.capitalMaterialTextView)
        MaterialTextView countryCapital;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(CountryModel country){
            countryName.setText(country.getCountryName());
            countryCapital.setText(country.getCapital());
            Util.loadImage(countryImageView, country.getFlag(), Util.getProgressDrawable(countryImageView.getContext()));
        }
    }

}
