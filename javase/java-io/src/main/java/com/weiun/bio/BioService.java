package com.weiun.bio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioService {

    private boolean on = true;

    private ConcurrentHashMap<String, BioClientHandle> handleMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        new BioService().start(8080);
    }

    public void start(int port) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ServerSocket serverSocket = new ServerSocket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        serverSocket.bind(inetSocketAddress);
        int i = 0;
        while (on) {
            i++;
            Socket socket = serverSocket.accept();
            String name = String.format("Client%s", i);
            BioClientHandle handle = new BioClientHandle(this, name, socket);
            handleMap.put(name, handle);
            executorService.execute(handle);
        }
    }


    public void close(String name) {
        handleMap.remove(name);
    }

    public String list() {
        StringBuilder sb = new StringBuilder();
        sb.append("已连接客户端");
        handleMap.keySet().forEach(s -> {
            sb.append("\n");
            sb.append(s);
        });
        return sb.toString();
    }

    public void cast(String msg) {
        // 广播消息
        handleMap.values().forEach(handle -> {
            try {
                handle.sendMsg(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void sendMsg(String name, String target, String msg) {
        BioClientHandle handle = handleMap.get(target);
        if (handle == null) {
            Optional.ofNullable(handleMap.get(name)).ifPresent(h -> h.sendMsg(target + "未上线"));
        } else {
            handle.sendMsg(msg);
        }
    }
}
