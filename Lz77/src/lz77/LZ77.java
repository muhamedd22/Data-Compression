/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lz77;

import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author mbakr
 */
public class LZ77 {

    public static String searchWindow="";
    public static String lookAheadWindow="";
    public static Vector<Tag> myTags = new Vector<>();
    public static Scanner in = new Scanner(System.in);
    public static void Compress()
    {
        String Text = in.next();
        Tag T = new Tag();
        int last_seen = 0;
        T.position = 0;
        T.length = 0;
        T.nextSymbol = Text.charAt(0);
        myTags.add(T);
        searchWindow += Text.charAt(0);
        for(int i = 1; i < Text.length(); i++)
        {
            String temp = Text.charAt(i)+"";
            int checkPoint = i;
            while(true)
            {
                boolean exist = searchWindow.contains(temp);
                if(exist==true)
                {
                    i++;
                    if(i==Text.length())
                    {
                        last_seen = searchWindow.indexOf(temp);
                        Tag T1 = new Tag();
                        T1.nextSymbol = Text.charAt(i - 1);
                        T1.length = temp.length() - 1;
                        T1.position = last_seen;
                        myTags.add(T1);
                        break;
                    }
                    last_seen = searchWindow.indexOf(temp);
                    temp+=Text.charAt(i);

                }
                else
                {
                    if(temp.length()==1)
                    {
                        Tag T1 = new Tag();
                        T1.position = 0;
                        T1.length = 0;
                        T1.nextSymbol = Text.charAt(i);
                        myTags.add(T1);
                    }
                    else
                    {
                        Tag T1 = new Tag();
                        T1.nextSymbol = Text.charAt(i);
                        T1.position = checkPoint - last_seen;
                        T1.length = temp.length() - 1;
                        myTags.add(T1);
                    }
                    searchWindow+=temp;
                    break;
                }
            }
        }
        for(int i = 0; i < myTags.size(); i++)
        {
            System.out.println(myTags.get(i).position + " " + myTags.get(i).length + " " + myTags.get(i).nextSymbol);
        }
    }
    public static String Decompress()
    {
        int numberOfTags = in.nextInt();
        int len,pos;
        String nextSymbol;
        String Text = "";
        for(int i = 0; i < numberOfTags; i++)
        {
            pos = in.nextInt();
            len = in.nextInt();
            nextSymbol = in.next();
            if(pos==0)
            {
                Text+=nextSymbol;
            }
            else
            {
                int start = Text.length() - pos;
                for(int j = 0; j<len ; j++)
                {      
                    Text+=Text.charAt(start);
                    start++;
                }
                Text+=nextSymbol;
                
            }
        }
        return Text;
    }
    public static void main(String[] args)
    {
        //Compress();
        System.out.println(Decompress());
    }
    
}
