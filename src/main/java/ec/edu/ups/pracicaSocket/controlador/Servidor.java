/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.pracicaSocket.controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author NALE COMPUTERS
 */
public class Servidor {
    public static void main(String[] args){
        
    //creamos unainstancia que escucha al puerto
        ServerSocket serverSocket=null;
        try {
            //inicializamos el servidor en el puerto 12345
            serverSocket= new ServerSocket(12345);
            System.out.println("Servidor esperando cliente...");
            //bucle infinito para aceptar conexiones
            while(true){
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde: "+clienteSocket.getInetAddress().getHostAddress());
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ClienteHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter salida; //declara un objeto para enviar datos al cliente
    private BufferedReader entrada; //recibe datos de un cliente

    public ClienteHandler(Socket socket) {
        this.clientSocket = socket;
        try {
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        //lee los mensajes del cliente
        try {
            String mensaje= entrada.readLine();
            System.out.println("Cliente dice: " + mensaje); //imprime en la consola del servidor el mensaje enviado por el cliente           
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                entrada.close();
                salida.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
