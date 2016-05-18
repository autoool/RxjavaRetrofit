package com.techidea.dblibrary.model;

import android.content.ContentValues;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by zchao on 2016/4/5.
 */
public class ListItem {

    private long id;
    private String name;
    private Item item1;
    private Item item2;
    private List<Item> items;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getItem1() {
        return item1;
    }

    public void setItem1(Item item1) {
        this.item1 = item1;
    }

    public Item getItem2() {
        return item2;
    }

    public void setItem2(Item item2) {
        this.item2 = item2;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
