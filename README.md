## Order:

Sample POST call:
```curl 'http://localhost:9191/orderservice/orders' \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  --data-raw '{
    "id": 1,
    "status": "completed",
    "trackingId": "somenumber",
    "productId": 1213
  }' \
  --compressed
  ```

GET orders:

```curl -i "http://localhost:9191/orderservice/orders""```
