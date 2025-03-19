package com.mproxy;

import java.io.IOException;
import java.net.Proxy
import java.net.http.*;
import java.time.Duration;
import java.util.List;

import com.kms.katalon.core.annotation.Keyword

public class MFunction {

	@Keyword
	public static void sendRequestWithProxy(String proxyHost, int proxyPort) {
		try {
			ProxySelector proxySelector = new ProxySelector() {
						@Override
						public List<Proxy> select(URI uri) {
							return List.of(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)));
						}

						@Override
						public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
							System.err.println("Proxy connection failed: " + ioe.getMessage());
						}
					};

			HttpClient client = HttpClient.newBuilder()
					.proxy(proxySelector)
					.connectTimeout(Duration.ofSeconds(30))
					.build();

			String jsonPayload = """
                {
                   "dialing_code":"+91",
                   "phone":"9741988761",
                   "email":"",
                   "type":"LOGIN"
                }
            """;

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://raven-data.int.lb/v1/workflow/generate_otp"))
					.timeout(Duration.ofSeconds(30))
					.header("User-Agent", "PostmanRuntime/7.20.1")
					.header("Content-Type", "application/json")
					.header("Cache-Control", "no-cache")
					.header("Postman-Token", "5acd3536-49f0-da6c-1ec5-2d7cec8dec19")
					.header("X-APP-TOKEN", "9939c070-b46e-4743-b759-662a059a1643")
					.header("X-TENANT-KEY", "2c54f6e4-eeef-4401-a236-c34520bd67bf")
					.POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
					.build();

			// Send the HTTP request and get the response
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			// Check the response status code and print the result
			if (response.statusCode() == 200) {
				System.out.println("Response Body: " + response.body());
			} else {
				System.err.println("HTTP Error: " + response.statusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
