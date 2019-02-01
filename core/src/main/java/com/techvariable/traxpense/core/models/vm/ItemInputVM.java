package com.techvariable.traxpense.core.models.vm;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author utpal
 * com.techvariable.traxpense.core.models.vm
 */
public class ItemInputVM implements Serializable {

    @NotNull
    @NotEmpty
    private String heading;

    private String notes;

    @NotNull
    private double cost;

    private long categoryId;

    private Date spendDate;

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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public Date getSpendDate() {
        return spendDate;
    }

    public void setSpendDate(Date spendDate) {
        this.spendDate = spendDate;
    }
}
