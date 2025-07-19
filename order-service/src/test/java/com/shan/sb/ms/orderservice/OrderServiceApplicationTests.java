package com.shan.sb.ms.orderservice;

import com.shan.sb.ms.orderservice.stubs.InventoryStubs;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;

@AutoConfigureWireMock(port = 0)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {
	@Container
	@ServiceConnection
	static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
			.withDatabaseName("order-service")
			.withUsername("root")
			.withPassword("mysql")
			.withReuse(true)
			.withStartupTimeout(Duration.ofSeconds(120));

	static {
		mysqlContainer.start();
	}
	@LocalServerPort
	private int randomPort;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = randomPort;
	}
	@Test
	void shouldSubmitOrder() {
		String submitOrderJson = createOrder();
		InventoryStubs.stubInventoryCall("iphone_15", 1);
		var responseBodyString = RestAssured.given()
				.contentType("application/json")
				.body(submitOrderJson).when().post("/api/order")
				.then()
				.log().all()
				.statusCode(201)
				.extract()
				.body().asString();
		 assertThat(responseBodyString, Matchers.is("Order Placed Successfully"));
	}


	private String createOrder() {
		String submitOrderJson = """
                {
                     "skuCode": "iphone_15",
                     "price": 1000,
                     "quantity": 1
                }
                """;
		return submitOrderJson;
	}
}
