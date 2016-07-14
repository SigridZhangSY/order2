# Tasks

/products

- post()
  1. return 201 
     - return 201 when create product — 3  6
     - return uri when create product — 3  5
     - return 201 when create product with specified parameter — 10  17
  2. return 400 
     - return 400 when name, description or price is null — 10 7
  3. save product in product repository (ProductRepository.createProduct) — 10 26
- get()

  1. return 200 
     - return 200 when get products — 3 5
     - return detail when get products — 5 10
  2. get products in product repository (ProductRepository.getAll) — 10 27
     ​

/products/{productId}

- get()

  1. return 200
     - return 200 when get product — 3 5
     - return detail when get product — 5 17
  2. return 404 
     - return 404 when no product exists — 3 7
  3. find  product in product repository (ProductRepository.findById)— 10  17

  ​

/users

- post()
  1. return 201 
     - return 201 when create user — 3 5
     - return uri when create user — 3  14
     - return 201 when create user with specified parameter  — 5 18
  2. return 400
     - return 400 when user exists — 3 7
     - return 400 when name is null — 10 8
  3. find whether user exist in user repository (UserRepository.isUserPresent) — 10  27
  4. save user in user repository (UserRepository.createUser) — 10 18




/users/{id}/orders

- post()
  1. return 201

     - return 201 when create order — 3 4
     - return uri when create order — 3 5
     - get product price in user repository(UserRepository.getProductPrice) — 10
     - save order in user repository (UserRepository.createOrder) — 10
     - return 201 when create oder with specified parameter — 7

  2. return 400 

     - find user by id (UserRepository.isUserPresent) — 10
     - return 400 when create order with no exist user — 5
     - return 400 when name, address, phone, order_items, product_id or quantity is null — 10
     - find product by product_id in user repository (UserRepository.isProductPresent) — 10
     - return 400 when product_id is not found — 5

     ​
- get()
  1. return 200 

     - return 200 when get orders — 3
     - get orders in user repository in user repository(UserRepository.getOrders) — 10
     - return detail when get orders — 5

  2. return 400 when get order with no exist user — 5

     ​




/users/{id}/orders/{orderId}

- get()
  1. return 200 
     - return 200 when get order — 3
     - find order by order_id in user repository (UserRepository.findOrderById) — 10
     - return detail when get order — 10
  2. return 404 when no order exists — 3




/users/{id}/orders/{orderId}/payments

- post()
  1. return 201

     - return 201 when create payment — 3
     - return uri when create payment — 3
     - save payment in user repository (UserRepository.creatPayment) — 10
     - return 201 when create payment with specified parameter — 5

  2. return 400

     - find whether payment exist in user repository (UserRepository.isPaymentPresent)
     - return 400 when payment exists — 3
     - return 400 when pay_type or amount is null — 5

     ​
- get()
  1. return 200

     -  return 200 when get payment — 3
     - find payment in user repository (UserRepository.findPaymentById) — 10
     - return details when get payment — 5

  2. return 404 when no payment exists — 3

     ​





