public class Day4
{
	public static void main(String[] args)
	{
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();

		System.out.println("Singleton one has " + s1.getInstances() + " instance(s).");
		System.out.println("Singleton two has " + s2.getInstances() + " instance(s).");
		System.out.println();



		Person bill = new Person("Bill");
		Person ted = new Person("Ted");

		bill.setFriend(ted);
		ted.setFriend(bill);

		Thread t1 = new Thread(bill);
		Thread t2 = new Thread(ted);

		//t1.start();
		//t2.start();
		
		endThread(t1);
		endThread(t2);



		Queue q = new Queue();

		Producer p = new Producer(q);
		Consumer c = new Consumer(q);

		Thread t3 = new Thread(p);
		Thread t4 = new Thread(c);

		t3.start();
		t4.start();

		endThread(t3);
		endThread(t4);
	}

	public static void endThread(Thread t)
	{
		try
		{
			t.join();
		}
		catch (InterruptedException e) {}
	}
}