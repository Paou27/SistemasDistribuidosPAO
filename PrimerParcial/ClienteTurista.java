/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Primer;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import java.util.Scanner;

/**
 *
 * @author Paola Alvarez Oliva
 */
public class ClienteTurista {

    public static void main(String[] args) {
       int port = 5002;
        try {
            Socket client = new Socket("10.230.2.123", port);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
           
            Scanner sc = new Scanner(System.in);
            
            System.out.print("Introduce una cadena: ");
            String mensaje = sc.nextLine();
           
            toServer.println(mensaje);
            
            String result = fromServer.readLine();
            System.out.println("Cadena invertida: " + result);

            client.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
