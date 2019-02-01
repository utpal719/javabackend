package com.techvariable.traxpense.core.controllers;

import com.techvariable.traxpense.core.models.vm.CategoryInputVM;
import com.techvariable.traxpense.core.models.vm.CategoryVM;
import com.techvariable.traxpense.core.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author utpal
 * com.techvariable.traxpense.core.controllers
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @CrossOrigin
    @ApiOperation(value = "Create new category", response = CategoryVM.class, httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, code = 201)
    @RequestMapping(value = "/",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryVM createCategory(@RequestBody @Valid CategoryInputVM categoryVm){
        return categoryService.createCategory(categoryVm);
    }

    @CrossOrigin
    @ApiOperation(value = "Fetch all categories", response = CategoryVM.class, responseContainer = "List", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CategoryVM> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @CrossOrigin
    @ApiOperation(value = "Fetch category by id", response = CategoryVM.class, httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{categoryid}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CategoryVM getCategoryById(@PathVariable(value = "categoryid") long categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @CrossOrigin
    @ApiOperation(value = "Update category", response = CategoryVM.class, httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{categoryid}",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CategoryVM updateCategory(@PathVariable(value = "categoryid") long categoryId,@RequestBody @Valid CategoryInputVM categoryVm){
        return categoryService.updateCategory(categoryId, categoryVm);
    }

    @CrossOrigin
    @ApiOperation(value = "Delete category by id", response = Void.class, httpMethod = "DELETE", produces = MediaType.TEXT_PLAIN_VALUE)
    @RequestMapping(value = "/{categoryid}",method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
    public void deleteCategoryById(@PathVariable(value = "categoryid") long categoryId) {
        categoryService.deleteCategoryById(categoryId);
    }

    @CrossOrigin
    @ApiOperation(value = "Delete categories in batch by id", response = Void.class, httpMethod = "DELETE", produces = MediaType.TEXT_PLAIN_VALUE)
    @RequestMapping(value = "/batch",method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
    public void deleteCategoriesInbatchById(@RequestParam(value = "ids") List<Long> ids) {
        categoryService.deleteCategoriesInBatch(ids);
    }

}
