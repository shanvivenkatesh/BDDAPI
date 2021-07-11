Feature: Pet store Post scenarios

@Post(order=1)
Scenario: Place an order for a pet with valid input data
	Given placing a valid pet order
	When User calls PostPetOrderAPI with "/store/order" post HTTP method
	Then validate the API status code 200
	And validate the "POST" response message
	
	@Get(order=2)		
Scenario: Retrive Get pet order with valid input data
	Given get a pet order ID
	When User calls GetPetOrderAPI "/store/order/" with valid order ID
	Then validate the Get API status code 200
	And validate the "GET" response message
	
	
@Get(order=3)
Scenario: Retrive Get pet order with invalid input data
	Given get a pet order ID
	When User calls GetPetOrderAPI "/store/order/" with invalid order ID
	Then validate the Get API status code 404
	And validate the "GET" response message
	
@Post(order=4)
Scenario: Place an order for a pet with invalid input data
	Given placing a invalid pet order
	When User calls PostPetOrderAPI with "/store/order" post HTTP method
	Then validate the API status code 400
	And validate the "POST" response message
