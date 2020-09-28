package com.examples.countriesappmvvm.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CountriesApi {

    @GET("DevTides/countries/master/countriesV2.json")
    Single<List<CountryModel>> getCountries();

    //if you do not have endpoint
    /*@GET
    Single<Object> getObject(@Url String urlString);*/
}
