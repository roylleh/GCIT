package lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome
{
	public static Scanner		in;
	public static Connection	conn;
	
	public static void main(String[] args) throws SQLException
	{
		String host = "jdbc:mysql://mysql.cv82vp5r6wmd.us-east-1.rds.amazonaws.com:3306/library?useSSL=false";
		String user = "root";
		String pass = "password";
		
		in = new Scanner(System.in);
		try
		{
			conn = DriverManager.getConnection(host, user, pass);
			lmsMain();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		in.close();
		conn.close();
	}
	
	public static int getChoice(String s, int low, int high) throws SQLException
	{
		int n = 0;
		while (n < low || n > high)
		{
			System.out.print(s);
			while (!in.hasNextInt())
			{
				System.out.print(s);
				in.next();
			}
			n = in.nextInt();
		}
		
		System.out.println();
		return n;
	}
	
	public static int printMenu(String s, int[] ID) throws SQLException
	{
		String sql = "";
		String column1 = "";
		String column2 = "";
		
		if (s.equalsIgnoreCase("authors"))
		{
			sql = "SELECT authorId, authorName FROM tbl_author";
			column1 = "authorId";
			column2 = "authorName";
		} else if (s.equalsIgnoreCase("books"))
		{
			sql = "SELECT bookId, title FROM tbl_book";
			column1 = "bookId";
			column2 = "title";
		} else if (s.equalsIgnoreCase("borrowers"))
		{
			sql = "SELECT cardNo, name FROM tbl_borrower";
			column1 = "cardNo";
			column2 = "name";
		} else if (s.equalsIgnoreCase("branches"))
		{
			sql = "SELECT branchId, branchName FROM tbl_library_branch";
			column1 = "branchId";
			column2 = "branchName";
		} else
		{
			sql = "SELECT publisherId, publisherName FROM tbl_publisher";
			column1 = "publisherId";
			column2 = "publisherName";
		}
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		int i = 0;
		while (rs.next())
		{
			i++;
			ID[i] = rs.getInt(column1);
			System.out.println(i + ") " + rs.getString(column2));
		}
		i++;
		
		System.out.println(i + ") Quit to previous menu");
		System.out.println();
		
		return i;
	}
	
	public static int printBranchBooks(int branchId, int[] ID) throws SQLException
	{
		String sql = "SELECT tbl_book.bookId, title, authorName FROM tbl_book " + "JOIN tbl_author ON tbl_book.authId = tbl_author.authorId " + "JOIN tbl_book_copies ON tbl_book.bookId = tbl_book_copies.bookId " + "WHERE branchId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, branchId);
		ResultSet rs = ps.executeQuery();
		
		int i = 0;
		while (rs.next())
		{
			i++;
			ID[i] = rs.getInt("tbl_book.bookId");
			System.out.format("%d) %s by %s%n", i, rs.getString("title"), rs.getString("authorName"));
		}
		i++;
		
		System.out.println(i + ") Quit to previous menu");
		System.out.println();
		
		return i;
	}
	
	public static void lmsMain() throws SQLException
	{
		System.out.println("Welcome to the GCIT Library Management System! Which category of user are you?");
		System.out.println();
		
		System.out.println("1) Librarian");
		System.out.println("2) Borrower");
		System.out.println("3) Administrator");
		System.out.println("4) Quit");
		System.out.println();
		
		int choice = getChoice("Please enter a valid choice: ", 1, 4);
		
		if (choice == 1) Librarian.libMain();
		else if (choice == 2) Borrower.borrMain();
		else if (choice == 3) Administrator.adminMain();
	}
}