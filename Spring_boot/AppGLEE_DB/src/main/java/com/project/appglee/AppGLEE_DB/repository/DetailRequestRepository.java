package com.project.appglee.AppGLEE_DB.repository;
import com.project.appglee.AppGLEE_DB.entity.DetailRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DetailRequestRepository extends CrudRepository<DetailRequest, Integer> {
    @Query("SELECT DR FROM DetailRequest DR WHERE DR.request.id=:idR")
    Iterable<DetailRequest> findByRequest(int idR);



}
