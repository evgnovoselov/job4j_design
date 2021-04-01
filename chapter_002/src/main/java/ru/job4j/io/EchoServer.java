package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Сервер эхо.
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
                    String answer = "";
                    String str = in.readLine();
                    while (str != null && !str.isEmpty()) {
                        if (str.startsWith("GET")) {
                            answer = str.substring(str.indexOf("msg=") + 4, str.indexOf(" HTTP"));
                            if (answer.equals("Exit")) {
                                isActive = false;
                            }
                        }
                        System.out.println(str);
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK \r\n\r\n".getBytes());
                    if (isActive) {
                        out.write(answer.getBytes());
                    } else {
                        out.write("Goodbye".getBytes());
                    }
                }
            }
        }
    }
}
