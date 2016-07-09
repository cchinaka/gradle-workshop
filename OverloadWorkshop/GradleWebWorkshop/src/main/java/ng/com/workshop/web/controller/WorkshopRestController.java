package ng.com.workshop.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ng.com.workshop.business.data.WorkToolsRepository;
import ng.com.workshop.exception.AjaxException;
import ng.com.workshop.exception.RecordNotFoundException;
import ng.com.workshop.model.workshop.WorkTool;


@RestController
@RequestMapping("/rest")
public class WorkshopRestController {

    private static final Logger LOG = LoggerFactory.getLogger(WorkshopRestController.class);

    private final String MAX_VALUE = "10000";

    @Autowired
    private WorkToolsRepository repo;


    @RequestMapping(value = "worktools")
    public List<WorkTool> findWorktools(@RequestParam(value = "max", defaultValue = MAX_VALUE) long max,
            @RequestParam(value = "count", defaultValue = "20") int count) {
        LOG.info("something got in here...");
        return repo.findWorkTools(max, count);
    }


    @RequestMapping(value = "worktools/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WorkTool findWorkTool(@PathVariable("id") Long worktoolId) throws RecordNotFoundException {
        WorkTool worktool = repo.findWorkTool(worktoolId);
        if (worktool == null) {
            throw new RecordNotFoundException("null my brother...");
        }
        return worktool;
    }


    @RequestMapping(value = "register", method = RequestMethod.POST)
    public WorkTool processRegistration(WorkTool worktool) throws AjaxException {
        try {
            worktool = repo.register(worktool);
            LOG.info("Rest Registered Tool: {}", worktool);
            return worktool;
        } catch (Exception e) {
            throw new AjaxException(e);
        }
    }
}
