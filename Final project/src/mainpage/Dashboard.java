package mainpage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Dashboard {
	int x;
	BillPayment bp;
	SendMoney sm;
	public void menu(int i)
	{
		this.x = i;
        int choice = 0;
        char runloop = 'y';
        Scanner sc = new Scanner(System.in);
        String account_balance = null;
        try (Stream<String> lines = Files.lines(Paths.get("accountbalance.txt"))) 
                    {
                        account_balance = lines.skip(x).findFirst().get();
                    }
                    catch(IOException e)
                    {
                        System.out.println(e);
                    }
        System.out.println("\t\t*****Welcome to HBL online banking.*****");
        System.out.println("\n\n\t\t\tYour curent account balance.");
        System.out.println("\t\t\t\tRs. "+account_balance+"/-");
        System.out.println("Enter 1 to pay bill.");
        System.out.println("Enter 2 to send money.");
        System.out.println("Enter 3 to exit.");
        while(runloop=='y')
        {
            try 
            {    //to check user should enter an int, not any string/char.
              choice = sc.nextInt();                   
            } catch (InputMismatchException e) 
            {
                System.err.println("Entered value is not an integer");
                sc.next(); // to clear the previous input data. otherwise program will keep printing ^ data.
            }
            if(choice>0&&choice<=3)
            {
                runloop='n';
            }
            else
            {
                System.out.println("Enter a valid choice.");
            }
        }
        switch(choice)
        {
            case 1:
            {
                bp = new BillPayment(x);
                break;
            }
            case 2:
            {
                sm = new SendMoney(x);
                break;
            }
            case 3:
            {
                break;
            }
            default:
                System.out.println("Enter a valid coice");
        }
        
		
	}
}
