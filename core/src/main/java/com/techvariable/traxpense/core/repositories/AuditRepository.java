package com.techvariable.traxpense.core.repositories;

import java.util.Collection;
import java.util.Date;

/**
 * @author utpal
 * com.techvariable.traxpense.core.repositories
 */
public interface AuditRepository<T> {
    Collection<T> findHavingCreatedDate(Date createdDate);
}
