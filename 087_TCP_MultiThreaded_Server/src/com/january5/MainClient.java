package com.january5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MainClient {

	public static void main(String[] args) {
		

		try(Socket socket = new Socket("localhost", 5000)){
			
			//soket üzerinden okuma ve yazma yapabilmek için bir tane reader bir tane writer nesnesi oluşutuyoruz.
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			
			
			Scanner scanner = new Scanner(System.in);
			String echoString;
			String response;
			
			do {
				System.out.println("Lütfen gönderilecek metni yazınız: ");
				echoString = scanner.nextLine();
				writer.println(echoString);
				
				if(!echoString.equalsIgnoreCase("exit")) {
					response = reader.readLine();
					System.out.println(response);
				}
				
				
			} while (!echoString.equalsIgnoreCase("exit"));
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
