/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.practicaSocket.modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author NALE COMPUTERS
 */
public class ClienteHandler extends Thread {

    private Socket clientSocket;
    private PrintWriter salida; //declara un objeto para enviar datos al cliente
    private BufferedReader entrada; //recibe datos de un cliente

    public ClienteHandler(Socket socket,String mensaje) {
        this.clientSocket = socket;
        try {
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            run(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(String mensaje ){
        //lee los mensajes del cliente
        
        System.out.println("Cliente dice: " + mensaje); //imprime en la consola del servidor el mensaje enviado por el cliente
        salida.println("Servidor dice: " + mensaje); //envia el mismo mensaje de cuenta al cliente
        try {
            entrada.close();
            salida.close();
            clientSocket.close();            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    }

