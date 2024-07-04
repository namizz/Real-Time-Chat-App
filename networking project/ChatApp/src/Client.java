import java.net.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;

public class Client {
    PrintWriter writer;
    BufferedReader reader;
    Socket sock;
    InputStreamReader read;
    public static void main(String[] args) {
        new Client().ClientCode(1500);
        
    }
    //function that setup the networking
    public void ClientCode(int port){
        try {
            //connects with the server
            sock = new Socket("127.0.0.1", port);

            //object to writes on the OutputStraem of the Socket
            writer = new PrintWriter(sock.getOutputStream());            
            //object to read from socket
            read = new InputStreamReader(sock.getInputStream());//reads the message on the socket
            reader = new BufferedReader(read);//buffer the message

            
            Thread write = new Thread(new writeMessage());
            Thread readd = new Thread(new read_message());
            write.start();
            readd.start();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect");
        
        } 
    }
    // a thread that reads message from the console and writes message on the socket
    public class writeMessage implements Runnable{
        public void run(){
            Scanner scan = new Scanner(System.in);
            try {
            while(true){
                String message = scan.nextLine();
                writer.println(message);
                writer.flush();
                
                }
            } catch (Exception e) {
            e.printStackTrace();
            } finally{
                scan.close();
            }
    }
        

    }
    // a thread that read a message from the socket and write on the console
    public class read_message implements Runnable{
        public void run(){
            String message;
            try {
                while((message = reader.readLine())!= null){
                    System.out.println("Server: " + message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


//     Client
    
//     function 3:
    
// - close connection
    
//     function 4:
    
// - constructor : connects with the socket

}


