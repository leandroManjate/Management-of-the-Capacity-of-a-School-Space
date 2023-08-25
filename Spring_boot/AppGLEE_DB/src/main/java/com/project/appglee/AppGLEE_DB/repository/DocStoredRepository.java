package com.project.appglee.AppGLEE_DB.repository;


import com.project.appglee.AppGLEE_DB.entity.DocStored;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DocStoredRepository extends CrudRepository<DocStored, Long> {
    @Query("SELECT da FROM DocStored da WHERE da.status = 'A' AND da.deleted = false")
    Iterable<DocStored> list();

    @Query("SELECT da FROM DocStored da WHERE da.fileName = :fileName AND da.status = 'A' AND da.deleted = false")
    Optional<DocStored> findByFileName(String fileName);
}
