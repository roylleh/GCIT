import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SampleSingleton
{
	private static SampleSingleton instance = null;
	private Connection conn = null;
	private SampleSingleton() {}

	public static SampleSingleton getInstance()
	{
		if(instance == null)
		{
			synchronized(SampleSingleton.class)
			{
				if(instance == null)
					instance = new SampleSingleton();
			}
		}

		return instance;
	}

	public void databaseQuery(BigDecimal input) throws SQLException
	{
		conn = DriverManager.getConnection("url of database");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select id from table");
		BigDecimal x = new BigDecimal(0);

		while( rs.next() )
			x = new BigDecimal( rs.getInt(1) ).multiply(input);

		System.out.println(x);
	}
}