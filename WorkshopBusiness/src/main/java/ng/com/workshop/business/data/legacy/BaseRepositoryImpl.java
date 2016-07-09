package ng.com.workshop.business.data.legacy;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ng.com.workshop.model.BaseModel;


@Repository
@Qualifier("baseRepo")
public class BaseRepositoryImpl implements BaseRepo {

    @PersistenceContext
    protected EntityManager manager;


    @Override
    public <T extends BaseModel> T save(T entity) {
        if (entity.getId() == null) {
            manager.persist(entity);
        } else {
            entity = manager.merge(entity);
        }
        return entity;
    }


    @Override
    public <T extends BaseModel> T find(Class<T> clazz, Serializable id) {
        return manager.find(clazz, id);
    }


    @Override
    public <T extends BaseModel> List<T> findAll(Class<T> clazz) {
        return manager.createQuery(String.format("select s from %s s", clazz.getSimpleName()), clazz).getResultList();
    }
}
