package com.weiun.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

public class NioClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        InetSocketAddress address = new InetSocketAddress("localhost", 8080);
        socketChannel.connect(address);
        while (true) {
            int select = selector.select(1000);
            if (select > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    SelectionKey selectionKey = iterator.next();
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    if (selectionKey.isConnectable()) {
                        channel.register(selector, SelectionKey.OP_READ);
                        if (channel.isConnectionPending()) {
                            channel.finishConnect();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            byteBuffer.put((LocalDateTime.now() + channel.getLocalAddress().toString() + "连接成功").getBytes());
                            byteBuffer.flip();
                            channel.write(byteBuffer);
                            new Thread(() -> {
                                Scanner scanner = new Scanner(System.in);
                                scanner.useDelimiter("\n");
                                while (true) {
                                    try {
                                        String msg = scanner.nextLine();
                                        byteBuffer.clear();
                                        byteBuffer.put(msg.getBytes());
                                        byteBuffer.flip();
                                        while (byteBuffer.hasRemaining()) {
                                            channel.write(byteBuffer);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    } else if (selectionKey.isReadable()) {
                        buffer.clear();
                        channel.read(buffer);
                        buffer.flip();
                        System.out.println(new String(buffer.array()));
                    }
                    iterator.remove();
                }
            }
        }
    }

}
