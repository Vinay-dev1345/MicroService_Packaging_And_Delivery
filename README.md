# Packaging And Delivery MicroService

This Service takes care of computing cost based on the type and quantity of the component.

When Microservice is initialised, script schema.sql and data.sql are executed to create necessary costing data.

When Request is originated, if quantity is greater than 0, then respective service function is called and performs required action.

The service function will fetch the price data from H2 in-memory database and gathers necessary price details
once price details are fetched, cost is computed along with Quantity then Packaging Cost, Delivery Cost is returned as an Array of Double.

This Double Array is parsed by the controller and sent a positive response.

If the component type is invalid or has a negative quantity, then errors boolean is updated with true.

For More details, Reffer below details.

## End Point

### 1. GET -> http://{{baseUrl}}/v1/packagingAndDelivery/getcost?type={{ componentType }}&count={{ quantity }}

  Response Body
  
  ````
  media type : application/json
  status : 200 OK
  
  {
    "processingCost": double
    "packagingAndDeliveryCost": double,
    "errors": boolean
  }
  
  ````
  
  ````
  media type : application/json
  status : 409 Conflict
  
  {
    "errors": boolean
  }
  
  ````



