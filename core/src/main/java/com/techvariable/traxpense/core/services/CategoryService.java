package com.techvariable.traxpense.core.services;

import com.techvariable.traxpense.core.models.vm.CategoryInputVM;
import com.techvariable.traxpense.core.models.vm.CategoryVM;

import java.util.List;

/**
 * @author utpal
 * com.techvariable.traxpense.core.services
 */

public interface CategoryService {
    CategoryVM createCategory(CategoryInputVM categoryVm);

    List<CategoryVM> getAllCategories();

    CategoryVM getCategoryById(long categoryId);

    CategoryVM updateCategory(long categoryId,CategoryInputVM categoryVm);

    void deleteCategoryById(long categoryId);

    void deleteCategoriesInBatch(Iterable<Long> ids);
}
