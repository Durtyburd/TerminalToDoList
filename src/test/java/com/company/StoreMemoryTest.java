package com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StoreMemoryTest {
    @Test
    void startsEmpty() {
        StoreMemory m = new StoreMemory();
        String[] items = m.showItems();
        assertEquals(0, items.length);
    }

    @Test
    void addsItem() {
        StoreMemory m = new StoreMemory();
        m.addItem("abc");
        String[] items = m.showItems();
        assertEquals(1, items.length);
        assertEquals("abc", items[0]);
    }

    @Test
    void deletesItem() {
        StoreMemory m = new StoreMemory();
        m.addItem("abc");
        String[] items = m.showItems();
        assertEquals(1, items.length);

        m.removeItem("abc");
        items = m.showItems();
        assertEquals(0, items.length);
    }
}