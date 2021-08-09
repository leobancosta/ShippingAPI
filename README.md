A Rest API Application built with Spring Boot 2.3.5

Tech Stack
 * Spring Boot Starter Web 
 * Spring Boot Starter Test
 * Spring Boot Starter Validation
 * Drools - Business Rule Management System
	drools-core
	kie-spring
 
 I. API Spec
 
	uri : /shipping
	method : POST
	request :
		{ 
			"weight": float,
			"height": float,
			"width": float,
			"length": float,
			"voucher": string
		}
		
	mandatory fields :
		weight, height, width, length

	response :
		{
			"shippingFee": float,
			"ruleName": string,
			"shippingStatus": string,  constants (PARCEL_REJECTED, HEAVY_PARCEL, SMALL_PARCEL, MEDIUM_PARCEL, LARGE_PARCEL)
			"discount": float,
			"discountStatus": string
		}
		
	error :
		{
			"code": int,
			"message": string,
			"timestamp": string, (unix timestamp)
			"fieldErrors": List<string>
		}


II. Steps to run the app 
	1. build app:   mvn clean package
			 it will execute junit tests, consist of 5 (3 for shippingService and 2 for voucherService)

	2. run app:     mvn spring-boot:run


III. Test scenarios
	request
	{
		"weight":30.22,
		"height":14.00,
		"width":15.45,
		"length":15.00,
		"voucher":"MYNT"
	}

	response : HEAVY_PARCEL + has discount
	
	request
	{
		"weight":6.22,
		"height":14.00,
		"width":15.45,
		"length":15.00,
		"voucher":"sdfsd"
	}

	response : LARGE_PARCEL + invalid discount

	request
	{
		"weight":0.0,
		"height":14.00,
		"width":15.45,
		"length":15.00,
		"voucher":"sdfsd"
	}

	response : validation error

IV. Config files
	drools rule files :

	src\main\resources\rules\shipping\shippingRules.drl
	src\main\resources\rules\voucher\voucherRules.drl

	drools main module config :

	src\main\resources\META-INF\kmodule.xml
