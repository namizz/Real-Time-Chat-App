import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Client {
    PrintWriter writer;
    DataInputStream reader;
    Socket sock;
    public static void main(String[] args) {
        new Client().ClientCode(1500);
        
    }
    public void ClientCode(int port){
        try {
            //connects with the server
            sock = new Socket("127.0.0.1", port);
            //object to writes on the OutputStraem of the Socket
            writer = new PrintWriter(sock.getOutputStream());
            
            //object to read from socket
           // InputStreamReader read = new InputStreamReader(sock.getInputStream());//reads the message on the socket
            reader = new DataInputStream(sock.getInputStream());//buffer the message
            Thread readd = new Thread(new read_message());
            Thread writeThread = new Thread(()->{while(true)write();});
            readd.start();
            writeThread.start();            

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect");
        
        } 
    }
    public void write(){
        Scanner scan = new Scanner(System.in);
        String message= null;
        //check here there is a while loop that might bring trouble when the client is reads message from the socket
        
        message = scan.nextLine();
        try {
            if(!message.isBlank()){
                System.out.print("you: " + message);
                writer.println(message);
                writer.flush();}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public class read_message implements Runnable{
        @Override
        public void run(){
            String message;
            while(true){
            try {
                message = reader.readUTF();
                if(!message.isBlank()){
                    System.out.print("Server: ");
                    System.out.println(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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


