package lms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Borrower extends Welcome
{
	public static void borrMain() throws SQLException
	{
		String sql = "SELECT MAX(cardNo) FROM tbl_borrower";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		int max = rs.getInt("MAX(cardNo)");
		int cardNo = getChoice("Please enter a valid card number: ", 1, max);
		
		System.out.println("1) Check out a book");
		System.out.println("2) Return a book");
		System.out.println("3) Quit to previous menu");
		System.out.println();
		
		int choice = getChoice("Please enter a valid choice: ", 1, 3);
		
		if (choice == 1) borrCheckoutPrintBranches(cardNo);
		else if (choice == 2) borrReturnPrintBranches(cardNo);
		else lmsMain();
	}
	
	public static void borrCheckoutPrintBranches(int cardNo) throws SQLException
	{
		int[] ID = new int[10000];
		int i = printMenu("branches", ID);
		int choice = getChoice("Please enter a valid choice: ", 1, i);
		
		if (choice < i) borrCheckoutPrintBranchBooks(cardNo, ID[choice]);
		else borrMain();
	}
	
	public static void borrCheckoutPrintBranchBooks(int cardNo, int branchId) throws SQLException
	{
		int[] ID = new int[10000];
		int i = printBranchBooks(branchId, ID);
		int choice = getChoice("Please enter a valid choice: ", 1, i);
		
		if (choice < i) borrCheckoutDo(cardNo, branchId, ID[choice]);
		else borrCheckoutPrintBranches(cardNo);
	}
	
	public static void borrCheckoutDo(int cardNo, int branchId, int bookId) throws SQLException
	{
		try
		{
			String sql = "INSERT INTO tbl_book_loans VALUES ( ?, ?, ?, NOW(), DATE_ADD(NOW(), INTERVAL 1 WEEK) )";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setInt(2, branchId);
			ps.setInt(3, cardNo);
			ps.execute();
			
			System.out.format("Successfully checked out Book %d from Branch %d.%n%n", bookId, branchId);
		} catch (SQLException e)
		{
			System.out.format("You've already checked out Book %d from Branch %d!%n%n", bookId, branchId);
		}
		
		borrCheckoutPrintBranchBooks(cardNo, branchId);
	}
	
	public static void borrReturnPrintBranches(int cardNo) throws SQLException
	{
		int[] ID = new int[10000];
		int i = printMenu("branches", ID);
		int choice = getChoice("Please enter a valid choice: ", 1, i);
		
		if (choice < i) borrReturnPrintBranchBooks(cardNo, ID[choice]);
		else borrMain();
	}
	
	public static void borrReturnPrintBranchBooks(int cardNo, int branchId) throws SQLException
	{
		String sql = "SELECT tbl_book.bookId, title, authorName FROM tbl_book " + "JOIN tbl_author ON tbl_book.authId = tbl_author.authorId " + "JOIN tbl_book_loans ON tbl_book.bookId = tbl_book_loans.bookId " + "WHERE branchId = ? AND cardNo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, branchId);
		ps.setInt(2, cardNo);
		ResultSet rs = ps.executeQuery();
		
		int i = 0;
		int[] ID = new int[10000];
		while (rs.next())
		{
			i++;
			ID[i] = rs.getInt("tbl_book.bookId");
			System.out.format("%d) %s by %s%n", i, rs.getString("title"), rs.getString("authorName"));
		}
		i++;
		
		System.out.println(i + ") Quit to previous menu");
		System.out.println();
		
		int choice = getChoice("Please enter a valid choice: ", 1, i);
		
		if (choice < i) borrReturnDo(cardNo, branchId, ID[choice]);
		else borrReturnPrintBranches(cardNo);
	}
	
	public static void borrReturnDo(int cardNo, int branchId, int bookId) throws SQLException
	{
		String sql = "DELETE FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, bookId);
		ps.setInt(2, branchId);
		ps.setInt(3, cardNo);
		ps.execute();
		
		System.out.format("Successfully returned Book %d from Branch %d.%n%n", bookId, branchId);
		borrReturnPrintBranchBooks(cardNo, branchId);
	}
}