/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Primer;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author Paola Alvarez Oliva
 */
public interface IOperadora extends Remote {
    
    RespuestaCuenta ConsultarCuentas(
            String ci,
            String nombres,
            String apellidos
    )throws RemoteException;
    
    void congelar(Cuenta cuenta, double monto)
            throws RemoteException;
}
