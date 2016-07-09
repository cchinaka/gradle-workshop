package ng.com.workshop.business.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import ng.com.workshop.model.workshop.WorkTool;


public interface WorkToolRepo extends JpaRepository<WorkTool, Long> {

    WorkTool findByCode(String code);
}
