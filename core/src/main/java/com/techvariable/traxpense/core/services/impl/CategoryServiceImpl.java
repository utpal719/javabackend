package com.techvariable.traxpense.core.services.impl;

import com.techvariable.traxpense.core.common.exceptions.BadRequestException;
import com.techvariable.traxpense.core.common.exceptions.ResourceNotFoundException;
import com.techvariable.traxpense.core.models.Category;
import com.techvariable.traxpense.core.models.vm.CategoryInputVM;
import com.techvariable.traxpense.core.models.vm.CategoryVM;
import com.techvariable.traxpense.core.repositories.CategoryRepository;
import com.techvariable.traxpense.core.services.CategoryService;
import com.techvariable.traxpense.core.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author utpal
 * com.techvariable.traxpense.core.services.impl
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemService itemService;

    @Override
    public CategoryVM createCategory(CategoryInputVM categoryVm) {
        return CategoryVM.from(categoryRepository.save(Category.from(categoryVm)));
    }

    @Override
    public List<CategoryVM> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> CategoryVM.from(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryVM getCategoryById(long categoryId) {
        return CategoryVM.from(categoryRepository.findById(categoryId));
    }

    @Override
    public CategoryVM updateCategory(long categoryId, CategoryInputVM categoryVm) {
        if(!categoryRepository.existsById(categoryId))
            throw new ResourceNotFoundException("Category with id "+categoryId+" not found");

        Category category = categoryRepository.findById(categoryId);
        category.setLabel(categoryVm.getLabel());
        category.setDescription(categoryVm.getDescription());
        return CategoryVM.from(categoryRepository.save(category));
    }

    @Override
    public void deleteCategoryById(long categoryId) {
        if(!categoryRepository.existsById(categoryId))
            throw new ResourceNotFoundException("Category with id "+categoryId+" does not exist");
        if(itemService.findItemsByCategoryId(categoryId).size() > 0)
            throw new BadRequestException("Operation failed: In use");
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public void deleteCategoriesInBatch(Iterable<Long> ids) {
        for(long categoryId: ids){
            if(!categoryRepository.existsById(categoryId))
                throw new ResourceNotFoundException("Category with id "+categoryId+" does not exist");
            if(itemService.findItemsByCategoryId(categoryId).size() > 0)
                throw new BadRequestException("Operation failed: In use");
        }

        for(long categoryId: ids){
            categoryRepository.deleteById(categoryId);
        }
    }
}
