package chatApp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port = 0;// initializing port for the server
    private ServerSocket serverSocket = null;//serverSocket
    private Socket socket = null;
    private BufferedReader input = null;
    private BufferedWriter writer = null;
    private String messageFromClient = "";

    public Server(int port){
        //sets  for the server and initiate Socket from the server socket and input streams to read information from
        // the network created
        this.port = port;
        try{
            serverSocket = new ServerSocket(this.port);//creating serverside socket with specific socket

            System.out.println("Server side port created \nwaiting to receive client request...");

            socket = serverSocket.accept(); // accepting client request and forming link
            System.out.println("client request accepted");

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));// reader from network or socket
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));// writes to network
        }catch(IOException ioE){
            ioE.printStackTrace();
        }
    }

    private void writeMessage()throws IOException{
        // scans the console and writes message to network
        DataInputStream consoleScan = new DataInputStream(System.in);
        String messageToClient = consoleScan.readUTF();
        writer.write(messageToClient);
    }

    private void readMessage() throws IOException{
        //receives message from network and prints to console
            String read = input.readLine();
            if(!read.isEmpty())messageFromClient = read;
        System.out.println(messageFromClient);
    }

    private  void closeConnection() {

        //to close every connection that has been made and every I/O stream opened
        try {
            serverSocket.close();
            socket.close();
            input.close();
        } catch (IOException ioE) {
            System.out.println(ioE.getMessage());
        }
    }
    public void run(){
        try{while(!messageFromClient.equals("Over")){
                readMessage();
                writeMessage();
            }
            closeConnection();
        }catch(IOException ioE){
                ioE.printStackTrace();
            }
    }
    public static void main(String[] args) throws Exception {
        new Server(1500);
    }
}
