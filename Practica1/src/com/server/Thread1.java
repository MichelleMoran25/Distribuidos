package com.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Thread1 extends Thread {
	
    private String name;
    static final int PUERTO = 5001;
    static ServerSocket sc;
    static Socket so;

    Thread1(String threadName) {
            name = threadName;
    }	
    
    public void run() {
    	
    	try {
    		
			sc = new ServerSocket(PUERTO);
			so = new Socket();
			
			System.out.println("Waiting connection on " + name + "...");
			
			so = sc.accept();
			
			System.out.println("Client has connected to " + name);
			

			ObjectInputStream inputObject = new ObjectInputStream(so.getInputStream());
			ObjectOutputStream outputObject = new ObjectOutputStream(so.getOutputStream());
			DataOutputStream output= new DataOutputStream(so.getOutputStream());
			DataInputStream input= new DataInputStream(so.getInputStream());
			
			int selection = input.readInt();
			
			if (selection == 1) {
	            
				Object clientNames = inputObject.readObject();
				
				List<String> clientNamesList = new ArrayList();
				clientNamesList.addAll((Collection<? extends String>) clientNames);
				
				Collections.sort(clientNamesList);
				
				outputObject.writeObject(clientNamesList);

														
			}else if (selection == 2) {

	            File file = null;
	            FileReader fr = null;
	            BufferedReader br = null;

	            try {
	            	
	            	Object clientFile = inputObject.readObject();
	            	
	            	
	                file = new File ("prueba.txt");
	                fr = new FileReader (file);
	                br = new BufferedReader(fr);

	                String linea;
	                int contador = 0;

	                while((linea = br.readLine())!=null){
	                    String sarray[] = linea.split(" ");
	                    for (int i = 0; i < sarray.length; i++) {
	                        if (sarray[i].equals("in") == true) {
	                            contador++;
	                        }
	                    }
	                }
	                System.out.println(contador);
	            }

	            catch(Exception e){
	                e.printStackTrace();

	            }finally{

	                try{
	                    if( null != fr ){
	                        fr.close();
	                    }

	                }catch (Exception e2){
	                e2.printStackTrace();
	                }
	            }
			}
            
            
            sc.close();
			
		} catch (IOException e) {
			
			System.out.println("Error 1: "+e.getMessage());
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error 2: "+e.getMessage());		}            	
    }    
}