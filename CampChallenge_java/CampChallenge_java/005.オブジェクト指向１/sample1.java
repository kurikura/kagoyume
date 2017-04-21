/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kurikura
 */
public class sample1 {
    public int a,b;
    public void sample1(){
        a=1;
        b=3;
    }
    public void sample2(){
        System.out.println(a);
        System.out.println(b);
    }    
}

class clear extends sample1{
    public void clear(){
      a=0;
      b=0;
}
}