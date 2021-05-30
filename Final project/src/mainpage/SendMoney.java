package mainpage;



public class SendMoney {
	final int x;
	public SendMoney(int x)
	{
		this.x = x;
		Forwading f = new Forwading(x);
		Thread3 t3=new Thread3(f);
		Thread4 t4 = new Thread4(f);
		t3.start();
		t4.start();
	}
}