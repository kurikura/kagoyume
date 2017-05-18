import java.util.*;
import java.awt.*;
import java.awt.event.*;
@SuppressWarnings("unchecked")
class Paint extends Frame implements MouseListener,MouseMotionListener{
    int x,y,num1=0,num2=0,num3=0;
    Vector objList;
    Figure obj;
    public static void main(String[] args){
        Paint f=new Paint();
        f.setSize(640,640);
        f.setTitle("Paint Sample");
        f.addWindowListener(new WindowAdapter(){
            @Override public void windowClosing(WindowEvent e){
                System.exit(0);
        }});
        f.setVisible(true);
    }
    Paint(){
        objList=new Vector();
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    @Override public void paint(Graphics g){
        Figure f;
        for(int i=0;i<objList.size();i++){
            f=(Figure)objList.elementAt(i);
            f.paint(g);
        }
        if(obj!=null) obj.paint(g);
    }
    @Override public void mousePressed(MouseEvent e){
        if(objList.size()>=100){
            objList.removeElementAt(0);
        }
        if(e.getButton()==MouseEvent.BUTTON1){
            obj=new Oval();
            num1++;
        }else if(e.getButton()==MouseEvent.BUTTON2){
            obj=new Point();
            num2++;
        }else{
            obj=new Rect();
            num3++;
        }
        obj.moveto(e.getX(),e.getY());
        x=e.getX();
        y=e.getY();
        obj.moveto(x,y);
        repaint();
    }
    @Override public void mouseReleased(MouseEvent e){
        x=e.getX();
        y=e.getY();
        obj.moveto(x,y);
        objList.add(obj);
        obj=null;
        repaint();
        System.out.println("現在obj="+objList.size()+" 累計obj="+(num1+num2+num3)+" 累計Oval="+num1+" 累計Point="+num3+" 累計Rect="+num2);
    }
    @Override public void mouseClicked(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){
        if(objList.size()%10==0){
            objList.clear();
        }else if(objList.size()%2==0){
            objList.removeElementAt(0);
        }
    }
    @Override public void mouseDragged(MouseEvent e){
        x=e.getX();
        y=e.getY();
        if(obj!=null) obj.moveto(x,y);
        repaint();
    }
    @Override public void mouseMoved(MouseEvent e){}
}
class Coord{
    int x,y;
    Coord(){
        x=y=0;
    }
    public void move(int dx,int dy){
        x+=dx;
        y+=dy;
    }
    public void moveto(int x,int y){
        this.x=x;
        this.y=y;
    }
    public void paint(Graphics g){}
}
class Figure extends Coord{
    int size;
    Figure(){
        size=15;
    }
    @Override public void paint(Graphics g){}
}
class Oval extends Figure{
    Oval(){}
    public void paint(Graphics g){
        g.setColor(Color.orange);
        g.fillOval(x-size/2,y-size/2,size*2,size);
    }
}
class Point extends Figure{
    Point(){}
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.fillOval(x-size/2,y-size/2,size,size);
    }
}
class Rect extends Figure{
    Rect(){}
    public void paint(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(x-size/2,y-size/2,size,size);
    }
}
