/*COMP90015 Assignment1  
  Shengqi Zhou 893295 */
package dict.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import static dict.server.Word.jsonToWord;
import static dict.server.Word.wordToJson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;

public class Server {
	
	// Declare the port number
	private static int port = 2019;
	
	// Identifies the user number connected
	private static int counter = 0;
	
	//private static boolean exitServer;

	private static Dictionary dic;

	static{
		dic = new Dictionary();
	}

	public static void main(String[] args) throws IOException
	{
		ServerSocketFactory factory = ServerSocketFactory.getDefault();
		ServerSocket serverSocket;
		try//(ServerSocket server = factory.createServerSocket(port))
		{
			serverSocket = new ServerSocket(port);
			System.out.println("Waiting for client connection-");
		} catch (BindException e) {
			System.out.println("The port is already in use, try another port.");
			return;
		} catch (IllegalArgumentException e2) {
			System.out.println("The port num is out of range, try another port num.");
			return;
		}
				//create a main thread to serve clients
				Thread mainThread = new Thread(() -> 
					serverMainThread(serverSocket));
				mainThread.start();
		
	}
	
	//a main thread of the server
	private static void serverMainThread(ServerSocket serverSocket) {
		while (true) {
			// Connect and get client socket
			Socket client;
			try {
				client = serverSocket.accept();
				counter++;
				Thread t = new Thread(() -> 
				{
					try {
						serveClient(client);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}); // (lambda parameter -> lambda body)
				t.start();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	//synchronize accesses
	/*public class ThreadSyn implements Runnable{
	    private  volatile  int count = 0;
	    @Override
	    public void run() {
	        synchronized(this) {
	            for (int i = 0; i < 5; i++) {
	                try {
	                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
	                    Thread.sleep(100);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	}*/

	//create a new thread to connect every client and synchronize access to the dictionary
	//@SuppressWarnings("unused")
	private static void serveClient(Socket client) throws Exception {
		try(Socket clientSocket = client) 
		{
			// Input stream
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			// Output Stream
			DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
			/*
			ThreadSyn threadSynOne = new ThreadSyn();
		    Thread thread1 = new Thread(threadSynOne,"ThreadSynOne");
		    Thread thread2 = new Thread(threadSynOne,"ThreadSynTwo");
		    thread1.start();
		    thread2.start();*/
	        boolean hasData = true;
			while(hasData) {
				if (input.available() > 0) {
					String inputStr = input.readUTF();
					Word word = jsonToWord(inputStr);
					String goBack;
					String selection = word.getInstruction();
					
					//switch-case handle different situations
					if (selection == null) {
						word.setMsg("Selection cannot be null.");
						return;
					}
					switch(selection) {
					case "find":
						goBack = dic.find(word.getKey());
						if(goBack == null) {
							word.setMsg("It does not exist.");
						}
						else {
							word.setValue(goBack);
						}
						break;
					case "add":
						goBack = dic.add(word.getKey(), word.getValue());
						//word.setMsg(goBack);
						if (goBack == null) {
							word.setValue("It has been added");
							System.out.println("It has been added");
							//word.setMsg("It has been added.");
						}
						else {
							//word.setKey(null);
							//word.setValue(null);
							word.setMsg(goBack);        
							//System.out.println(goBack);
						}
						break;
					case "remove":
						goBack = dic.remove(word.getKey());
						if(goBack == null) {
							word.setValue("It has been removed.");
						}
						else {
							word.setMsg(goBack);
						}
						break;
					default:
						System.out.println("Unknown command. Please try again.");
						break;
					}
					output.writeUTF(wordToJson(word));
					output.flush();
				}

			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}