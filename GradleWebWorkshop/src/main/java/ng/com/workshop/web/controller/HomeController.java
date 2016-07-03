package ng.com.workshop.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ng.com.workshop.business.data.WorkToolsRepository;
import ng.com.workshop.model.workshop.WorkTool;


@Controller
@RequestMapping({ "/", "/homepage" })
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    WorkToolsRepository repo;


    public HomeController() {
        LOG.info("something happens here...");
    }


    @Autowired
    public HomeController(WorkToolsRepository repo) {
        LOG.debug("SOMETHING HAPPENS HERE. INJECTING");
        this.repo = repo;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        LOG.info("will at least print here out...");
        return "home";
    }


    @RequestMapping(value = "worktools")
    public String showRecentWorkTools(Model model) {
        System.out.println("repo: " + repo);
        model.addAttribute("worktools", repo.findWorkTools(Long.MAX_VALUE, 20));
        return "worktool";
    }


    @RequestMapping(value = "hellothyme")
    public String getSampleThyme(Model model) {
        LOG.info("nothing dey there jor...");
        WorkTool worktool = new WorkTool("thyme-leaf-one", "the description of thymeleaf description");
        model.addAttribute("worktool", worktool);
        return "hello";
    }


    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("worktool", new WorkTool());
        model.addAttribute("tools", repo.findWorkTools(Long.MAX_VALUE, 20));
        return "registration-form";
    }


    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String processRegistration(WorkTool worktool) throws Exception {
        LOG.info("Doing the registration now...tool: {}", worktool);
        repo.register(worktool);
        return "redirect:/hellothyme";
    }
}
