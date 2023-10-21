	
	Feature: Address Module API Automation
	
	  Scenario Outline: Verify add new address and get addressId in the application through API
	    Given User should add headers and bearer authorization for accessing address endpoints
	    When User should add request body for add new address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>" and "<address_type>"
	    When User send "POST" request to add new address
	    Then User should verify the status code is 200
	    And User should verify the response body message for create address as "Address added successfully" and get AddressId
	
	    Examples: 
	      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address | address_type |
	      | Jacobs     | s         | 6234567891 | apartment |    35 | 3659 |     101 | 6000101 | chennai | Office       |
	
	  Scenario Outline: Verify updated address by using addressId in the application through API
	    Given User should add headers and bearer authorization for accessing address endpoints
	    When User add request body for update address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>" and"<address_type>"
	    When User send "PUT" request to update existing address
	    Then User should verify the status code is 200
	    And User should verify the response body message for updated address as "Address updated successfully"
	
	    Examples: 
	      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address | address_type |
	      | Ela        | s         | 3445566777 | apartment |    35 | 3659 |     101 |  600012 | chennai | Home         |
	@Address
	  Scenario: Verify delete address by using addressId in the application through API
	    Given User should add headers and bearer authorization for accessing address endpoints
	    When User add request body for delete address
	    When User send "DELETE" request to delete address
	    Then User should verify the status code is 200
	    And User should verify the delete address response body message as "Address deleted successfully"
	
	  Scenario: Verify get address in the application through API
	    Given User should add headers and bearer authorization for accessing address endpoints
	    When User send "GET" request to get address
	    Then User should verify the status code is 200
	    And User should verify the get address response body message as "OK"
