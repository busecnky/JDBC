package com.january6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MainClientUDP {

	public static void main(String[] args) {
		
		try {
			InetAddress address = InetAddress.getLocalHost();
			DatagramSocket datagramSocket = new DatagramSocket();
			
			Scanner scanner = new Scanner(System.in);
			String echoString;
			
			//Ben exit yazana kadar karşı tarafa mesaj göndersin ve echo'sunu alsın göstersin
			
			do {
				echoString = scanner.nextLine();
				
				byte[] buffer = echoString.getBytes();
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
				datagramSocket.send(packet);
				
				
				// Server tarafından gelen echo mesajını alıp görüntüleyelim..					
				byte[] buffer2 = new byte[50];
				packet = new DatagramPacket(buffer2, buffer2.length);
				datagramSocket.receive(packet);
				String receivedText = new String(buffer2, 0, packet.getLength()); 
				System.out.println("Text received is : " + receivedText);		
				
				
			} while (!echoString.equalsIgnoreCase("exit"));
			
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
