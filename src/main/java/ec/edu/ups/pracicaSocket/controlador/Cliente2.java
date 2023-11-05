/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.pracicaSocket.controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author NALE COMPUTERS
 */
public class Cliente2 {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;

        try (
            Socket socket = new Socket(hostname, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
            System.out.println("¡Bienvenido al chat! Escribe 'salir' para salir.");
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Servidor dice: " + in.readLine());
                if (userInput.equalsIgnoreCase("salir")) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("No se puede encontrar el host: " + hostname);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida para la conexión con " + hostname);
        }
    }
}
