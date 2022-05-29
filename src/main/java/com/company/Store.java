package com.company;

import java.io.IOException;

public interface Store {

    void addItem(String item);

    void removeItem(String item);

    void removeAll() throws IOException;

    String[] showItems();
}
