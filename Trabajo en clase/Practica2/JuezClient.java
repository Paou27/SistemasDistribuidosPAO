package cliente;

import modelo.Cuenta;
import modelo.RespuestaCuenta;
import servidor.IJusticia;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class JuezClient {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            IJusticia justicia = (IJusticia) reg.lookup("ServidorJusticia");

            // Datos del caso de prueba obligatorio[cite: 1]
            String ci = "11021654";
            String nombres = "Juan Perez";
            String apellidos = "Segovia";

            System.out.println("Enviando consulta judicial...");
            RespuestaCuenta resultado = justicia.consultarCuentas(ci, nombres, apellidos);

            if (!resultado.isError()) {
                System.out.println("\n--- RESULTADO DE CUENTAS ENCONTRADAS ---");
                for (Cuenta c : resultado.getCuentas()) {
                    System.out.println("Banco: " + c.getBanco() + " | Nro Cuenta: " + c.getNrocuenta() + " | Saldo: " + c.getSaldo());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}