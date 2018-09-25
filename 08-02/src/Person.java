public class Person implements Runnable
{
	private String name;
	private Person friend;

	public Person(String name)
	{
		this.name = name;
	}

	public void setFriend(Person friend)
	{
		this.friend = friend;
	}

	public String name()
	{
		return this.name;
	}

	public synchronized void greet()
	{
		System.out.format("%s: Hello %s!%n", this.name, friend.name() );
		friend.respond();
	}

	public synchronized void respond()
	{
		System.out.format("%s: Nice day %s!%n", this.name, friend.name() );
	}

	@Override
	public void run()
	{
		greet();
	}
}