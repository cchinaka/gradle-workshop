package ng.com.workshop.business.data;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import ng.com.workshop.business.data.jpa.WorkToolRepo;
import ng.com.workshop.business.data.legacy.BaseRepo;
import ng.com.workshop.business.messaging.PostMan;
import ng.com.workshop.model.workshop.ToolShed;
import ng.com.workshop.model.workshop.WorkTool;


@Service
@Qualifier("repo")
public class WorkToolsRepositoryImpl implements WorkToolsRepository {

    private static final Logger LOG = LoggerFactory.getLogger(WorkToolsRepositoryImpl.class);

    @Autowired
    JdbcOperations jdbcOperations;

    @Autowired
    @Qualifier("baseRepo")
    BaseRepo repo;

    @Autowired
    WorkToolRepo workToolRepo;

    @Autowired
    @Qualifier("postman")
    PostMan postman;


    @Override
    public List<WorkTool> findWorkTools(long max, int count) {
        List<WorkTool> tools = workToolRepo.findAll();
        LOG.debug("tools size: {}", (CollectionUtils.isEmpty(tools) ? "[empty]" : tools.size()));
        LOG.debug("shed size: {}", repo.findAll(ToolShed.class).size());
        return tools;
    }


    @Override
    @Transactional(rollbackFor = { Exception.class })
    public WorkTool register(WorkTool tool) {
        LOG.info("attempting to insert into tool");
        tool = workToolRepo.save(tool);
        LOG.info("tool: {}", tool);
        postman.sendMessage("workshop.queue", tool);
        return tool;
    }


    @Override
    public void saveFile(MultipartFile pic) throws Exception {
        LOG.debug("saving file pic: {}", pic);
    }


    @Override
    public WorkTool findWorkTool(long id) {
        return workToolRepo.findOne(id);
    }
}
