package ng.com.workshop.web.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import ng.com.workshop.business.data.WorkToolsRepository;
import ng.com.workshop.model.workshop.WorkTool;


@Controller
@RequestMapping("/forms")
public class TestFormController {

    private static final Logger LOG = LoggerFactory.getLogger(TestFormController.class);

    @Autowired
    WorkToolsRepository repo;


    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model) {
        WorkTool tool = new WorkTool();
        tool.setCode("test code");
        model.addAttribute("worktool", tool);
        model.addAttribute("tools", repo.findWorkTools(Long.MAX_VALUE, 20));
        return "registration-form";
    }


    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String processRegistration(WorkTool worktool, @RequestPart("profilePicture") MultipartFile pic) throws Exception {
        try {
            LOG.info("Doing the registration now...tool: {}", worktool);
            File file = new File("/tmp/worktools/saved/" + pic.getOriginalFilename());
            file.createNewFile();
            // pic.transferTo(file);
            // repo.saveFile(pic);
            repo.register(worktool);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/hellothyme";
    }
}
