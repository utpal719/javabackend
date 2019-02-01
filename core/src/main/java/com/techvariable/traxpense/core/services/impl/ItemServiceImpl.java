package com.techvariable.traxpense.core.services.impl;

import com.techvariable.traxpense.core.common.exceptions.BadRequestException;
import com.techvariable.traxpense.core.common.exceptions.ResourceNotFoundException;
import com.techvariable.traxpense.core.models.Item;
import com.techvariable.traxpense.core.models.vm.ItemInputVM;
import com.techvariable.traxpense.core.models.vm.ItemVM;
import com.techvariable.traxpense.core.repositories.CategoryRepository;
import com.techvariable.traxpense.core.repositories.ItemRepository;
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
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<ItemVM> findItemsByCategoryId(long categoryId) {
        List<ItemVM> itemList = itemRepository.findAll()
                                            .stream()
                                            .filter(item -> categoryId == item.getCategory().getId() )
                                            .map(item -> ItemVM.from(item))
                                            .collect(Collectors.toList());
        return itemList;
    }

    @Override
    public ItemVM findExpenseItemById(long itemId) {
        return ItemVM.from(itemRepository.findById(itemId));
    }

    @Override
    public ItemVM createExpenseItem(ItemInputVM itemVM) {
        if(itemVM.getCategoryId() > 0){
            if(!categoryRepository.existsById(itemVM.getCategoryId()))
                throw new BadRequestException("Referenced category with id "+itemVM.getCategoryId()+" does not exist");
        } else {
            throw new BadRequestException("Referenced category with id "+itemVM.getCategoryId()+" does not exist");
        }

        return ItemVM.from(itemRepository
                .save(
                        Item.from(itemVM, categoryRepository.findById(itemVM.getCategoryId()))
                )
        );
    }

    @Override
    public ItemVM updateExpenseItem(long itemId, ItemInputVM itemVM) {
        Item iBase = itemRepository.findById(itemId);
        iBase.setHeading(itemVM.getHeading());
        iBase.setNotes(itemVM.getNotes());
        iBase.setCost(itemVM.getCost());
        if(itemVM.getCategoryId() > 0){
            if(!categoryRepository.existsById(itemVM.getCategoryId()))
                throw new BadRequestException("Referenced category with id "+itemVM.getCategoryId()+" does not exist");
        } else {
            throw new BadRequestException("Referenced category with id "+itemVM.getCategoryId()+" does not exist");
        }
        iBase.setCategory(categoryRepository.findById(itemVM.getCategoryId()));

        return ItemVM.from(itemRepository.save(iBase));
    }

    @Override
    public void deleteExpenseItem(long itemId) {
        if(!itemRepository.existsById(itemId))
            throw new ResourceNotFoundException("Item with id "+itemId+" does not exist");
        itemRepository.deleteById(itemId);
    }

    @Override
    public void deleteItemsInBatch(Iterable<Long> ids) {
        for(long id: ids){
            if(!itemRepository.existsById(id))
                throw new ResourceNotFoundException("Item with id "+id+" does not exist");
        }

        for(long id: ids){
            itemRepository.deleteById(id);
        }
    }

    @Override
    public List<ItemVM> findAllExpenseItems() {
        return itemRepository.findAll()
                .stream()
                .map(item -> ItemVM.from(item))
                .collect(Collectors.toList());
    }
}
