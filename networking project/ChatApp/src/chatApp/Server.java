package chatApp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port = 0;// initializing port for the server
    private ServerSocket serverSocket = null;//serverSocket
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream writer = null;
    private String messageFromClient = "";
    private String messageToClient = ""; 

    public Server(int port){
        //sets  for the server and initiate Socket from the server socket and input streams to read information from
        // the network created
        this.port = port;
        try{
            serverSocket = new ServerSocket(this.port);//creating serverside socket with specific socket

            System.out.println("Server side port created \nwaiting to receive client request...");

            socket = serverSocket.accept(); // accepting client request and forming link
            System.out.println("client request accepted");

            input = new DataInputStream(socket.getInputStream());//reader from network or socket
            writer = new DataOutputStream(socket.getOutputStream());// writes to network
        }catch(IOException ioE){
            ioE.printStackTrace();
        }
    }

    private void writeMessage() throws IOException{
        // scans the console and writes message to network
        
        //input stream to read from console/terminal
        BufferedReader consoleScan = new BufferedReader(new InputStreamReader(System.in));
        messageToClient = consoleScan.readLine();
        
        //writes messageToClient onto the network if it isn't whiteSpace only
        if (!messageToClient.isBlank()){
            writer.writeUTF(messageToClient);
        }
        messageToClient = "";
    }

    private void readMessage() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        //receives message from network and prints to console
        messageFromClient = reader.readLine();
        
        //checks whether the read is only whitespace and if not assigns to messageFromClient and prints on console
        if(!messageFromClient.isBlank()){
            System.out.println("Client: " + messageFromClient);
        }
        messageFromClient = "";//resetting message from client to
    }

    private  void closeConnection() throws IOException {
        //to close every connection that has been made and every I/O stream opened
            serverSocket.close();
            socket.close();
            input.close();
    }
    public void run(){
            Thread messageWriter = new MessageWriter();
            Thread messageReceiver = new MessageReceiver();

            messageReceiver.start();
            messageWriter.start();
            
            while(true){
                if(messageFromClient.equals("Over")){
                    try {
                        closeConnection();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
    }

    public static void main(String[] args){
        new Server(1500).run();
    }

    private class MessageReceiver extends Thread{
        @Override
        public void run(){
            try {
                while(true)readMessage();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private class MessageWriter extends Thread{
        @Override
        public void run(){
            try{
                while(true)writeMessage();
            }catch(IOException ioe){
                System.out.println(ioe.getMessage());
            }
        }
    }
}
