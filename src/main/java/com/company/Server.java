package com.company;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class Server {
    private Gson gson = new Gson();
    private Store listItems;

    private boolean maybeServeCORS(HttpExchange t) throws IOException {
        t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        if (t.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            t.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
            t.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
            t.sendResponseHeaders(204, -1);
            return true;
        }
        return false;
    }

    private HttpHandler attempt(HttpHandler handler) {
        return (HttpExchange t) -> {
            try {
                handler.handle(t);
            } catch (Exception e) {
                System.out.println(e);
                String message = "Internal server error";
                t.sendResponseHeaders(500, message.length());
                OutputStream os = t.getResponseBody();
                os.write(message.getBytes());
                os.close();
            }
        };
    }
    public Server(Store store) throws Exception {
        listItems = store;
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 420), 0);

        server.createContext("/", attempt((HttpExchange t) -> {
           String htmlFile = FileTasks.readFile("src/JSToDoListFront/public/index.html");
            t.sendResponseHeaders(200, htmlFile.length());
            OutputStream os = t.getResponseBody();
            os.write(htmlFile.getBytes());
            os.close();
            System.out.println("response sent");
        }));

        server.createContext("/public", attempt((HttpExchange t) -> {
            String path = t.getRequestURI().getPath();
            String rootPath = "src/JSToDoListFront";
            File combinedPaths = new File(rootPath + path);
            Path newPath = Path.of(path).normalize();
            File directoryPath = new File("src/JSToDoListFront/public");
            String[] contents = directoryPath.list();
            for(int i = 0; i < contents.length; i++) {
                if(Objects.equals(String.valueOf(newPath), "\\public\\" + contents[i])) {
                    String jsFile = FileTasks.readFile(String.valueOf(combinedPaths));
                    byte[] bytes = jsFile.getBytes(StandardCharsets.UTF_8);
                    t.sendResponseHeaders(200, bytes.length);
                    OutputStream os = t.getResponseBody();
                    os.write(jsFile.getBytes());
                    os.close();
                    System.out.println("response sent");
                    return;
                }
            }
                t.sendResponseHeaders(404, 0);
                t.close();
        }));

        server.createContext("/list", (HttpExchange t) -> {
            if (maybeServeCORS(t)) { return; }
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
            if (maybeServeCORS(t)) { return; }
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
            if (maybeServeCORS(t)) { return; }
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