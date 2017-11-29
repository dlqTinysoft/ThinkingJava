package com.tinysoft.cn;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 董乐强 on 2017/11/28.
 */
@Table(name = "jpa_ITEM")
@Entity
public class Item {

    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "ITEM_NAME")
    private String itemName;

    @JoinTable(
            name = "ITEM_CATEGORY",
            joinColumns = {@JoinColumn(name = "ITEM_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID",referencedColumnName = "ID")}
    )
    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
