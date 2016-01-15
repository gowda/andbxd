package com.unbxdapi.android.search;

import java.util.HashMap;

public class Query extends HashMap<String, String> {

    public static class Builder {
        private int mPageNumber;
        private int mPageSize;

        private String mQueryString;

        public Builder() {
            mPageNumber = 0;
            mPageSize = 10;
        }

        public Builder setQueryString(String queryString) {
            mQueryString = queryString;

            return this;
        }

        public Query build() {
            Query query = new Query();

            query.put("q", mQueryString);
            query.put("start", String.valueOf(mPageNumber));
            query.put("rows", String.valueOf(mPageSize));

            return query;
        }
    }
}
