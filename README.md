# andbxd

Java language bindings for Unbxd Site Search APIs.

### Requirements

1. Unbxd Site Account
2. At least one site under the account

## Usage
### Search

##### Client instance
`AndbxdClient` is the client that is required for making calls to Unbxd Site Search API endpoints. To get an instance of `AndbxdClient`:

```Java
import com.unbxdapi.andbxd.AndbxdClient;

...

public class SearchActivity {
  private static final SITE_KEY = "";
  private static final API_KEY = "";

  ...

  private AndbxdClient mClient;

  ...

  @Override
  protected onCreate(Bundle savedInstanceState) {
    ...

    mClient = AndbxdClient.getInstance(SITE_KEY, API_KEY);

    ...
  }

  ...

}
```

##### Building a query
Build a `Query` using `Query.Builder`:
```Java
import com.unbxdapi.andbxd.Query;

...

private doSearch(String queryString) {
  ...

  Query query = new Query.Builder().setQueryString(queryString).build();

  SearchResponse response = mClient.search(query);

  ...
}

```

###### Get list of products for a query
`SearchResponse` contains the list of products for the requested query.
```Java
private doSearch(String queryString) {
  ...

  SearchResponse response = mClient.search(query);
  ArrayList<Product> products = response.response.products;

  ...
}
```

`Product` implements `HashMap`. `products.get(index).get(key)` will return the value for `key`.

`key` can be any of the attributes for the product which as uploaded in the feed.

#### Handling errors
**TODO**

### Add dependency on andbxd
```groovy
compile 'com.unbxdapi.android:andbxd:2.2.1'
```
