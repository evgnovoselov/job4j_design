package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Сервер ожидающий клиента.
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean isActive = true;
            while (isActive && !server.isClosed()) {
                Socket socket = server.accept();
                try (
                        OutputStream out = socket.getOutputStream();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                ) {
                    String str = in.readLine();
                    while (!str.isEmpty()) {
                        if (str.startsWith("GET") && str.contains("msg=Exit")) {
                            isActive = false;
                        }
                        System.out.println(str);
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK \r\n\r\n".getBytes());
                    out.write("Hello, my dear!".getBytes());
                }
            }
        }
    }
}
