package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Server {

    public static void main(String[] args) {
        int portNumber = 8081;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server started. Listening on port " + portNumber);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String clientMessage = in.readLine();
            System.out.println("Client greeting: " + clientMessage);

            if (containsRussianLetters(clientMessage)) {
                out.println("що таке паляниця?");

                String response = in.readLine();
                if ("Клубника".equalsIgnoreCase(response.trim())) {

                    LocalDateTime now = LocalDateTime.now();
                    out.println("Current date and time: " + now);
                } else {
                    out.println("Incorrect answer. Disconnecting.");
                    clientSocket.close();
                    return;
                }
            } else {
                out.println("Привіт!");
            }

            out.println("До побачення!");

        } catch (IOException e) {
            System.err.println("Error during communication: " + e.getMessage());
        }
    }

    private static boolean containsRussianLetters(String text) {
        return text.matches(".*[а-яА-Я].*");
    }
}