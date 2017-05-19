import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class PictChatUDPClient{
  private MyCanvas c;
  private DatagramSocket socket;
  private DatagramPacket sendPacket;
  String cmd;
  byte sendBuf[]=new byte[256];
  boolean flag=false;

  public static void main(String[] args){
    PictChatUDPClient pcuc=new PictChatUDPClient();
  }

  PictChatUDPClient(){
    try{
      socket=new DatagramSocket();
      sendPacket=new DatagramPacket(sendBuf,sendBuf.length,InetAddress.getByName("localhost"),9000); //サーバに接続
      socket.send(sendPacket);

      Frame f=new Frame("PictChatUDPClient"); //ウィンドウの生成
      f.setBounds(680,0,840,680);
      f.addWindowListener(new WindowAdapter(){
        @Override public void windowClosing(WindowEvent e){ //ウィンドウを閉じた時の処理
          try{
            cmd="Quit";
            sendBuf=cmd.getBytes();
            sendPacket=new DatagramPacket(sendBuf,sendBuf.length,InetAddress.getByName("localhost"),9000);
            socket.send(sendPacket);
            socket.close();
          }catch(SocketException se){
            se.printStackTrace();
          }catch(SecurityException se2){
            se2.printStackTrace();
          }catch(Exception e2){
            e2.printStackTrace();
          }
          System.exit(0);
        }
      });

      c=new MyCanvas();
      c.addMouseListener(new MouseListener(){
        @Override public void mouseClicked(MouseEvent e){ //クリックした時の処理
          try{
            if(e.getButton()==1){ //図形描画
              cmd="setDiagram "+e.getX()+" "+e.getY();
              sendBuf=cmd.getBytes();
              sendPacket=new DatagramPacket(sendBuf,sendBuf.length,InetAddress.getByName("localhost"),9000);
              socket.send(sendPacket);
              c.setPoint(e.getX(),e.getY());
              System.out.print(" setDiagram "+e.getX()+" "+e.getY());
            }else if(e.getButton()==2){ //描画する図形の変更
              cmd="changeDiagram";
              sendBuf=cmd.getBytes();
              sendPacket=new DatagramPacket(sendBuf,sendBuf.length,InetAddress.getByName("localhost"),9000);
              socket.send(sendPacket);
              c.changeDiagram();
              System.out.println(" changeDiagram");
            }else if(e.getButton()==3){ //描画する図形の色の変更
              cmd="changeColor";
              sendBuf=cmd.getBytes();
              sendPacket=new DatagramPacket(sendBuf,sendBuf.length,InetAddress.getByName("localhost"),9000);
              socket.send(sendPacket);
              c.changeColor();
              System.out.println(" changeColor");
            }
          }catch(SocketException se){
            se.printStackTrace();
          }catch(SecurityException se2){
            se2.printStackTrace();
          }catch(Exception e2){
            e2.printStackTrace();
          }
        }
        @Override public void mouseEntered(MouseEvent e){
          try{
            if(flag==true){
              JFrame frame=new JFrame(); //ダイアログ表示
              String options[]={"Yes","No","終了"};
              int cl=JOptionPane.showOptionDialog(frame,"白紙に戻しますか？","確認",
	                       JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[1]);
              flag=false;
              if(cl==0){ //Yesが押された時→配列初期化
                cmd="clear";
                sendBuf=cmd.getBytes();
                sendPacket=new DatagramPacket(sendBuf,sendBuf.length,InetAddress.getByName("localhost"),9000);
                socket.send(sendPacket);
                c.clear();
                System.out.println(" clear");
              }else if(cl==2){ //終了が押されたとき→終了処理
                cmd="Quit";
                sendBuf=cmd.getBytes();
                sendPacket=new DatagramPacket(sendBuf,sendBuf.length,InetAddress.getByName("localhost"),9000);
                socket.send(sendPacket);
                socket.close();
                System.out.println(" Quit");
                System.exit( 0 );
              }
            }
          }catch(HeadlessException he){
            he.printStackTrace();
          }catch(SocketException se){
            se.printStackTrace();
          }catch(SecurityException se2){
            se2.printStackTrace();
          }catch(Exception e2){}
        }
        @Override public void mouseExited(MouseEvent e){
          flag=true;
        }
        @Override public void mousePressed(MouseEvent e){}
        @Override public void mouseReleased(MouseEvent e){}
      });

      c.addMouseMotionListener(new MouseMotionListener(){
        @Override public void mouseDragged(MouseEvent e){ //ドラッグされたときの処理
          try{
            if(c.mode==0){ //点の時のみ描画する
              cmd="setDiagram "+e.getX()+" "+e.getY();
              sendBuf=cmd.getBytes();
              sendPacket=new DatagramPacket(sendBuf,sendBuf.length,InetAddress.getByName("localhost"),9000);
              socket.send(sendPacket);
              c.setPoint(e.getX(),e.getY());
              System.out.print(" setDiagram "+e.getX()+" "+e.getY());
            }
          }catch(SocketException se){
            se.printStackTrace();
          }catch(SecurityException se2){
            se2.printStackTrace();
          }catch(Exception e2){
            e2.printStackTrace();
          }
        }
        @Override public void mouseMoved(MouseEvent e){}
      });
      f.add(c);
      f.setVisible(true);
    }catch(HeadlessException he){
      he.printStackTrace();
    }catch(IllegalArgumentException iae){
      iae.printStackTrace();
    }catch(Exception e2){
      e2.printStackTrace();
    }
  }
}
