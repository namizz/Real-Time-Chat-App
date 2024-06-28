import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GUI {
    JFrame frame;
    JTextArea chat;
    JTextField sending;
    JPanel panel;
    JButton send;
    Client message = new Client();
    
    public static void main(String[] args) {
        new GUI().chatbox();
        
    }
    public void chatbox() {
        message.ClientCode();
    
        frame = new JFrame("ChatApp");
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
    
        chat = new JTextArea(15, 30);
        chat.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chat);
        chatScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        chatScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    
        sending = new JTextField(10);
        send = new JButton("Send");
        send.addActionListener(new ButtonListener());
        Thread t = new Thread(new Chat());
        t.start();
    
        panel.add(chatScrollPane, BorderLayout.CENTER);
        panel.add(sending, BorderLayout.SOUTH);
        panel.add(send, BorderLayout.EAST);
    
        frame.add(panel);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }
    
    public class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            message.writer.println(sending.getText());
            sending.setText("");
                        
            }
    
    }
    public class Chat implements Runnable{
        public void run(){
            String line;
    
            try{
            while ((line = message.reader.readLine())!= null) {
                System.out.println(line);
                chat.append(line+"\n");
            }
        }
            catch(IOException ex){
                ex.printStackTrace();

            }            
        }
    }
}
