package com.techvariable.traxpense.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techvariable.traxpense.core.common.Audit;
import com.techvariable.traxpense.core.models.vm.CategoryInputVM;
import com.techvariable.traxpense.core.models.vm.CategoryVM;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author utpal
 * com.techvariable.traxpense.core.models
 */
@Entity
@Table(name = "categories")
@SQLDelete(
        sql = "update categories set is_deleted = 1 where id = ?")
@Where(clause = "is_deleted = 0 OR is_deleted IS NULL")
public class Category extends Audit {
    @Id
    @GenericGenerator(name="per_table_inc" , strategy="increment")
    @GeneratedValue(generator="per_table_inc")
    private long id;

    @NotNull
    private String label;

    @Column(length = 500)
    private String description;

    @JsonIgnore
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public static Category from(CategoryInputVM categoryInputVM){
        return new Category(categoryInputVM.getLabel(), categoryInputVM.getDescription());
    }

    protected Category(){

    }

    public Category(String label, String description){
        this.label = label;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
