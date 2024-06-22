package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int portNumber = 8081;

        try (Socket socket = new Socket(serverAddress, portNumber);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the server.");

            System.out.print("Enter your greeting: ");
            String userGreeting = stdIn.readLine();
            out.println(userGreeting);

            String serverResponse = in.readLine();
            System.out.println("Server: " + serverResponse);

            if ("що таке паляниця?".equals(serverResponse)) {
                System.out.print("Enter your answer: ");
                String answer = stdIn.readLine();
                out.println(answer);

                serverResponse = in.readLine();
                System.out.println("Server: " + serverResponse);

                if (serverResponse.startsWith("Current date and time:")) {
                    System.out.println(serverResponse);
                }
            }

            serverResponse = in.readLine();
            System.out.println("Server: " + serverResponse);

        } catch (IOException e) {
            System.err.println("Error during communication: " + e.getMessage());
        }
    }
}
