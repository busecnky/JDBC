package com.january6;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class TCPGUIClient {

	private JFrame frame;
	private JTextField textField;
	private Socket socket;
	private JEditorPane editorPane;
	private JButton btnClose;
	private JButton btnConnect;

	/**
	 * Launch the application.
	 */
	
	
	//Mail clientta o class boyunca sürekli açıksa girmesi lazım diye do-while yaptık.
	//Ama burda penceremiz sürekli açık olduğu için do while yazmamıza gerek yok.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TCPGUIClient window = new TCPGUIClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TCPGUIClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 694, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(180, 131, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				mesajGonder(textField.getText());
			}
		});
		btnSend.setBounds(372, 131, 117, 29);
		frame.getContentPane().add(btnSend);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(180, 196, 309, 131);
		frame.getContentPane().add(scrollPane);
		
		editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
		
		JButton btnConnect = new JButton("Connect to Server");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					socket = new Socket("localhost", 5000);
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnConnect.setBounds(343, 28, 146, 29);
		frame.getContentPane().add(btnConnect);
		
		JButton btnClose = new JButton("Close Connection");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//1. exit kelimesini server'a göndermeliyim
					mesajGonder("exit");
					
					
					//2. olarakta socket kapatılır.
					socket.close();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		btnClose.setBounds(485, 28, 146, 29);
		frame.getContentPane().add(btnClose);
	}
	
	protected void mesajGonder(String mesaj) {

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			
			String response;
			writer.println(mesaj);
			
			if(!mesaj.equalsIgnoreCase("exit")) {
				response = reader.readLine();
				editorPane.setText(editorPane.getText() + "\n" + response);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
