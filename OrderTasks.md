# Tasks

/products

- get()

  1. return 200 when get products — 3
  2. return detail when get products — 5
  3. get products in product repository (ProductRepository.getAll) — 10

- post()

  1. return 201 when create product — 3

  2. return uri when create product — 3

  3. return 201 when create product with specified parameter — 10

  4. return 400 when name, description or price is null — 10

  5. save product in product repository (ProductRepository.create) — 10

     ​

/products/{productId}

- get()

  1. return 200 when get product — 3
  2. return 404 when no product exists — 3
  3. return detail when get product — 5
  4. find  product in product repository (ProductRepository.findById)— 10

  ​

/users

- post()
  1. return 201 when create user — 3
  2. return 400 when user exists — 3
  3. return uri when create user — 3
  4. return 201 when create user with specified parameter  — 5
  5. return 400 when name is null — 10
  6. find user by name in user repository (UserRepository.findUserByName) — 10 
  7. save user in user repository (UserRepository.createUser) — 10




/users/{id}/orders

- post()
  1. return 201 when create order — 3
  2. return 400 when create order with no exist user — 5
  3. return uri when create order — 3
  4. return 201 when create oder with specified parameter — 7
  5. return 400 when name, address, phone, order_items, product_id or quantity is null — 10
  6. return 400 when product_id is not found — 5
  7. find user by id (UserRepository.isUserPresent) — 10
  8. get product price in user repository(UserRepository.getProductPrice) — 10
  9. save order in user repository (UserRepository.createOrder) — 10
  10. find product by product_id in user repository (UserRepository.isProductPresent) — 10
- get()
  1. return 200 when get orders — 3
  2. return 400 when get order with no exist user — 5
  3. return detail when get orders — 5
  4. get orders in user repository in user repository(UserRepository.getOrders) — 10




/users/{id}/orders/{orderId}

- get()
  1. return 200 when get order — 3
  2. return 404 when no order exists — 3
  3. return detail when get order — 10
  4. find order by order_id in user repository (UserRepository.findOrderById) — 10




/users/{id}/orders/{orderId}/payments

- post()
  1. return 201 when create payment — 3
  2. return 400 when payment exists — 3
  3. return uri when create payment — 3
  4. return 201 when create payment with specified parameter — 5
  5. return 400 when pay_type or amount is null — 5
  6. save payment in user repository (UserRepository.creatPayment) — 10
- get()
  1. return 200 when get payment — 3
  2. return 404 when no payment exists — 3
  3. return details when get payment — 5
  4. find payment in user repository (UserRepository.findPayment) — 10




