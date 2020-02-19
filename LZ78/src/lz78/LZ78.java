

package lz78;
/**
 * 
 * Developed by @Mohamed Sameh
 * Developed by @Mohamed Bakr
 * 
 */

import java.util.Scanner;
import java.util.Vector;  
/**
 *
 * @author MU SAMEH
 * @author MU BAKR
 * 
 */

public class LZ78 {

    
    static Scanner input=new Scanner(System.in);
    
    public static Vector<Tag>myTags = new Vector<>();
    public static Vector<Dictionary>myDictionary = new Vector<>();
    
    
     
public static void add_dictionary(Dictionary d)
{
	myDictionary.add(d);
}

public static void add_Tag(Tag t)
{
	myTags.add(t);
}

public static int Search_Dictionary(String STR)
{
	for (int i = 0; i < myDictionary.size(); i++)
	{
		if (STR.equals(myDictionary.get(i).symbol))
			return myDictionary.get(i).index;
	}
	return 0;
}

public static void Compress_LZ78(String text)
{
	int last_seen = 0 , index = 1;
	Dictionary D = new Dictionary();
	D.symbol = text.charAt(0)+"";
	D.index = index;
	add_dictionary(D);
	Tag T=new Tag();
	T.index = 0;
	T.next_symbol = text.charAt(0);
	add_Tag(T);
        
        int found;
	for (int i = 1; i < text.length(); i++)
	{
                // text = ABBABA
            
		String temp;
		temp = text.charAt(i)+"";
                //System.out.println(temp);               
		while (true)   
		{
                    found = Search_Dictionary(temp);
                    //System.err.println(found);
                    if(found >0)
                    {
                        i++;
			if(i==text.length())
                        {
                            Tag tag = new Tag();
                            tag.index = found;
                            tag.next_symbol = ' ';
                            add_Tag(tag);
                            break;
                        }
                        temp += text.charAt(i);
                        last_seen = found;
                        
                    }                          
                   else
                       {
                           if (temp.length()== 1)
                            {
				Tag tag = new Tag();
				tag.next_symbol = text.charAt(i);
				tag.index = 0;
                          	add_Tag(tag);                              
                            }
                            else
                            {
                                    Tag tag=new Tag();
                                    tag.next_symbol = text.charAt(i);
                                    tag.index = last_seen;
                                    add_Tag(tag);
                            }
                           index++;

                           if (i != text.length())    ///check if it the last character
                            {
                                    Dictionary dictionary=new Dictionary();
                                    dictionary.index = index;
                                    dictionary.symbol = temp;
                                    add_dictionary(dictionary);
                            }
                           break;
                        }	
                }   
        }
}
   public static String Decompress_LZ78()
{
	int num, index = 1;
        boolean bool = true;
        System.out.println("Enter the number of tags : ");
        num=input.nextInt();
	String Text = "";
	for (int i = 0; i < num; i++)
	{
		String tag;
		tag=input.next();
                Tag T = new Tag();
		T.index = tag.charAt(1) - '0';
		T.next_symbol = tag.charAt(3);
		add_Tag(T);
	}
	for (int i = 0; i < num; i++)
	{
		Dictionary D = new Dictionary();
		if (myTags.get(i).index == 0)
		{
			Text += myTags.get(i).next_symbol;
                        String STR =  myTags.get(i).next_symbol + "";
			int found = Search_Dictionary(STR);
                        if(found==0)
                        {
                            D.index = index;
                            D.symbol = myTags.get(i).next_symbol+"";
                            add_dictionary(D);
                        }
		}
		else
		{
			int x = myTags.get(i).index - 1;
			Text += myDictionary.get(x).symbol;
                        Text += myTags.get(i).next_symbol;
			D.index = index;
			D.symbol = myDictionary.get(x).symbol + myTags.get(i).next_symbol;
			add_dictionary(D);
		}
		index++;
	}
	return Text;
}

    
    public static void main(String[] args) {
        while(true)
        {
            myTags.clear();
            myDictionary.clear();
            System.out.println("LZ78 Compression & Decompression");
            System.out.println("1- Compress Text.");
            System.out.println("2- Decompress Tags.");
            System.out.print("Enter Your choice : ");
            int choice = input.nextInt();
            if(choice == 1)
            {
                System.out.print("Enter The Text : ");
                String text = input.next();
                Compress_LZ78(text);
                System.out.println( "TAGS : ");
                for (int i = 0; i < myTags.size(); i++)
                {
                    System.out.println("<" + myTags.get(i).index + ',' + myTags.get(i).next_symbol + ">" );
                }

                    System.out.println( "DICTIONARY : "); 

                for (int i = 0; i < myDictionary.size(); i++)
                {
                    System.out.println("<" + myDictionary.get(i).index + ',' + myDictionary.get(i).symbol + ">" );
                }
            }
            else if(choice == 2)
            {
                System.out.println("The Text : " + Decompress_LZ78() );
                System.out.println("Dictionary : ");
                for (int i = 0; i < myDictionary.size(); i++)
                {
                    System.out.println("<" + myDictionary.get(i).index + ',' + myDictionary.get(i).symbol + ">" );
                }
            }
            else
            {
                System.out.println("Please Enter a right number");
            }
        }
    }    
}
