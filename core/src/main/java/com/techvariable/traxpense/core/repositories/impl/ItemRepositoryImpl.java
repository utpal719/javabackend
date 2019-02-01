package com.techvariable.traxpense.core.repositories.impl;

import com.techvariable.traxpense.core.models.Item;
import com.techvariable.traxpense.core.repositories.AuditRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * @author utpal
 * com.techvariable.traxpense.core.repositories.impl
 */
public class ItemRepositoryImpl implements AuditRepository<Item> {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findHavingCreatedDate(Date createdDate) {
        return this.entityManager
                .createNativeQuery("SELECT i.id, i.heading, i.notes, i.cost, i.spend_date FROM items i WHERE CAST(i.created_on as DATE) = CAST(:createdDate as DATE) AND (is_deleted = 0 OR is_deleted IS NULL)", Item.class)
                .setParameter("createdDate",createdDate,TemporalType.TIMESTAMP)
                .getResultList();
    }
}
