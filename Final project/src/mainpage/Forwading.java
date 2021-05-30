package mainpage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Forwading {
	final int x;
	Dashboard db = new Dashboard();
	int i = 0;
	String account_to,file_accname,amount_to_transfer;
	int file_amount1;
	File file;
	public Forwading(int x)
	{
		this.x = x;
	}
	synchronized public void transfering() throws IOException
	{
		char runloop='y';
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter account number to which you want to transfer money.");
		this.account_to = sc.nextLine();
		try
		{
			wait();
		}catch(Exception e)
		{
		}
		try (Stream<String> lines = Files.lines(Paths.get("accountbalance.txt"))) 
        {
			file_amount1 = Integer.parseInt(lines.skip(x).findFirst().get());
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
		System.out.println("Enter amount you want ot transfer.");
		while(runloop=='y')
		{
			amount_to_transfer = sc.nextLine();
			if(this.file_amount1<Integer.parseInt(amount_to_transfer))
			{  
				System.out.println("You don't have enough money to Transfer.\n\n");  
				System.out.println("Enter a valid amount.");
			}
			else
				break;
		}
		file_amount1-=Integer.parseInt(amount_to_transfer);  
		System.out.println("Amount transfered successfuly.\n\n");  
		String data[] = new String[10];
		int i = 0,j=0;
		file = new File("accountbalance.txt");//updating account balance in file
		try (Scanner InputFile = new Scanner(file)) 
        { // to start reading the file.
            while(InputFile.hasNext())
            {
                data[i] = InputFile.nextLine();//to read file line by line
                i++;
            }
            InputFile.close();
        }
		data[x] = Integer.toString(file_amount1);
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("accountbalance.txt"));)
        {
            for(j=0;j<i;j++)
            {
                writer.write(data[j]);  // to replace all of the data in the file with new score.
                writer.newLine(); // to write from new line.
            }
            writer.close();
        }
		db.menu(x);
	}
	synchronized public void checkingAccount() throws FileNotFoundException
	{
		File file;
		file = new File("accounts.txt");
    	try (Scanner InputFile = new Scanner(file)) 
    	{ // to start reading the file.
    		while(InputFile.hasNext())
    		{
    			this.file_accname = InputFile.nextLine();//to read file line by line
    			if(account_to.equals(file_accname))
    			{
    				notify(); 
    				break;
    			}
    			else if(!(InputFile.hasNext()))
    			{
    				System.out.println("This account doesn't axits.");
    				db.menu(x);
    			}
    			i++;
    		}
    		InputFile.close();
    	}
	}
}

class Thread3 extends Thread
{
	Forwading f;
	Thread3(Forwading f)
	{
		this.f=f;
	}
	
	
	public void run()
	{
		try {
			f.transfering();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class Thread4 extends Thread
{
	Forwading f;
	Thread4(Forwading f)
	{
		this.f=f;
	}
	
	
	public void run()
	{
		try {
			f.checkingAccount();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
