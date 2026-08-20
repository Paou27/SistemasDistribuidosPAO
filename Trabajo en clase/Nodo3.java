package sis258.ejercicio2clasesnodos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * Nodo 3 - Procesamiento final
 * Recibe la informacion del Nodo 2, convierte el texto a mayusculas, cuenta
 * las vocales, arma el resumen final y lo envia de vuelta al Nodo 1.
 */
public class Nodo3 {

    // Puerto propio, donde este nodo escucha al Nodo 2
    private static final int PUERTO_NODO3 = 7003;

    // Direccion del Nodo 1 (para devolver el resultado final)
    private static final String HOST_NODO1 = "172.20.10.4";
    private static final int PUERTO_NODO1 = 7001;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PUERTO_NODO3)) {
            System.out.println("Nodo 3 escuchando en el puerto " + PUERTO_NODO3 + "...");

            byte[] buffer = new byte[4096];

            while (true) {
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);

                try {
                    String mensaje = new String(
                            paqueteRecibido.getData(), 0, paqueteRecibido.getLength(), StandardCharsets.UTF_8);
                    System.out.println("Recibido del Nodo 2 -> " + mensaje);

                    String[] partes = mensaje.split("\\|", -1);
                    String texto = partes[0];
                    String cantidadCaracteres = partes[1];
                    String cantidadPalabras = partes[2];
                    String paridad = partes[3];

                    String textoMayusculas = texto.toUpperCase();
                    int cantidadVocales = contarVocales(texto);

                    String resumen =
                            "=== RESUMEN FINAL DEL PROCESAMIENTO ===\n"
                            + "Texto original: " + texto + "\n"
                            + "Cantidad de caracteres: " + cantidadCaracteres + " (" + paridad + ")\n"
                            + "Cantidad de palabras: " + cantidadPalabras + "\n"
                            + "Texto en mayusculas: " + textoMayusculas + "\n"
                            + "Cantidad de vocales: " + cantidadVocales;

                    byte[] datos = resumen.getBytes(StandardCharsets.UTF_8);
                    InetAddress direccionNodo1 = InetAddress.getByName(HOST_NODO1);
                    DatagramPacket paqueteSalida = new DatagramPacket(datos, datos.length, direccionNodo1, PUERTO_NODO1);
                    socket.send(paqueteSalida);

                    System.out.println("Enviado el resumen final al Nodo 1.");
                } catch (RuntimeException e) {
                    // Un mensaje malformado no debe tumbar el servidor: se registra y se sigue escuchando.
                    System.out.println("Mensaje descartado, no se pudo procesar: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static int contarVocales(String texto) {
        String textoMinusculas = texto.toLowerCase();
        int contador = 0;
        for (char c : textoMinusculas.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                contador++;
            }
        }
        return contador;
    }
}
