package mainpage;

public class Water {
	public Water(int x)
	{
		Process p = new Process(x,2000);
		Thread1 t1=new Thread1(p);
		Thread2 t2 = new Thread2(p);
		t1.start();
		t2.start();
	}
	
}
