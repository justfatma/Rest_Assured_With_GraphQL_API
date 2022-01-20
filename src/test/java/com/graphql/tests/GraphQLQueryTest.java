package com.graphql.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.pojos.GraphQLQuery;
import com.qa.pojos.QueryVariable;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class GraphQLQueryTest {
	
	@Test
	public void getAllFilmsTest() {
		
		//https://swapi-graphql.netlify.app/.netlify/functions/index
		
		RestAssured.baseURI ="https://swapi-graphql.netlify.app";
		String query = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n    }\\n  }\\n}\\n\",\"variables\":null}";
		
		given().log().all()
			.contentType("application/json")
			.body(query)
				.when().log().all()
					.post("/.netlify/functions/index")
						.then().log().all()	
							.assertThat()
								.statusCode(200)
									.and()
										.body("data.allFilms.films[0].title", equalTo("A New Hope"));
					
	}
	
	
	@Test
	public void getAllUsersTest() {
		RestAssured.baseURI ="https://hasura.io";
		String query = "{\"query\":\"{\\n  users(limit: 10) {\\n    id\\n    name\\n  }\\n}\\n\",\"variables\":null}";
		
		given().log().all()
			.contentType("application/json")
			.header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxZDliZTU3NzVlYjY5MDA2YmZlYjdkZSJ9LCJuaWNrbmFtZSI6InRhc2F0YW4iLCJuYW1lIjoidGFzYXRhbkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvY2YzZGQxYmNiNGNlMzc0NjM5YTc4OTJmOGU5YWE3MzU_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZ0YS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMi0wMS0yMFQwNTo1MjoxOC4zNTJaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjFkOWJlNTc3NWViNjkwMDZiZmViN2RlIiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2NDI2NTc5NDAsImV4cCI6MTY0MjY5Mzk0MCwiYXRfaGFzaCI6Ik1UZG40aXVyQXlfSjlOOFYzUkRRMlEiLCJub25jZSI6ImdxOHZ5elIuNWV3RWQ3SUxwSHRnYXJvRnJPeFRQR2dqIn0.sKVmvpgCKINRCXFzzVMymNlMhDBcHwtVm4KGd4YJ-1B9wYH5d_gMr5taBlJDdSDHKagPQgreD0yY47b9eMMPe6fKA9FlhsiGwqfMM77uSwcQBsIgX64QfUxEDNhegngbe56cgGUH2zENrp9zgJ_lUxfhnmB_Bma1CuuWNPKTU2q2T7d6RUxNiT8JkdU8vtH8eT8qRdNaPZYLKVcFnYS0S_ztxbne8JbGpknruo4gxssXmxNkgAPxE7vlwEBnPnfieI5vymR7nYB7ASBN81A5tkLW_buw6sdY5IA54sVfq9PeV-xs3thkPFO4cK7fAiP07pF3IC0A3EEq0Xao0xn4qg")
				
				.body(query)
					.when().log().all()
						.post("/learn/graphql")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										.body("data.users[0].name", equalTo("tui.glen"));	
			
	}
	
	
	@DataProvider
	public Object[][] getQueryData() {
		return new Object[][] {{"10", "akshayapsangi123", "Flutter development"},
								};
	}
	
	@Test(dataProvider = "getQueryData")
	public void getAllUsersTestWithDataTest(String limit, String name, String title) {
		RestAssured.baseURI ="https://hasura.io";
		String query = "{\"query\":\"{\\n  users(limit: "+limit+", where: {name: {_eq: \\\""+name+"\\\"}}) {\\n    id\\n    name\\n    todos(where: {title: {_eq: \\\""+title+"\\\"}}) {\\n      title\\n    }\\n  }\\n}\\n\",\"variables\":null}";
		
		given().log().all()
			.contentType("application/json")
			.header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxZDliZTU3NzVlYjY5MDA2YmZlYjdkZSJ9LCJuaWNrbmFtZSI6InRhc2F0YW4iLCJuYW1lIjoidGFzYXRhbkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvY2YzZGQxYmNiNGNlMzc0NjM5YTc4OTJmOGU5YWE3MzU_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZ0YS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMi0wMS0yMFQwNTo1MjoxOC4zNTJaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjFkOWJlNTc3NWViNjkwMDZiZmViN2RlIiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2NDI2NTc5NDAsImV4cCI6MTY0MjY5Mzk0MCwiYXRfaGFzaCI6Ik1UZG40aXVyQXlfSjlOOFYzUkRRMlEiLCJub25jZSI6ImdxOHZ5elIuNWV3RWQ3SUxwSHRnYXJvRnJPeFRQR2dqIn0.sKVmvpgCKINRCXFzzVMymNlMhDBcHwtVm4KGd4YJ-1B9wYH5d_gMr5taBlJDdSDHKagPQgreD0yY47b9eMMPe6fKA9FlhsiGwqfMM77uSwcQBsIgX64QfUxEDNhegngbe56cgGUH2zENrp9zgJ_lUxfhnmB_Bma1CuuWNPKTU2q2T7d6RUxNiT8JkdU8vtH8eT8qRdNaPZYLKVcFnYS0S_ztxbne8JbGpknruo4gxssXmxNkgAPxE7vlwEBnPnfieI5vymR7nYB7ASBN81A5tkLW_buw6sdY5IA54sVfq9PeV-xs3thkPFO4cK7fAiP07pF3IC0A3EEq0Xao0xn4qg")
				
				.body(query)
					.when().log().all()
						.post("/learn/graphql")
							.then().log().all()
								.assertThat()
									.statusCode(200);
			
	}
	
	
	@Test
	public void getAllUsers_WithPojoTest() {
		RestAssured.baseURI ="https://hasura.io";
		GraphQLQuery query = new GraphQLQuery();
		
		query.setQuery("query ($limit: Int!, $name:String!) {\n"
				+ "  users(limit: $limit, where: {name: {_eq: $name}}) {\n"
				+ "    id\n"
				+ "    name\n"
				+ "  }\n"
				+ "}");
		
		QueryVariable variable = new QueryVariable();
		variable.setLimit(5);
		variable.setName("tui.glen");
		
		query.setVariables(variable);
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxZDliZTU3NzVlYjY5MDA2YmZlYjdkZSJ9LCJuaWNrbmFtZSI6InRhc2F0YW4iLCJuYW1lIjoidGFzYXRhbkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvY2YzZGQxYmNiNGNlMzc0NjM5YTc4OTJmOGU5YWE3MzU_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZ0YS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMi0wMS0yMFQwNTo1MjoxOC4zNTJaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjFkOWJlNTc3NWViNjkwMDZiZmViN2RlIiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2NDI2NTc5NDAsImV4cCI6MTY0MjY5Mzk0MCwiYXRfaGFzaCI6Ik1UZG40aXVyQXlfSjlOOFYzUkRRMlEiLCJub25jZSI6ImdxOHZ5elIuNWV3RWQ3SUxwSHRnYXJvRnJPeFRQR2dqIn0.sKVmvpgCKINRCXFzzVMymNlMhDBcHwtVm4KGd4YJ-1B9wYH5d_gMr5taBlJDdSDHKagPQgreD0yY47b9eMMPe6fKA9FlhsiGwqfMM77uSwcQBsIgX64QfUxEDNhegngbe56cgGUH2zENrp9zgJ_lUxfhnmB_Bma1CuuWNPKTU2q2T7d6RUxNiT8JkdU8vtH8eT8qRdNaPZYLKVcFnYS0S_ztxbne8JbGpknruo4gxssXmxNkgAPxE7vlwEBnPnfieI5vymR7nYB7ASBN81A5tkLW_buw6sdY5IA54sVfq9PeV-xs3thkPFO4cK7fAiP07pF3IC0A3EEq0Xao0xn4qg")
			.body(query)
		.when().log().all()
			.post("/learn/graphql")
		.then().log().all()
			.assertThat()
				.statusCode(200)
					.and()
						.body("data.users[0].name", equalTo("tui.glen"));
		
	}
	
	

}
