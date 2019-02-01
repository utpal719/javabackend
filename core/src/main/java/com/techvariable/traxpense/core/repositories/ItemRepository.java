package com.techvariable.traxpense.core.repositories;

import com.techvariable.traxpense.core.models.Item;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author utpal
 * com.techvariable.traxpense.core.repositories
 */
@Configuration
@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Long>,AuditRepository<Item> {

    Item findById(long id);

    List<Item> findAll();

    List<Item> findAllById(Iterable<Long> ids);

    Item save(Item item);

    void deleteById(long id);

    void delete(Item i);

    void deleteInBatch(Iterable<Item> items);

    List<Item> findHavingCreatedDate(Date createdDate);
}
