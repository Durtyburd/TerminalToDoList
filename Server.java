package com.company;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class Server {
    private Gson gson = new Gson();
    private Store listItems;

    public Server(Store store) throws Exception {
        listItems = store;
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 420), 0);

        server.createContext("/list", (HttpExchange t) -> {
            if (!t.getRequestMethod().equals("GET")) {
                t.sendResponseHeaders(405, 0);
                t.close();
                return;
            }
            String[] items = listItems.showItems();
            Task[] response = Arrays.stream(items)
                    .map(l -> new Task(l))
                    .toArray(Task[]::new);
            String jsonResponse = gson.toJson(response, Task[].class);
            t.sendResponseHeaders(200, jsonResponse.length());
            OutputStream os = t.getResponseBody();
            os.write(jsonResponse.getBytes());
            os.close();
        });

        server.createContext("/add", (HttpExchange t) -> {
            Task task = gson.fromJson(new InputStreamReader(t.getRequestBody()), Task.class);
            if (!t.getRequestMethod().equals("POST")) {
                t.sendResponseHeaders(405, 0);
                t.close();
                return;
            }
            if (task.name == null || task.name.length() == 0) {
                t.sendResponseHeaders(400, 0);
                t.close();
                return;
            }
            listItems.addItem(task.name);
            t.sendResponseHeaders(200, 0);
            t.close();
        });

        server.createContext("/delete", (HttpExchange t) -> {
            Task task = gson.fromJson(new InputStreamReader(t.getRequestBody()), Task.class);
            if (!t.getRequestMethod().equals("DELETE")) {
                t.sendResponseHeaders(405, 0);
                t.close();
                return;
            }
            if (task.name == null || task.name.length() == 0) {
                t.sendResponseHeaders(400, 0);
                t.close();
                return;
            }
            listItems.removeItem(task.name);
            t.sendResponseHeaders(200, 0);
            t.close();
        });

        server.setExecutor(null);
        server.start();
    }
}
