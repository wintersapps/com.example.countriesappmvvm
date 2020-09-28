package com.examples.countriesappmvvm.di;

import com.examples.countriesappmvvm.model.CountriesService;
import com.examples.countriesappmvvm.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(CountriesService service);

    void inject(ListViewModel viewModel);
}
