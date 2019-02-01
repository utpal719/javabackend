package com.techvariable.traxpense.core.models.vm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techvariable.traxpense.core.models.Category;
import com.techvariable.traxpense.core.models.Item;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author utpal
 * com.techvariable.traxpense.core.models.vm
 */
public class ItemVM implements Serializable {
    private long id;

    @NotNull
    @NotEmpty
    private String heading;

    private String notes;

    @NotNull
    private double cost;

    private CategoryVM category;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createdOn;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date updatedOn;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date spendDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @NotNull
    public CategoryVM getCategory() {
        return category;
    }

    public void setCategory(CategoryVM category) {
        this.category = category;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    ItemVM(){}

    ItemVM(long id, String heading, String notes, double cost, Date spendDate, Date createdOn, Date updatedOn, Category category){
        this.id = id;
        this.heading = heading;
        this.notes = notes;
        this.cost = cost;
        this.category = CategoryVM.from(category);
        this.spendDate = spendDate;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public static ItemVM from(Item item){
        return new ItemVM(item.getId(), item.getHeading(), item.getNotes(), item.getCost(), item.getSpendDate(), item.getCreatedOn(), item.getUpdatedOn(), item.getCategory());
    }


    public Date getSpendDate() {
        return spendDate;
    }

    public void setSpendDate(Date spendDate) {
        this.spendDate = spendDate;
    }
}
