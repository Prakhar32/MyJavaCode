/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CC;
import java.util.*;
/**
 *
 * @author hp
 */

class nodem
{
    int info;inodem leftchild,rightchild;boolean visited=false;int nov=0;
}
class inodem
{
    ArrayList<nodem>list=new ArrayList();boolean isfirst=false;    
}
public class Mtree 
{
    int sizemax;
   inodem root=null;
   void setsize(int max)
   {
       sizemax=max;
   }
   void insertinchild(ArrayList<nodem> child,int x)
   {
       nodem n=new nodem();n.info=x;
       int i=0;
       while(child.get(i)!=null)
       {
           i++;
       }
       child.add(n);
   }
    inodem feedback(int start,int end,ArrayList<nodem> present)
    {
        inodem branch=new inodem();
        for(int i=start;i<=end;i++)
            branch.list.add(present.get(i));
        
        return branch;
    }
   
   inodem[] breaknode(inodem current,inodem parent)
   {
       int i=0;boolean flag=false;
       if(parent==null)
       {
           parent = new inodem();i=0;flag=true;
       }
       else
       {
          while(i!=parent.list.size() )
          {
              
              if(parent.list.get(i).leftchild==current )
                  break;
              i++;
          } 
       }
       
       int len=current.list.size()-1;
       nodem mid=current.list.get((current.list.size()-1)/2);
       
       
       current.list.remove(len/2);
       mid.leftchild=feedback(0,len/2-1,current.list);
       
       mid.rightchild=feedback(len/2,len-1,current.list);
       
       parent.list.add(i, mid);
       
       try{
             if(parent.list.size()>1)
             {
                 if(parent.list.size()-1>i)
                 { parent.list.get(i+1).leftchild=parent.list.get(i).rightchild;
                 }
                if(i>0)
                {parent.list.get(i-1).rightchild=parent.list.get(i).leftchild;
                
                }
             }
       }
      
       catch(ArrayIndexOutOfBoundsException e)
       {
           System.out.println("Exception occurs");
       }
       
       
       inodem arr[]=new inodem[2];
       
       if(i<parent.list.size()-1 && parent.list.size()>1)
           current=parent.list.get(i+1).leftchild;
       if(i!=0 && i==parent.list.size()-1)
           current=parent.list.get(parent.list.size()-1).rightchild;
       arr[0]=current;
       arr[1]=parent;
       return arr;
   }
   boolean equality(inodem e1,inodem e2)
   {
       if(e1.list.size()!=e2.list.size())
           return false;
       for(int i=0;i<e1.list.size();i++)
       {
           if(e1.list.get(i).info!=e2.list.get(i).info)
               return false;
       }
       return true;
   }
   inodem insert(inodem current,int x,inodem parent)
   {
       nodem ins=new nodem();ins.info=x;
       if(current==null)
       {   
           current=new inodem();current.list.add(ins);return current;
       }
       
       root.isfirst=true;
       int i=0;
       while(current.list.size()<sizemax)
       {
           if(current.list.get(i).info==x)
           {
               System.out.println("Wrong input");return current;
           }
           
           
           
                  
           if(current.list.get(i).info<x && current.list.size()-1==i )
           {
               if(current.list.get(i).rightchild==null)
               {
                    current.list.add(i+1,ins);
                    
                    return current;
               }
               if(current.list.get(i).rightchild!=null)
               {
                   current.list.get(i).rightchild=insert(current.list.get(i).rightchild,x,current);return current;
               }
              
           }
           
           
           if(current.list.get(i).info>x )
           {
               
               current.list.get(i).leftchild=insert(current.list.get(i).leftchild,x,current);
              if(i>0)
                  current.list.get(i-1).rightchild=current.list.get(i).leftchild;
              return current;
           }
           i++;
       }
       
             
       if(current.list.get(i).info<x && current.list.get(i).rightchild==null )
       {
           current.list.add(ins);
       }
       if(current.list.get(i).info<x && current.list.get(i).rightchild!=null)
       {   
         current.list.get(i).rightchild=insert(current.list.get(i).rightchild,x,current);return current;
       }
       
       
       if(current==parent)
           parent=null;
       
       if(current.list.size()==sizemax+1)
       {
           
           inodem store[];
           store=breaknode(current,parent);
           current=store[0];parent=store[1];
           
           if(current==root)
            {
                if(parent!=null) 
                    return parent;
            }
           
       }
       
       
       return current;
   }
   
   void display(inodem node)
   {
       int i=0;
       if(node==null)
           return;
       while(i!=node.list.size())
       {         
           display(node.list.get(i).leftchild);
           
           System.out.print(node.list.get(i).info+" ");
           
           if(node.list.size()-1==i)
            display(node.list.get(i).rightchild);
           i++;   
       }
   }
    void displaypreorder(inodem node)
   {
       int i=0;
       if(node==null)
           return;
       while(i!=node.list.size())
       {
          System.out.print(node.list.get(i).info+" ");
           displaypreorder(node.list.get(i).leftchild);
           if(node.list.size()-1==i)
           displaypreorder(node.list.get(i).rightchild);
           
           i++;   
       }
   }
   public static void main(String args[])
   {
       Mtree ob=new Mtree();ob.setsize(4);
       ob.root=ob.insert(ob.root, 10, ob.root);
       ob.root=ob.insert(ob.root, 20, ob.root);
       ob.root=ob.insert(ob.root, 30, ob.root);
      
       ob.root=ob.insert(ob.root, 40, ob.root);
       ob.root=ob.insert(ob.root, 50, ob.root);
       ob.root=ob.insert(ob.root, 60, ob.root);
       ob.root=ob.insert(ob.root, 70, ob.root);
       ob.root=ob.insert(ob.root, 80, ob.root);
        ob.root=ob.insert(ob.root, 33, ob.root);
       ob.root=ob.insert(ob.root, 35, ob.root);
       ob.root=ob.insert(ob.root, 37, ob.root);
       ob.root=ob.insert(ob.root, 38, ob.root);
       ob.root=ob.insert(ob.root, 39, ob.root);
       
        System.out.println();
       
       ob.display(ob.root);
       System.out.println();
       ob.displaypreorder(ob.root);
       
   }
}   