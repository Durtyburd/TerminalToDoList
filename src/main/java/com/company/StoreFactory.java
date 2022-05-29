package com.company;

public class StoreFactory {
    public static Store getStore(String storeMethod) throws Exception {
        switch (storeMethod) {
            case "filesystem":
                System.out.println("Using file system as storage");
                return new StoreFileSystem();
            case "memory":
                System.out.println("Using RAM as storage");
                return new StoreMemory();
            default: throw new Exception("Unknown store method: " + storeMethod);
        }
    }
}
