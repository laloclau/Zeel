package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITest {


	public static void main(String[] args) {

		RestAssured.baseURI = "https://iezapt5vu6.execute-api.us-east-1.amazonaws.com/test/patient";
		RequestSpecification request = RestAssured.given();

		request.queryParam("api_key", getAPIKey());

		Response response = request.get();
		List<HashMap<String, Object>> patients = response.jsonPath().getList("");

		String targetId = "SR19760827202206208364";
		String compareBody ="{id=SR19760827202206208364, birthdate=1976-08-27, phone=347-555-9876, appointment_date=2022-06-20, name={lastName=Rogers, firstName=Steve}, address={street=45 W 45th St, city=New York, state=NY, zip=10036}}"
				;
		boolean found = false;
		for (HashMap<String, Object> patient : patients) {
			if (patient.get("id").equals(targetId)) {
				found = true;

//				HashMap<String, Object> targetPatient = patient;
//				Compare all the details to make sure this is patient, and all details matches
				if(patient.toString().equals(compareBody)) {
					System.out.println("All of the details are match in record");
				}else {
					System.out.println("We found patient, but the details is not match"+patient.toString());
				}

				break;
			}
		}

		if (found) {
			System.out.println("Patient found, test passed");
		} else {
			System.out.println("Patient is not found, test failed");
		}

	}

	public static String getAPIKey() {
		
		RestAssured.baseURI = "https://iezapt5vu6.execute-api.us-east-1.amazonaws.com/test/getkey?firstName=Claudia&lastName=Ramirez";
		RequestSpecification request = RestAssured.given();

		request.queryParam("firstName", "Claudia");
		request.queryParam("lastName", "Ramirez");
		
		Response response = request.get();
		String key = response.jsonPath().get("api_key");
				
		
		return key;
	}

}
