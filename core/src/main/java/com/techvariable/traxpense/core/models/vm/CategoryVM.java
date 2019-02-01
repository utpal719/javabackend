package com.techvariable.traxpense.core.models.vm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techvariable.traxpense.core.models.Category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author utpal
 * com.techvariable.traxpense.core.models.vm
 */
public class CategoryVM implements Serializable {
    private long id;

    @NotEmpty
    @NotNull
    private String label;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createdOn;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date updatedOn;

    public CategoryVM(){}

    public CategoryVM(long id){
        this.id = id;
    }

    public CategoryVM(long id, String label, String description, Date createdOn, Date updatedOn) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public static CategoryVM from(Category category){
        return new CategoryVM(category.getId(), category.getLabel(), category.getDescription(), category.getCreatedOn(), category.getUpdatedOn());
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
}
