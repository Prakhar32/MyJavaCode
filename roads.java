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
class node
{
    nextnode traverse;node next;int key;boolean visited=false;
}
class nextnode
{
   nextnode trav;int key;int weight;
}
public class roads
{
    node root=null;
    void graph(String s)
    {
        roads r=new roads();
       if(root==null)
                {
                   root=new node();
                   root.key=Character.getNumericValue(s.charAt(0));
                }
        node current=root;
        node record=current;node c=root.next;
        while(c!=null)
        {
            c=c.next;record=record.next;
        }
        while(true)
        {
            if(current==null)
                {
                   current=new node();
                   current.key=Character.getNumericValue(s.charAt(0));record.next=current;record=current;
                }
           if(Character.getNumericValue(s.charAt(0))==current.key)
           {     
               if(current.traverse==null)
               {
                   current.traverse=new nextnode();
                   current.traverse.key=Character.getNumericValue(s.charAt(2));
                   current.traverse.weight=Character.getNumericValue(s.charAt(4));
                   return;
               }
               nextnode n=current.traverse.trav;nextnode prev=current.traverse;
               while(n!=null)
               {
                   n=n.trav;prev=prev.trav;
               }
               n=new nextnode();
               n.key=Character.getNumericValue(s.charAt(2));
               n.weight=Character.getNumericValue(s.charAt(4));
               prev.trav=n;
               return;
           }
           else
              current=current.next;
        }    
    }
    
    node transfer(nextnode m)
    {
       node current=root;
       if(m==null)
           System.out.println("aaaaa");
       while(current!=null && current.key!=m.key )
           current=current.next;
       return current;
    }
    int len=0;
    void path(node current)
    {
        if(current.visited)
            return;
        System.out.print(current.key+" ");
        current.visited=true;
        if(current.next==null)
           return;
        nextnode n=current.traverse;
        
        while(n!=null)
        {             
                path(transfer(n));
              n=n.trav;
        }
    }
    
    void pathlen(node current,ArrayList<Integer> p,StringBuffer sb,ArrayList<String> s)
    {
        sb.append(current.key+"#");
        
        if(current.next==null)
        {p.add(len);sb.append(len);s.add(sb.toString());sb.setLength(sb.length()-2); return;}
       
        if(current.next==null)
        {  System.out.println("Alive reaches");return ;}
        nextnode n=current.traverse;
        
        while(n!=null)
        { 
            len=len+n.weight;
              pathlen(transfer(n),p,sb,s);
              sb.setLength(sb.length()-2);
              len=len-n.weight;
              n=n.trav;
              if(sb.charAt(sb.length()-1)!='#')
                 sb.append('#');
        }
    }
    void display()
    {
        node current=root;
        while(current!=null)
        {
            nextnode n=current.traverse;
            System.out.print(current.key+"->\t ");
            while(n!=null)
            {
                System.out.print(n.key+"  ");n=n.trav;
            }
            System.out.println(" ");
            current=current.next;
        }
    }
    int weightcalculator(String s)
    {
        int i=s.length()-1;
        while(s.charAt(i)!='#')
            i--;
        i++;
        String sub=s.substring(i);
        return Integer.parseInt(sub);
    }
    public static void main(String args[])
    {
        String ar[]={"1#3#5","1#5#6","1#4#4","1#2#8","1#6#4","2#5#4","2#3#4","2#6#5","3#4#6","3#6#3","4#6#8","5#3#5","6#0#0"};
        roads ob=new roads();
        for(int i=0;i<ar.length;i++)
        {
            ob.graph(ar[i]);
        }
        ob.display();
        StringBuffer sb=new StringBuffer("");
        ArrayList<String> s=new ArrayList();
        ArrayList<String> end=new ArrayList();
        ArrayList<Integer> p=new ArrayList();
        Scanner obj=new Scanner(System.in);
       System.out.println(" ");ob.path(ob.root);
       
       ob.pathlen(ob.root,p,sb,s);
      for(int i=0;i<p.size();i++)
          System.out.println(p.get(i));
      for(int i=0;i<s.size();i++)
          System.out.println(s.get(i));
      int max=0;
      for(int i=0;i<s.size();i++)
      {
          int l=ob.weightcalculator(s.get(i));
          if(max<l)
              max=l;
      }
      for(int j=0;j<s.size();j++)
      {
          String str=s.get(j);
          str+="##"+(max-ob.weightcalculator(str));
          end.add(str);
      }
      for(int i=0;i<end.size();i++)
          System.out.println(end.get(i));
    }
}
