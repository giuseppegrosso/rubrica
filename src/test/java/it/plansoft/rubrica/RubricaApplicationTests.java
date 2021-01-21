package it.plansoft.rubrica;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/rubrica/")).andDo(print()).andExpect(status().isOk())
				// .andExpect(content().string(containsString("[{"id":1,"cognome":"Grosso","nome":"Giuseppe","indirizzo":"via delle panche 13"}]")))
				;
	}

}
