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

public class Process 
{
	final int x;//immutable
	final int billAmount;
	int amount;//mutable
	Dashboard db = new Dashboard();
	public Process(int x,int billAmount)
	{
		this.x = x;
		this.billAmount = billAmount;
	}
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	synchronized void checkingBalance() throws IOException
	{
		File file;
		try (Stream<String> lines = Files.lines(Paths.get("accountbalance.txt"))) 
        {
            amount = Integer.parseInt(lines.skip(x).findFirst().get());
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
		if(this.amount<billAmount)
		{  
			System.out.println("You don't have enough money to pay bill.\n\n");  
			try
			{
				wait();
			}catch(Exception e)
			{
			}
		}  
		this.amount-=billAmount;  
		System.out.println("Bill payed successfully.\n\n");  
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
		data[x] = Integer.toString(amount);
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
	synchronized void backToDashboard()
	{
		db.menu(x);
	}
}
class Thread1 extends Thread
{

	Process p;
	Thread1(Process p)
	{
		this.p=p;
	}
	
	
	public void run()
	{
		try {
			p.checkingBalance();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class Thread2 extends Thread
{

	Process p;
	Thread2(Process p)
	{
		this.p=p;
	}
	
	
	public void run()
	{
		p.backToDashboard();
	}

}