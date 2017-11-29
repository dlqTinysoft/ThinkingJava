package com.tinysoft.cn;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 董乐强 on 2017/11/28.
 */

@Table(name = "jpa_category")
@Entity
public class Category {

    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    //放弃维护关联关系
    @ManyToMany(mappedBy = "categories")
    private Set<Item> items = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
