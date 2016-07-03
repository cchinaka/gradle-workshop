package ng.com.workshop.test.web;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import ng.com.workshop.business.data.WorkToolsRepository;
import ng.com.workshop.model.workshop.WorkTool;
import ng.com.workshop.web.controller.HomeController;


public class HomeControllerTest {

    @Test
    public void testHomePage() throws Exception {
        HomeController ctrl = new HomeController();
        MockMvc mockMvc = standaloneSetup(ctrl).build();
        mockMvc.perform(get("/")).andExpect(view().name("home"));
        mockMvc.perform(get("/homepage")).andExpect(view().name("home"));
    }


    @Test
    public void shouldShowRecentWorkTools() throws Exception {
        List<WorkTool> expectedTools = createWorkTools(20);
        WorkToolsRepository workRepo = Mockito.mock(WorkToolsRepository.class);
        Mockito.when(workRepo.findWorkTools(Long.MAX_VALUE, 20)).thenReturn(expectedTools);
        HomeController controller = new HomeController(workRepo);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/pages/workshop.jsp")).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/worktools")).andExpect(MockMvcResultMatchers.view().name("worktool"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("worktools")).andExpect(model().attribute("worktools", expectedTools));
    }


    @Test
    public void shouldShowRegistrationForm() throws Exception {
        HomeController controller = new HomeController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/register")).andExpect(view().name("registration-form"));
    }


    @Test
    public void shouldProcessRegistration() throws Exception {
        WorkToolsRepository workToolRepo = mock(WorkToolsRepository.class);
        WorkTool unsavedTool = new WorkTool(0L, "code", "description");
        WorkTool savedTool = new WorkTool(1L, "code", "description");
        Mockito.when(workToolRepo.register(unsavedTool)).thenReturn(savedTool);
        HomeController controller = new HomeController(workToolRepo);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(post("/register").param("id", "0").param("code", "code").param("description", "description"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/hellothyme"));
        Mockito.verify(workToolRepo, Mockito.atLeastOnce()).register(unsavedTool);
    }


    private List<WorkTool> createWorkTools(int number) {
        List<WorkTool> tools = new ArrayList<>();
        for (long i = 0; i < number; i++) {
            tools.add(new WorkTool(i + 1, "code" + i, "desc" + i, 20.0d + i, 30.59d + i));
        }
        return tools;
    }
}
