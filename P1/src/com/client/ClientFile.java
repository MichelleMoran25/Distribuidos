package com.client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class ClientFile {
	
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
			
			File file = new File("test.txt");
			byte[] data = new byte[2048];
			
			FileInputStream fileStream = new FileInputStream(file);
            BufferedInputStream fileBuffer = new BufferedInputStream(fileStream);
            OutputStream outSocket = sc.getOutputStream();
            DataOutputStream out = new  DataOutputStream(sc.getOutputStream());
            
			out.writeInt(2);
            
            int count;
            
            while ((count = fileBuffer.read(data)) > 0) {
            	
                System.out.println("Data Sent : " + count);
                outSocket.write(data, 0, count);
                outSocket.flush();
                
            }
            
            outSocket.close();
            fileBuffer.close();
            fileStream.close();
			
			sc.close();
			
		}
		
		catch (Exception e){
			
			System.out.println("Error " + e.getMessage());
			//Añadiendo un comentario
			
		}
	}
}
