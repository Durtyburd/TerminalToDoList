package com.company;

import java.util.ArrayList;

public class StoreMemory implements Store {
    private final ArrayList<String> listItems = new ArrayList<>();

    public void addItem(String item){
        listItems.add(item);
    }

    public void removeItem(String item){

    }

    public String[] showItems() {
        return listItems.toArray(new String[0]);
    }

    public void removeAll() {listItems.clear();}
}


