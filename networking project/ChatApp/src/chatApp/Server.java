package chatApp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port = 0;// initializing port for the server
    private ServerSocket serverSocket = null;//serverSocket
    private Socket socket = null;
    private DataInputStream input = null;

    public Server(int port){
        //sets  for the server and initiate Socket from the server socket and input streams to read information from
        // the network created
        this.port = port;
        try{
            serverSocket = new ServerSocket(this.port);//creating serverside socket with specific socket

            System.out.println("Server side port created \nwaiting to receive client request...");

            socket = serverSocket.accept(); // accepting client request and forming link
            System.out.println("client request accepted");

            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        }catch(IOException ioE){
            ioE.printStackTrace();
        }
    }



    public void closeConnection() {

        //to close every connection that has been made and every I/O stream opened

        try {
            serverSocket.close();
            socket.close();
            input.close();
        } catch (IOException ioE) {
            System.out.println(ioE.getMessage());
        }
    }
    public static void main(String[] args) throws Exception {
        new Server(12345);
    }
}
