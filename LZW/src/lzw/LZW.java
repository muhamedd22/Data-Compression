/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;

import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author mbakr
 */
// decompression 
public class LZW {
    
    public static Vector<Dictionary> myDictionary = new Vector<>();
    public static Scanner in = new Scanner(System.in);
    public static String searchDictionary(int index)
    {
        String string="";
        for(int i = 0; i < myDictionary.size(); i++)
        {
            if(index == myDictionary.get(i).index){
                string = myDictionary.get(i).symbol;
                return string;
            }
        }
        return string ;
    }
    public static void main(String[] args)
    {
        int numberOftags = in.nextInt();
        int index = 128;
        String prev="";
        String curr="";
        String Text ="";
        int input = in.nextInt();
                char c = (char)input;
                prev+=c;
                Text += c;
                
            
        for(int i = 1; i < numberOftags; i++)
        {
            
            input = in.nextInt();
            if(input<128)
            {
                c = (char)input;
                //prev+=c;
                Text += c;
                curr=prev+c;
                prev=c+"";
                Dictionary dic = new Dictionary();
                dic.index=index;
                dic.symbol=curr;
                myDictionary.add(dic);
                index++;
            }
            else
            {
                
                String temp=searchDictionary(input);
                if(temp.equals(""))
                {
                    curr=prev+prev.charAt(0);
                    Text+=curr;
                    Dictionary dic = new Dictionary();
                    dic.index=index;
                    dic.symbol=curr;
                    myDictionary.add(dic);
                    prev=curr;
                    
                }
                else
                {
                    Text+=temp;
                    curr=prev+(temp.charAt(0));
                    prev=temp;
                    Dictionary dic = new Dictionary();
                    dic.index=index;
                    dic.symbol=curr;
                    myDictionary.add(dic);
                    index++;
                }
            }
            
        }
        System.out.println(Text);
    }
    
    
}
