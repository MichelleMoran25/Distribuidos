package com.server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Thread2 extends Thread {
	
    private String name;
    static final int PUERTO = 5002;
    static ServerSocket sc;
    static Socket so;

    Thread2(String threadName) {
            name = threadName;
    }	
    
    public void run() {
    	
    	try {
    		
			sc = new ServerSocket(PUERTO);
			so = new Socket();
			
			System.out.println("Waiting connection on " + name + "...");
			
			so = sc.accept();
			
			System.out.println("Client has connected to " + name);
			

			ObjectInputStream inObject = new ObjectInputStream(so.getInputStream());
			ObjectOutputStream outObject = new ObjectOutputStream(so.getOutputStream());
			DataOutputStream out= new DataOutputStream(so.getOutputStream());
			DataInputStream in= new DataInputStream(so.getInputStream());
			
			int selection = in.readInt();
			System.out.println(selection);
			
			if (selection == 1) {
	            
				Object clientNames = inObject.readObject();
				
				List<String> clientNamesList = new ArrayList();
				clientNamesList.addAll((Collection<? extends String>) clientNames);
				
				Collections.sort(clientNamesList);
				
				outObject.writeObject(clientNamesList);

														
			}else if (selection == 2) {
				
				System.out.println("llega aqui");

	            File file = null;
	            FileReader fr = null;
	            BufferedReader br = null;

	            try {
	            	
	            	byte data[] = new byte[2048]; // Here you can increase the size also which will receive it faster
	                
	            	FileOutputStream fileOut = new FileOutputStream("receivedTest.txt");
	                InputStream fileIn = so.getInputStream();
	                BufferedOutputStream fileBuffer = new BufferedOutputStream(fileOut);
	                
	                int count;
	                int sum = 0;
	                
	                while ((count = fileIn.read(data)) > 0) {
	                	
	                    sum += count;
	                    fileBuffer.write(data, 0, count);
	                    System.out.println("Data received : " + sum);
	                    fileBuffer.flush();
	                }
	                
	                System.out.println("File Received...");
	                
	            	

	            	
	                /*file = new File ("test.txt");
	                fr = new FileReader (fileIn);
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
	                System.out.println(contador);*/
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