package com.january5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//Echo Server TCP yazılımı yazalım
//Clienttan hello geldiğinde server --> client'a Echo from server:hello diyerek geri yollar
public class MainServer {

	
	
	public static void main(String[] args) {
		
		try(ServerSocket serverSocket = new ServerSocket(5000)){
			Socket socket = serverSocket.accept();  //Burada serversocket bağlantı bekler. Bağlantı kurulduğunda yeni bir socket(iletişim)
													//oluşturulup yeni oluşturulan socket üzerinden haberleşilir.
													//
													//Yani serverSocket.accept() bağlantı kurulana kadar bekler, client ile
													//bağlantı kurulunca bir haberleşme socketi oluşturur ve onu return eder.
													//Biz bu yeni oluşturulan socket üzerinden veri gönderir ve alırız.
			System.out.println("Client connected");
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
			
			//clienttan exit gelene kadar clienttan ne gelirse echo from server: yazıp o mesajı sonuna eklesin
			
			while (true) {
				String echoString = reader.readLine();
				if (echoString.equalsIgnoreCase("exit")) {
					System.out.println("Server exit...");
					break;
				}
				printWriter.println("Echo from server: " + echoString);
			}
			
			
					
		} catch (IOException e) {
			System.out.println("Server exception: " + e.getMessage());
		}
			
			
		

	}

}
