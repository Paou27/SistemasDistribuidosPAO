/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Primer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Paola Alvarez Oliva
 */
public class Operadora extends UnicastRemoteObject{
       /** implements IOperadora{
    
    public Operadora() throws RemoteException{
        super();
    } 
    */
    @Override
    public RespuestaCuenta ConsultarCuentas(
            String ci, String nombres, String apellidos)
            throws RemoteException{
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        cuentas.addAll(consultarBNB(ci));

        return new RespuestaCuenta(
                false,
                "Consulta realizada",
                cuentas
        );
    }
    
    @Override
    public void Congelar(Cuenta cuenta, double monto)
            throws RemoteException{
        //envia al banco la orden en este caso solo a un banco
    }
    private ArrayList<Cuenta> consultarBNB(String ci){
        //cliente hacia bnb
        return new ArrayList<>();
    }
   
  }
}
