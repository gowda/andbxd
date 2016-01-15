package com.unbxdapi.android.search;

import com.google.gson.Gson;
import com.unbxdapi.android.search.models.SearchResponse;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class SearchClient {
    private static final String ENDPOINT = "https://search.unbxdapi.com";

    private static SearchClient sInstance;

    private final String mSiteKey;
    private final String mApiKey;

    private Service mService;

    private SearchClient(String siteKey, String apiKey) {
        mSiteKey = siteKey;
        mApiKey = apiKey;

        Gson gson = new Gson();

        RequestInterceptor interceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addPathParam("siteKey", mSiteKey);
                request.addPathParam("apiKey", mApiKey);
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(interceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mService = restAdapter.create(Service.class);
    }

    public static SearchClient getInstance(String siteKey, String apiKey) {
        if (sInstance == null) {
            sInstance = new SearchClient(siteKey, apiKey);
        }

        return sInstance;
    }
    public SearchResponse search(Query query) {
        return mService.search(query);
    }
}
