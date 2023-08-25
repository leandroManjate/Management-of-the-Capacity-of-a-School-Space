package com.project.appglee.AppGLEE_DB.repository;
import com.project.appglee.AppGLEE_DB.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    @Query(value = "SELECT C FROM Category C WHERE C.isvalid IS 1")
    Iterable<Category> listValidCatogories();
}
