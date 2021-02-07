package it.plansoft.rubrica;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * classe di test sull'ambiente.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RubricaApplicationTests {

	@Autowired
	private MockMvc mockMvc;



	@Test
	@WithMockUser(username = "giuseppe", password = "pippo", roles = {"USER|READ"})
	public void testRubrica() throws Exception {
		this.mockMvc.perform(get("/rubrica/")).andDo(print()).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username = "giuseppe", password = "giuseppe")
	public void testGetRubricaById() throws Exception {
		Long id = 1L;
		this.mockMvc.perform(get("/rubrica/" + id)).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "daniele", password = "daniele", roles = {"USER|READ"})
	public void testRubricaVis() throws Exception {
		this.mockMvc.perform(get("/rubricavis/")).andDo(print()).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username = "daniele", password = "daniele", roles = {"USER|READ"})
	public void testRubricaVis2() throws Exception {
		this.mockMvc.perform(get("/rubricavis/")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "giuseppe", password = "giuseppe")
	public void testUserAccount() throws Exception {
		this.mockMvc.perform(get("/userAccount/")).andDo(print()).andExpect(status().isOk());
	}

}
