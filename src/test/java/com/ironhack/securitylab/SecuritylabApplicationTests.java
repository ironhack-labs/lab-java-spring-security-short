package com.ironhack.securitylab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SecuritylabApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	void getPosts_ShouldBePublic() throws Exception {
		mockMvc.perform(get("/api/posts"))
				.andExpect(status().isOk());
	}

	@Test
	void addAuthor_withoutAuth_ShouldFail() throws Exception {
		mockMvc.perform(
						post("/api/authors")
								.contentType(MediaType.APPLICATION_JSON)
								.content("{\"name\": \"John Doe\"}"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	void addAuthor_withAdmin_ShouldSuccess() throws Exception {
		mockMvc.perform(
						post("/api/authors")
								.contentType(MediaType.APPLICATION_JSON)
								.content("{\"name\": \"John Doe\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	@WithMockUser(roles = "USER")
	void deletePost_WithUserRole_ShouldBeForbidden() throws Exception {
		mockMvc.perform(delete("/api/posts/1"))
				.andExpect(status().isForbidden());
	}
}