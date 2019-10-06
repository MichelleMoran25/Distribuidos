package com.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class ClientList {
	
	  static final String HOST = "localhost";
	  static final int PORT=5001;
	  static Socket sc;

	public static void main(String[] args) {
		
		System.out.println("Starting client...\n");
		iniciateServer();
		
	}
	
	public static void iniciateServer() {
		
		try {
			
			sc = new Socket(HOST, PORT);
			
			DataOutputStream out = new  DataOutputStream(sc.getOutputStream());
			ObjectOutputStream outObject = new ObjectOutputStream(sc.getOutputStream());
			ObjectInputStream inObject = new ObjectInputStream(sc.getInputStream());
			
			LinkedList<String> names = new LinkedList<String>();
			
			System.out.println("Original List: ");
			names.add("Estela");
			names.add("Michelle");
			names.add("Mike");
			names.add("Eduardo");
			names.add("Ana");
			names.add("Juanito");
			names.add("Maria");
			names.add("Ancelmo");
			names.add("Caro");
			names.add("Pedrito");
			names.add("Zaide");
			names.add("Wiliam");
			names.add("Elisa");
									
			System.out.println(names);
			
			out.writeInt(1);
			outObject.writeObject(names);
			
			System.out.println("\nOrdered List:");
			Object orderedNameList = inObject.readObject();
			System.out.println(orderedNameList);
			
			sc.close();
			
		}
		
		catch (Exception e){
			
			System.out.println("Error " + e.getMessage());
			
		}
	}
}