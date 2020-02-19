import java.util.Scanner;
import java.util.Vector;
import lz78.Dectionary;
import lz78.Tag;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class LZ78 {

    public static Vector<Tag> myTags = new Vector<>();
    public static Vector<Dectionary> myDectionary = new Vector<>();
    static Scanner in = new Scanner(System.in);
    public static int Search(String S)
    {
        for(int i = 0; i < myDectionary.size(); i++)
        {
            if(S.equals(myDectionary.get(i).symbol))
                return myDectionary.get(i).index;
        }
        return 0;
    }
    public static void Compress(String STR)
    {
        int index = 2, last_seen = 0;
        Tag T = new Tag();
        T.index = 0;
        T.nextSymbol = STR.charAt(0);
        myTags.add(T);
        
        Dectionary D = new Dectionary();
        D.index = 1;
        D.symbol = STR.charAt(0)+"";
        myDectionary.add(D);
        
        for(int i = 1; i < STR.length(); i++)
        {
            String temp = STR.charAt(i)+"";
            while(true)
            {
                int found = Search(temp);
                if(found==0)
                {
                    Dectionary D1 = new Dectionary();
                    D1.index = index;
                    D1.symbol = temp;
                    myDectionary.add(D1);
                    index++;
                    if(temp.length()==1)
                    {
                        Tag T1 = new Tag();
                        T1.index = 0;
                        T1.nextSymbol = STR.charAt(i);
                        myTags.add(T1);
                    }
                    else
                    {
                        Tag T1 = new Tag();
                        T1.index = last_seen;
                        T1.nextSymbol = STR.charAt(i);
                        myTags.add(T1);
                    }  
                    break;
                }
                else
                {
                    last_seen = found;
                    i++;
                    if(i==STR.length())
                    {
                        Tag tag = new Tag();
                        tag.index = found;
                        tag.nextSymbol = ' ';
                        myTags.add(tag);
                        break;
                    }
                    temp+=STR.charAt(i)+"";
                } 
            }
        }   
    }
public static String Decompress()
{
	int num, index = 1;
        boolean bool = true;
        System.out.println("Enter the number of tags : ");
        num=in.nextInt();
	String Text = "";
	for (int i = 0; i < num; i++)
	{
		String tag;
		tag=in.next();
                Tag T = new Tag();
		T.index = tag.charAt(1) - '0';
		T.nextSymbol = tag.charAt(3);
		myTags.add(T);
	}
	for (int i = 0; i < num; i++)
	{
		Dectionary D = new Dectionary();
		if (myTags.get(i).index == 0)
		{
			Text += myTags.get(i).nextSymbol;
                        String STR =  myTags.get(i).nextSymbol + "";
			int found = Search(STR);
                        if(found==0)
                        {
                            D.index = index;
                            D.symbol = myTags.get(i).nextSymbol+"";
                            myDectionary.add(D);
                        }
		}
		else
		{
			int x = myTags.get(i).index - 1;
			Text += myDectionary.get(x).symbol;
                        Text += myTags.get(i).nextSymbol;
			D.index = index;
			D.symbol = myDectionary.get(x).symbol + myTags.get(i).nextSymbol;
			myDectionary.add(D);
		}
		index++;
	}
	return Text;
}

    
    public static void main(String[] args)
    {
        while(true)
        {
             myDectionary.clear();
            myTags.clear();
            System.out.println("LZ78 Compression & Decompression");
            System.out.println("1- Compress Text.");
            System.out.println("2- Decompress Tags.");
            System.out.println("0- Exit");
            System.out.print("Enter Your choice : ");
            int choice = in.nextInt();
            if(choice==1)
            {
                String STR = in.next();
                Compress(STR);
                System.out.println("Tags : ");
                for(int i = 0; i < myTags.size(); i++)
                {
                    System.out.println(myTags.get(i).index + "  " + myTags.get(i).nextSymbol);
                }
                System.out.println("Dectionary : ");
                for(int i = 0; i < myDectionary.size(); i++)
                {
                    System.out.println(myDectionary.get(i).index + "  " + myDectionary.get(i).symbol);
                }
            }
            else if(choice==2)
            {
                String text = Decompress();
                System.out.println("Text : " + text);
                System.out.println("Dectionary : ");
                for(int i = 0; i < myDectionary.size(); i++)
                {
                    System.out.println(myDectionary.get(i).index + "  " + myDectionary.get(i).symbol);
                }
            }
            else if (choice ==0)
                break;
            else
                System.out.println("Enter a right number");
            
           
        }
    }
}