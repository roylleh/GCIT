import java.util.concurrent.Semaphore;

public class Queue
{
	int i;
	
	Semaphore SP = new Semaphore(1);
	Semaphore SC = new Semaphore(0);
	
	void produce(int i)
	{
		try
		{
			SP.acquire();
		}
		catch(InterruptedException e) {}
		
		this.i = i;
		System.out.println("Produced: " + i);
		SC.release();
	}
	
	void consume()
	{
		try
		{
			SC.acquire();
		}
		catch(InterruptedException e) {}
		
		System.out.println("Consumed: " + i);
		SP.release();
	}
}

class Producer implements Runnable
{
	Queue q;
	
	Producer(Queue q)
	{
		this.q = q;
	}
	
	@Override
	public void run()
	{
		for(int i = 1; i <= 5; i++)
			q.produce(i);
	}
}

class Consumer implements Runnable
{
	Queue q;
	
	Consumer(Queue q)
	{
		this.q = q;
	}
	
	@Override
	public void run()
	{
		for(int i = 1; i <= 5; i++)
			q.consume();
	}
}