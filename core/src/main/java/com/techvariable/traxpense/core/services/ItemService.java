package com.techvariable.traxpense.core.services;

import com.techvariable.traxpense.core.models.vm.ItemInputVM;
import com.techvariable.traxpense.core.models.vm.ItemVM;

import java.util.List;

/**
 * @author utpal
 * com.techvariable.traxpense.core.services
 */

public interface ItemService {
    List<ItemVM> findAllExpenseItems();

    List<ItemVM> findItemsByCategoryId(long categoryId);

    ItemVM findExpenseItemById(long itemId);

    ItemVM createExpenseItem(ItemInputVM itemVM);

    ItemVM updateExpenseItem(long itemId, ItemInputVM itemVM);

    void deleteExpenseItem(long itemId);

    void deleteItemsInBatch(Iterable<Long> ids);
}
