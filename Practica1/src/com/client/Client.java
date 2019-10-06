package com.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class Client {
	
	  static final String HOST = "localhost";
	  static final int PORT=5000;
	  static Socket sc;

	public static void main(String[] args) {
		
		System.out.println("Starting client...\n");
		iniciarCliente();
		
	}
	
	public static void iniciarCliente() {
		
		try {
			
			//sc = new Socket(HOST, PORT);
			
			DataOutputStream output = new  DataOutputStream(sc.getOutputStream());
			DataInputStream input = new DataInputStream(sc.getInputStream());
			
			LinkedList<String> names = new LinkedList<String>();
			names.add("Estela");
			names.add("Michelle");
			names.add("Mike");
			names.add("Eduardo");
			names.add("Ana");
			names.add("Juanito");
			names.add("Maria");
			names.add("Ancelmo");
			names.add("Carro");
			names.add("Pedrito");
			names.add("Zeta");
			names.add("Wow");
			
			for (String name : names) {
				
				System.out.println(name);
				
			}
			
			sc.close();
			
		}
		
		catch (Exception e){
			
			System.out.println("Error " + e.getMessage());
			
		}
	}
}
