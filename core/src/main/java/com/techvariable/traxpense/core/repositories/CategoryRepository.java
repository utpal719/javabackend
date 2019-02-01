package com.techvariable.traxpense.core.repositories;

import com.techvariable.traxpense.core.models.Category;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author utpal
 * com.techvariable.traxpense.core.repositories
 */
@Configuration
@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAll();

    Category findById(long id);

    List<Category> findAllById(Iterable<Long> ids);

    Category save(Category c);

    void delete(Category c);

    void deleteById(long id);

}
