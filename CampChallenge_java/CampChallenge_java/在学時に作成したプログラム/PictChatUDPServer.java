import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class PictChatUDPServer{
  private MyCanvas c;
  public static void main(String[] args){
    PictChatUDPServer pcus=new PictChatUDPServer();
    pcus.doEvent();
  }
 
  PictChatUDPServer(){
    Frame f=new Frame("PictChatUDPServer"); //ウィンドウの生成
    f.setBounds(0,0,840,680);
    f.addWindowListener(new WindowAdapter(){
      @Override public void windowClosing(WindowEvent e){
        System.exit(0);
      }
    });
    c=new MyCanvas();
    f.add(c);
    f.setVisible(true);
  }
 
  public void doEvent(){
    try{
      int i=0;
      DatagramSocket socket=null;
      DatagramPacket receivePacket=null;
      byte receiveBuf[]=new byte[256];
      socket=new DatagramSocket(9000);
      receivePacket=new DatagramPacket(receiveBuf,receiveBuf.length);
   
      for(;;){ //繰り返し待ち受ける
        socket.receive(receivePacket);
        InetAddress addr=receivePacket.getAddress();
        int port=receivePacket.getPort();
        if(i==0){
          System.out.println("IPアドレス:"+addr+" Port番号:"+port); //1回だけコンソールに表示
          i++;
        }
   
        String command=new String(receivePacket.getData(),0,receivePacket.getLength()); //受信したバイト列を文字列に変換
        String[] words=command.split( " " );
        System.out.print(command+" ");
        if(words[0].equalsIgnoreCase("setDiagram")){ //図形描画
	        try{
 	          c.setPoint(
    	        Integer.parseInt(words[1]),
    	        Integer.parseInt(words[2])
    	      );
    	    }catch(NumberFormatException nfe){
    	      nfe.printStackTrace();
          }catch(Exception e){
            e.printStackTrace();
          }
        }else if(words[0].equalsIgnoreCase("changeDiagram")){ //描画する図形の変更
	        try{
	          c.changeDiagram();
	          System.out.println();
          }catch(Exception e){
            e.printStackTrace();
          }
        }else if(words[0].equalsIgnoreCase("changeColor")){ //描画する図形の色の変更
	        try{
	          c.changeColor();
	          System.out.println();
	        }catch(Exception e){
	          e.printStackTrace();
	        }
        }else if(words[0].equalsIgnoreCase("quit")){
	        System.out.println();
	        JFrame frame=new JFrame();
	        JOptionPane.showMessageDialog(frame,"切断されました。終了します。",
	                                       "error",JOptionPane.INFORMATION_MESSAGE);
	        System.exit( 0 );
	        break;
        }else if(words[0].equalsIgnoreCase("clear")){
          try{
            c.clear();
	          System.out.println();
	        }catch(Exception e){
	          e.printStackTrace();
	        }
        }
      }
    }catch(IndexOutOfBoundsException iobe){
      iobe.printStackTrace();
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}