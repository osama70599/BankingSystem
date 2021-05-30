package mainpage;

public class KarachiElectric {
	public KarachiElectric(int x)
	{
		Process p = new Process(x,10000);
		Thread1 t1=new Thread1(p);
		Thread2 t2 = new Thread2(p);
		t1.start();
		t2.start();
	}

}
