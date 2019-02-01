package com.techvariable.traxpense.core.controllers;

import com.techvariable.traxpense.core.models.vm.ItemInputVM;
import com.techvariable.traxpense.core.models.vm.ItemVM;
import com.techvariable.traxpense.core.services.ItemService;
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
@RequestMapping("/api")
public class ItemController {
    @Autowired
    ItemService itemService;

    @CrossOrigin
    @ApiOperation(value = "Create new expense item", response = ItemVM.class, httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, code = 201)
    @RequestMapping(value = "/items/",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemVM createExpenseItem(@RequestBody @Valid ItemInputVM itemVM){
        return itemService.createExpenseItem(itemVM);
    }

    @CrossOrigin
    @ApiOperation(value = "Fetch all expense items", response = ItemVM.class, responseContainer = "List", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/items/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ItemVM> getAllExpenseItems(){
        return itemService.findAllExpenseItems();
    }

    @CrossOrigin
    @ApiOperation(value = "Fetch expense item by id", response = ItemVM.class, httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/items/{itemid}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ItemVM getExpenseItemById(@PathVariable(value = "itemid") long itemId) {
        return itemService.findExpenseItemById(itemId);
    }

    @CrossOrigin
    @ApiOperation(value = "Fetch expense items by category id", response = ItemVM.class, responseContainer = "List", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/categories/{categoryid}/items/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ItemVM> getExpenseItemByCategoryId(@PathVariable(value = "categoryid") long categoryid) {
        return itemService.findItemsByCategoryId(categoryid);
    }

    @CrossOrigin
    @ApiOperation(value = "Update expense item", response = ItemVM.class, httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/items/{itemid}",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ItemVM updateExpenseItem(@PathVariable(value = "itemid") long itemId, @RequestBody ItemInputVM itemBody) {
        return itemService.updateExpenseItem(itemId, itemBody);
    }

    @CrossOrigin
    @ApiOperation(value = "Delete expense item by id", response = Void.class, httpMethod = "DELETE", produces = MediaType.TEXT_PLAIN_VALUE)
    @RequestMapping(value = "/items/{itemid}",method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
    public void deleteExpenseItemById(@PathVariable(value = "itemid") long itemId) {
        itemService.deleteExpenseItem(itemId);
    }

    @CrossOrigin
    @ApiOperation(value = "Delete expense items in batch by id", response = Void.class, httpMethod = "DELETE", produces = MediaType.TEXT_PLAIN_VALUE)
    @RequestMapping(value = "/items/batch",method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
    public void deleteExpenseItemsInbatchById(@RequestParam(value = "ids") List<Long> ids) {
        itemService.deleteItemsInBatch(ids);
    }
}
