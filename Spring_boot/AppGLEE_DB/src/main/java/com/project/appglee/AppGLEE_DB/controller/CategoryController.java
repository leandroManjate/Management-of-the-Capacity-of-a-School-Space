package com.project.appglee.AppGLEE_DB.controller;
import com.project.appglee.AppGLEE_DB.service.CategoryService;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public GenericResponse listarCategoriasActivas(){
        return this.categoryService.listValidCategories();
    }
}
