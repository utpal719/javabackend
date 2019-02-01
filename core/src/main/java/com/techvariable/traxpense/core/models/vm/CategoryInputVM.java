package com.techvariable.traxpense.core.models.vm;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author utpal
 * com.techvariable.traxpense.core.models.vm
 */
public class CategoryInputVM implements Serializable {
    @NotNull
    @NotEmpty
    private String label;
    private String description;

    CategoryInputVM(){}

    public CategoryInputVM(String label, String description){
        this.label = label;
        this.description = description;
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
}
