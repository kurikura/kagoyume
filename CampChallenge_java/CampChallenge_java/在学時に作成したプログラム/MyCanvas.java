import java.awt.*;

public class MyCanvas extends Canvas{
 public int x[]=new int[200],y[]=new int[200],num=0;
 int mode=0;
 int mode_c=0;
 @Override public void paint(Graphics g){
   if(mode_c==0){
     g.setColor(new Color(255,0,0));
   } else if(mode_c==1){
       g.setColor(new Color(0,255,0));
   } else if(mode_c==2){
       g.setColor(new Color(0,0,255));
   }

  if(mode==0){
   for(int i=0;i<num;i++){
    g.fillOval(x[i],y[i],5,5);
   }
  } else if(mode==1){
     for(int i=0;i<num-1;i++){
      g.drawLine(x[i],y[i],x[i+1],y[i+1]);
     }
  } else if(mode==2){
     for(int i=0;i<num-1;i++){
      g.drawRect(x[i],y[i],x[i+1],y[i+1]);
     }
  }
 }

 public void setPoint(int x,int y){
  this.x[num]=x;
  this.y[num]=y;
  num++;
  if(num>=200)num=0;
  repaint();
 }

 public void changeDiagram(int mode){
  this.mode=mode;
  repaint();
 }

 public void changeDiagram(){
  if(this.mode==0){
   this.mode=1;
  }else if(this.mode==1){
   this.mode=2;
  }else if(this.mode==2){
   this.mode=0;
  }
  repaint();
 }

 public void changeColor(){
   if(this.mode_c==0){
    this.mode_c=1;
  }else if(this.mode_c==1){
    this.mode_c=2;
  }else if(this.mode_c==2){
    this.mode_c=0;
   }
   repaint();
 }

 public void clear(){
   int x[]=new int[200];
   int y[]=new int[200];
   num=0;
   repaint();
 }
}
