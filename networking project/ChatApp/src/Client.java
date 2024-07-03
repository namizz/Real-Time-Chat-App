import java.net.*;
import java.util.Scanner;

import javax.swing.JOptionPane;
import java.io.*;

public class Client {
    PrintWriter writer;
    BufferedReader reader;
    Socket sock;
    public static void main(String[] args) {
        new Client().ClientCode(4518);
        
    }
    public void ClientCode(int port){
        try {
            //connects with the server
            sock = new Socket("127.0.0.1", port);
            //object to writes on the OutputStraem of the Socket
            writer = new PrintWriter(sock.getOutputStream());
            write();
            //object to read from socket
            InputStreamReader read = new InputStreamReader(sock.getInputStream());//reads the message on the socket
            reader = new BufferedReader(read);//buffer the message
            Thread readd = new Thread(new read_message());
            readd.start();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect");
        
        } 
    }
    public void write(){
        Scanner scan = new Scanner(System.in);
        String message= null;
        //check here there is a while loop that might bring trouble when the client is reads message from the socket
        while(true){
        System.out.print("You: ");
        message = scan.nextLine();
        try {
            writer.println(message);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        

    }
    public class read_message implements Runnable{
        public void run(){
            String message;
            System.out.print("Server: ");
            try {
                while((message = reader.readLine())!= null){
                    System.out.println(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


//     Client

// function 1:

// - scans from console and writes it on the network
    
//     function 2:
    
// - read from the text and print on the console
    
//     function 3:
    
// - close connection
    
//     function 4:
    
// - constructor : connects with the socket

}


