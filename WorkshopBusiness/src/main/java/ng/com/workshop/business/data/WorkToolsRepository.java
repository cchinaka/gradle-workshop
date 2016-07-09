package ng.com.workshop.business.data;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ng.com.workshop.model.workshop.WorkTool;


public interface WorkToolsRepository {

    List<WorkTool> findWorkTools(long max, int count);


    WorkTool findWorkTool(long id);


    WorkTool register(WorkTool tool) throws Exception;


    void saveFile(MultipartFile pic) throws Exception;
}
