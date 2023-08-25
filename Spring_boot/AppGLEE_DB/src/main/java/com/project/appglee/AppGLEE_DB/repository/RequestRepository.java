package com.project.appglee.AppGLEE_DB.repository;

import com.project.appglee.AppGLEE_DB.entity.Request;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RequestRepository extends CrudRepository<Request, Integer> {

    @Query(value = "SELECT R FROM Request R WHERE R.visitant.id=:idVis")
    Iterable<Request> giveMyRequest(int idVis);
    @Query(value = "SELECT R FROM Request R WHERE R.id=:idOrden AND R.visitant.id=:idVis")
    Optional<Request> findByIdAndVisitantId(int idVis, int idOrden);

    @Modifying
    @Query(value = "UPDATE Event E SET E.vacancy=vacancy-:quant WHERE E.id=:id")
    void discountVacancy(int quant, int id);

    @Modifying
    @Query(value = "UPDATE Event E SET E.vacancy=vacancy+:quant WHERE E.id=:id")
    void increaseVacancy(int quant, int id);


}
