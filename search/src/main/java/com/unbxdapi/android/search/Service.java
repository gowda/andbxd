package com.unbxdapi.android.search;

import com.unbxdapi.android.search.models.SearchResponse;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface Service {
    @GET("/{apiKey}/{siteKey}/search?wt=json")
    SearchResponse search(@QueryMap Map<String, String> options);
}
