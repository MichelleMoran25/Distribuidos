package cliente;
import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Collections;

public class Cliente1P1 {
  static final String HOST = "localhost";
  static final int PUERTO=5000;
  static Socket sc;
 
    public static void main(String[] args) throws IOException {  
       System.out.println("Iniciando cliente...\n");
       iniciarCliente();
    }

    public static void iniciarCliente() {
        try {            
           sc = new Socket(HOST,PUERTO);
           
           DataOutputStream salida = new  DataOutputStream(sc.getOutputStream());
           DataInputStream entrada = new DataInputStream(sc.getInputStream());
           
           LinkedList<String> nombres = new LinkedList<String>();
           nombres.add("Estela");
           nombres.add("Michelle");
           nombres.add("Mike");
           nombres.add("Eduardo");
           nombres.add("Ana");
           nombres.add("Juanito");
           nombres.add("Maria");
           nombres.add("Ancelmo");
           nombres.add("Carro");
           nombres.add("Pedrito");
           nombres.add("Zeta");
           nombres.add("Wow");
           
           for (String nombre : nombres) {
        	   salida.writeUTF(nombre);
           }
           
           

           for (Object nombre : nombres) {
               System.out.println(nombre);
           }

           
           sc.close();

        }
        catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }
    }
}