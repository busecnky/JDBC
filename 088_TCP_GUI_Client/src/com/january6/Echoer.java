package com.january6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread {

	private Socket socket;
	private int threadID;
	
	public Echoer(Socket socket, int id) {
		this.socket = socket;
		this.threadID = id;
		System.out.println("Client tarafının port numarası: " + this.socket.getPort());  
		System.out.println("Server tarafının port numarası: " + this.socket.getLocalPort());  
	}
	
	
	public void run() {
		System.out.println("Server bir client'a bağlandı ve haberleşme başladı...");
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
			
			while (true) {
				String echoString = reader.readLine();
				if (echoString.equalsIgnoreCase("exit")) {
					System.out.println("Server-thread exit...");
					break;
				}
				System.out.println("Thread ID: " + this.threadID + " " + echoString);
				printWriter.println("Echo from server: " + echoString);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		
	}
	
}
