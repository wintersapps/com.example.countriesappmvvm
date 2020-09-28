package com.examples.countriesappmvvm.view;

import androidx.annotation.BinderThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.examples.countriesappmvvm.R;
import com.examples.countriesappmvvm.model.CountryModel;
import com.examples.countriesappmvvm.view.adapter.CountriesRecyclerViewAdapter;
import com.examples.countriesappmvvm.viewmodel.ListViewModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.countriesRecyclerView)
    RecyclerView countriesRecyclerView;

    @BindView(R.id.listErrorMaterialTextView)
    MaterialTextView listErrorMaterialTextView;

    @BindView(R.id.loadingViewProgressBar)
    ProgressBar loadingViewProgressBar;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ListViewModel viewModel;
    private CountriesRecyclerViewAdapter adapter = new CountriesRecyclerViewAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();

        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        countriesRecyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.refresh();
            swipeRefreshLayout.setRefreshing(false);
        });

        observerViewModel();
    }

    private void observerViewModel(){
        viewModel.countries.observe(this, countryModels -> {
            if(countryModels != null){
                countriesRecyclerView.setVisibility(View.VISIBLE);
                adapter.updateCountries(countryModels);
            }
        });
        viewModel.countryLoadError.observe(this, isError -> {
            if(isError != null){
                listErrorMaterialTextView.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });
        viewModel.loading.observe(this, isLoading -> {
            if(isLoading != null){
                loadingViewProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if(isLoading){
                    listErrorMaterialTextView.setVisibility(View.GONE);
                    countriesRecyclerView.setVisibility(View.GONE);
                }
            }
        });
    }
}