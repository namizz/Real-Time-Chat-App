package chatApp;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Client {
    PrintWriter writer;
    BufferedReader reader;
    Socket sock;
    public static void main(String[] args) {
        new Client().ClientCode();
        
    }
    public void ClientCode(){
        try {
            sock = new Socket("127.0.0.1", 1500);//connects with the server
            writer = new PrintWriter(sock.getOutputStream());//write on the OutputStraem of the Socket
            writer.println("hi server");//writing on the socket outputstream
            writer.flush();
            writer.println("Paramparas");
            writer.flush();
            //not yet important
            InputStreamReader read = new InputStreamReader(sock.getInputStream());//reads the message on the socket
            reader = new BufferedReader(read);//buffer the message

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect");
        
        } 
    }

}
