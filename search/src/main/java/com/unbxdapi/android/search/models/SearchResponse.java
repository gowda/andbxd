package com.unbxdapi.android.search.models;

import java.util.ArrayList;

public class SearchResponse {
    public class QueryParams {
        String q;
        String wt;
    }

    public class MetaData {
        int status;
        int queryTime;

        QueryParams queryParams;
    }

    public class Error {
        String msg;
        String code;
    }

    public class Response {
        int numberOfProducts;
        int start;

        public ArrayList<Product> products;
    }

    private MetaData searchMetaData;
    private Error error;
    public Response response;
}
