package com.project.appglee.AppGLEE_DB.repository;


import com.project.appglee.AppGLEE_DB.entity.Visitant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VisitantRepository extends CrudRepository<Visitant, Integer> {
    @Query(value = "(SELECT EXISTS(SELECT id FROM visitant WHERE phone=:num_phone))", nativeQuery = true)
    int existByNumPhone(String num_phone);
    @Query(value = "SELECT EXISTS(SELECT V.* FROM visitant V WHERE V.phone=:num_phone AND NOT (V.id=:id))", nativeQuery = true)
    int existByNumPhoneForUpdate(String num_phone, int id);
}
