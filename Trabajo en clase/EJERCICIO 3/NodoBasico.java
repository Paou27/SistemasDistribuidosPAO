package bo.edu.usfx.jgroups;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ObjectMessage;
import org.jgroups.Receiver;
import org.jgroups.View;

public class NodoBasico {
    public static void main(String[] args) throws Exception {
        // Asigna un nombre personalizado pasado como argumento, o genera uno aleatorio
        String nombre = args.length > 0 ? args[0] : "nodo-" + (int) (Math.random() * 100);
        
        JChannel canal = new JChannel(); // Utiliza udp.xml por defecto
        canal.name(nombre); // Asigna un nombre lógico al miembro
        
        // Define cómo reaccionar a cambios en el grupo y mensajes recibidos
        canal.setReceiver(new Receiver() {
            @Override
            public void viewAccepted(View vista) {
                System.out.println("** Nueva vista: " + vista);
            }

            @Override
            public void receive(Message msg) {
                System.out.println("[" + msg.getSrc() + "] " + msg.getObject());
            }
        });

        // Se une al grupo (cluster) con el nombre dado
        canal.connect("ClusterSIS258"); 
        
        System.out.println("Conectado como " + canal.getAddress()
                + " | coordinador: " + canal.getView().getCoord());

        // Envía 5 mensajes multicast con una pausa de 3 segundos
        for (int i = 1; i <= 5; i++) {
            // Un destino null envía el mensaje a TODOS los miembros del grupo
            canal.send(new ObjectMessage(null, "Hola #" + i + " desde " + nombre));
            Thread.sleep(3000);
        }

        canal.close(); // Sale del grupo ordenadamente
    }
}