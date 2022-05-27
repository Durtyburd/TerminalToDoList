package com.company;

public class Main {
    public static void main(String[] args) throws Exception {
        Store listItems = StoreFactory.getStore(args[0]);
        if(args[1].equals("cli")){
            new Cli(listItems);
        } else if (args[1].equals("server")){
            new Server(listItems);
        }
    }
}


