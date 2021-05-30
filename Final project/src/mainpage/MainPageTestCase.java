package mainpage;

import static org.junit.Assert.*;

import org.junit.Test;


public class MainPageTestCase {

	@Test
	public void test() {
		Dashboard db = new Dashboard();
		db.menu(2);
	}

}
