package it.plansoft.rubrica;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * classe di test sull'ambiente.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RubricaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testRubrica() throws Exception {
		this.mockMvc.perform(get("/rubrica/")).andDo(print()).andExpect(status().isUnauthorized());
	}
	
	@Test
	public void testGetRubricaById() throws Exception {
		Long id = 1L;
		this.mockMvc.perform(get("/rubrica/" + id)).andDo(print()).andExpect(status().isUnauthorized());
	}
	
	// in generale posso mettere tanti metodi con @Test.
	@Test
	public void testRubricaVis() throws Exception {
		this.mockMvc.perform(get("/rubricavis/")).andDo(print()).andExpect(status().isUnauthorized());
	}
	
	@Test
	public void testUserAccount() throws Exception {
		this.mockMvc.perform(get("/userAccount/")).andDo(print()).andExpect(status().isUnauthorized());
	}

}
