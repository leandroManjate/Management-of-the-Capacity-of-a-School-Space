package com.project.appglee.AppGLEE_DB.service;
import com.project.appglee.AppGLEE_DB.repository.CategoryRepository;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.project.appglee.AppGLEE_DB.utils.Global.*;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public GenericResponse listValidCategories(){
        return new GenericResponse(TYPE_DATA, RESP_OK, CORRECT_OPERATION, this.categoryRepository.listValidCatogories());
    }
}
