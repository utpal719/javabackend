package com.techvariable.traxpense.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techvariable.traxpense.core.common.Audit;
import com.techvariable.traxpense.core.models.vm.ItemInputVM;
import com.techvariable.traxpense.core.models.vm.ItemVM;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author utpal
 * com.techvariable.traxpense.core.models
 */
@Entity
@Table(name = "items")
@SQLDelete(
        sql = "update items set is_deleted = 1 where id = ?")
@Where(clause = "is_deleted = 0 OR is_deleted IS NULL")
public class Item extends Audit {
    @Id
    @GenericGenerator(name="per_table_inc" , strategy="increment")
    @GeneratedValue(generator="per_table_inc")
    private long id;

    @NotNull
    private String heading;

    @Lob
    private String notes;

    private double cost;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "spend_date")
    private Date spendDate;

    @JsonIgnore
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    private Category category;

    protected Item(){

    }

    public Item(String heading, String notes, double cost, Date spendDate, Category category){
        this.heading = heading;
        this.notes = notes;
        this.cost = cost;
        this.spendDate = spendDate;
        this.category = category;
    }

    public static Item from(ItemInputVM itemInputVM, Category category){
        return new Item(itemInputVM.getHeading(), itemInputVM.getNotes(), itemInputVM.getCost(), itemInputVM.getSpendDate(), category);
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getSpendDate() {
        return spendDate;
    }

    public void setSpendDate(Date spendDate) {
        this.spendDate = spendDate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", heading='" + heading + '\'' +
                ", cost=" + cost +
                '}';
    }
}
