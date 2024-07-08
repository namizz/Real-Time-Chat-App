import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Client {
    PrintWriter writer;
    DataInputStream reader;
    Socket sock;
    InputStreamReader read;
    public static void main(String[] args) {
<<<<<<< HEAD
        new Client().ClientCode(1500);
        
    }
    //function that setup the networking
    public void ClientCode(int port){
=======
        new Client().clientCode(1500);   
    }
    public void clientCode(int port){
>>>>>>> a4a519470d715d046a1977882a6dee75754d4997
        try {
            //connects with the server
            sock = new Socket("127.0.0.1", port);

            //object to writes on the OutputStraem of the Socket
<<<<<<< HEAD
            writer = new PrintWriter(sock.getOutputStream());            
            //object to read from socket
            read = new InputStreamReader(sock.getInputStream());//reads the message on the socket
            reader = new BufferedReader(read);//buffer the message

            
            Thread write = new Thread(new writeMessage());
            Thread readd = new Thread(new read_message());
            write.start();
=======
            writer = new PrintWriter(sock.getOutputStream());
            
            //object to read from socket
            reader = new DataInputStream(sock.getInputStream());//buffer the message
            Thread readd = new Thread(new read_message());
            Thread writeThread = new Thread(()->{while(true)write();});
>>>>>>> a4a519470d715d046a1977882a6dee75754d4997
            readd.start();
            writeThread.start();            

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect");
        
        } 
    }
<<<<<<< HEAD
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
=======
    public void write(){
        Scanner scan = new Scanner(System.in);
        String message= null;
        
        message = scan.nextLine();
        try {
            if(!message.isBlank()){
                writer.println(message);
                writer.flush();}
        } catch (Exception e) {
>>>>>>> a4a519470d715d046a1977882a6dee75754d4997
            e.printStackTrace();
            } finally{
                scan.close();
            }
    }
<<<<<<< HEAD
        

    }
    // a thread that read a message from the socket and write on the console
=======
>>>>>>> a4a519470d715d046a1977882a6dee75754d4997
    public class read_message implements Runnable{
        @Override
        public void run(){
            String message;
<<<<<<< HEAD
            try {
                while((message = reader.readLine())!= null){
                    System.out.println("Server: " + message);
=======
            while(true){
            try {
                message = reader.readUTF();
                if(!message.isBlank()){
                    System.out.print("Server: ");
                    System.out.println(message);
>>>>>>> a4a519470d715d046a1977882a6dee75754d4997
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }
    }


//     Client
    
//     function 3:
    
// - close connection
    
//     function 4:
    
// - constructor : connects with the socket

}


