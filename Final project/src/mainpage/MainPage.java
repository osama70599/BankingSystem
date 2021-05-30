package mainpage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class MainPage {

	public static void main(String[] args) throws IOException 
	{

		Scanner sc = new Scanner(System.in);
		Dashboard db = new Dashboard();
        String accname,pin,file_accname,file_pin = null;
        int i;
        File file;
        char runloop1='y',runloop2='y';
        System.out.println("\t\t*****Welcome to HBL online banking.*****");
        while(runloop1=='y')
        {
        	i=0;
        	System.out.println("\n\nEnter account number.");
        	accname = sc.nextLine();
        	file = new File("accounts.txt");
        	try (Scanner InputFile = new Scanner(file)) 
        	{ // to start reading the file.
        		while(InputFile.hasNext())
        		{
        			file_accname = InputFile.nextLine();//to read file line by line
        			if(accname.equals(file_accname))
        			{
        				runloop1='n';
        				while(runloop2=='y')
        				{
        					System.out.println("Enter pin.");
        					pin = sc.nextLine();
        					try (Stream<String> lines = Files.lines(Paths.get("pin.txt")))//reading all lines present in file into a list to skip n lines and get the reqired pin 
        					{
        						file_pin = lines.skip(i).findFirst().get();
        					}
        					catch(IOException e)
        					{
        						System.out.println(e);
        					}
        					if(pin.equals(file_pin))
        					{
        						runloop2='n';
        						db.menu(i);
        						break;
        					}
        					else
        					{
        						System.out.println("Enter correct pin.");
        					}
        				}
        				break;
        			}
        			else if(!(InputFile.hasNext()))
        			{
        				System.out.println("Enter correct account number.");
        			}
        			i++;
        		}
            InputFile.close();
        	}
        }
	}

}
