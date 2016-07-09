package ng.com.workshop.business.data.legacy;

import java.io.Serializable;
import java.util.List;

import ng.com.workshop.model.BaseModel;


public interface BaseRepo {

    <T extends BaseModel> T save(T t);


    <T extends BaseModel> T find(Class<T> t, Serializable id);


    <T extends BaseModel> List<T> findAll(Class<T> t);
}
