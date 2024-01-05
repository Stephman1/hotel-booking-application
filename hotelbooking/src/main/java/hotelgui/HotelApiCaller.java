package hotelgui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.http.ResponseEntity;

import java.net.http.HttpHeaders;

public class HotelApiCaller {
	
	// "http://localhost:8080/hotel_rooms"
	private String base_url;
	
	public HotelApiCaller(String url) {
		this.base_url = url;
	}
    
	public String getAllRooms() {
		return getAllAvailableRooms("");
    }
	
	public String getAllAvailableRooms(String mapping) {

        String apiUrl = base_url + mapping;

        // Create an instance of HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        // Create an HttpRequest with the API URL
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        try {
            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Print the response code
            int statusCode = response.statusCode();
            System.out.println("Response Code: " + statusCode);

            // Print the response headers
            HttpHeaders headers = response.headers();
            System.out.println("Response Headers: " + headers.map());
            
            // Print the response body
            return response.body();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
        	httpClient = null;
        }
	}
	
	public String getAllAvailableRoomsByType(String mapping, String type) {
		String apiUrl = base_url + mapping + "/" + type.toLowerCase().strip();

        // Create an instance of HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        // Create an HttpRequest with the API URL
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();
        
        try {
            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Print the response code
            int statusCode = response.statusCode();
            System.out.println("Response Code: " + statusCode);

            // Print the response headers
            HttpHeaders headers = response.headers();
            System.out.println("Response Headers: " + headers.map());
            
            // Print the response body
            return response.body();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
        	httpClient = null;
        }
	}
	
	public String updateOccupiedStatusByHotelAndRoomNumber(String mapping, String hotel, String roomNumber, boolean occupiedStatus) {
		String apiUrl = base_url + mapping + "/" + roomNumber.strip();

        // Create JSON payload with the updated field name
        String jsonInputString = String.format("{\"hotel\": \"%s\", \"occupied\": %s}", hotel.strip(), occupiedStatus);

        // Set up HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .method("PUT", HttpRequest.BodyPublishers.ofString(jsonInputString))
                .build();
        
        try {
            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Print the response code
            int statusCode = response.statusCode();
            System.out.println("Response Code: " + statusCode);

            // Print the response headers
            HttpHeaders headers = response.headers();
            System.out.println("Response Headers: " + headers.map());
            
            // Print the response body
            return response.body();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
        	httpClient = null;
        }
	}
	
	public String updateRateByHotelAndRoomType(String mapping, String hotel, String roomType, String rate) {
		String apiUrl = base_url + mapping + "/" + roomType.toLowerCase().strip();
		
		if (!rate.contains(".")) {
			rate = rate.concat(".00");
		}

        // Create JSON payload with the updated field name
        String jsonInputString = String.format("{\"hotel\": \"%s\", \"rate\": %s}", hotel.strip(), rate);

        // Set up HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .method("PUT", HttpRequest.BodyPublishers.ofString(jsonInputString))
                .build();
        
        try {
            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Print the response code
            int statusCode = response.statusCode();
            System.out.println("Response Code: " + statusCode);

            // Print the response headers
            HttpHeaders headers = response.headers();
            System.out.println("Response Headers: " + headers.map());
            
            // Print the response body
            return response.body();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
        	httpClient = null;
        }
	}
	
}
