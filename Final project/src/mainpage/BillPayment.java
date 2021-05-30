package mainpage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public class BillPayment {
	int x;
	public BillPayment(int x)
	{
		this.x = x;
		Scanner sc = new Scanner(System.in);
		KarachiElectric ke;
		Gas g;
		Water w;
		char runloop = 'y';
		int choice = 0;
		System.out.println("\t\t*****Welcome to HBL online banking.*****");
        System.out.println("\n\nPress 1 if you want to pay bill of K.E");
		System.out.println("Press 2 if you want to pay bill of SSGC");
		System.out.println("Press 3 if you want to pay bill of Water");
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
	            	ke = new KarachiElectric(x);
	                break;
	            }
	            case 2:
	            {
	            	g = new Gas(x);
	                break;
	            }
	            case 3:
	            {
	            	w = new Water(x);
	                break;
	            }
	            default:
	                System.out.println("Enter a valid coice");
	        }
	}
	
}
