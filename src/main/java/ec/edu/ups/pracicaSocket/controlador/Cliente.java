/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.pracicaSocket.controlador;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author NALE COMPUTERS
 */
public class Cliente {

    Socket socket;
    PrintWriter out;
    BufferedReader in;
    BufferedReader stdIn;

    public Cliente(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("¡Bienvenido al chat!");
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void enviarMensaje(String mensaje) throws IOException {
        out.println(mensaje);
        String respuesta = in.readLine();
        System.out.println("Servidor dice: " + respuesta);
        if (mensaje.equalsIgnoreCase("salir")) {
            cerrarConexion();
        }
    }

    private void cerrarConexion() throws IOException {
        out.close();
        in.close();
        socket.close();

    }
}
