package lms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Librarian extends Welcome
{
	public static void libMain() throws SQLException
	{
		System.out.println("1) Enter branch to manage");
		System.out.println("2) Quit to previous menu");
		System.out.println();

		int choice = getChoice("Please enter a valid choice: ", 1, 2);

		if(choice == 1)	libPrintBranches();
		else			lmsMain();
	}

	public static void libPrintBranches() throws SQLException
	{
		int[] ID = new int[10000];
		int i = printMenu("branches", ID);
		int choice = getChoice("Please enter a valid choice: ", 1, i);

		if(choice < i)	libPickBranch( ID[choice] );
		else			libMain();
	}

	public static void libPickBranch(int branchId) throws SQLException
	{
		System.out.println("1) Update branch details");
		System.out.println("2) Add copies of book to branch");
		System.out.println("3) Quit to previous menu");
		System.out.println();

		int choice = getChoice("Please enter a valid choice: ", 1, 3);

		if(choice == 1)			libUpdateBranch(branchId);
		else if(choice == 2)	libPrintBranchBooks(branchId);
		else					libPrintBranches();
	}

	public static void libUpdateBranch(int branchId) throws SQLException
	{
		String sql;
		PreparedStatement ps;
		in.nextLine();

		System.out.print("Please enter new branch name or type N/A for no change: ");
		String name = in.nextLine();

		System.out.print("Please enter new branch address or type N/A for no change: ");
		String address = in.nextLine();

		System.out.println();
		if( !name.equalsIgnoreCase("N/A") )
		{
			sql = "UPDATE tbl_library_branch SET branchName = ? WHERE branchId = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, branchId);
			ps.executeUpdate();

			System.out.format("Branch %d updated with Name: '%s'%n", branchId, name);
		}
		if( !address.equalsIgnoreCase("N/A") )
		{
			sql = "UPDATE tbl_library_branch SET branchAddress = ? WHERE branchId = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, address);
			ps.setInt(2, branchId);
			ps.executeUpdate();

			System.out.format("Branch %d updated with Address: '%s'%n", branchId, address);
		}

		System.out.println();
		libPickBranch(branchId);
	}

	public static void libPrintBranchBooks(int branchId) throws SQLException
	{
		int[] ID = new int[10000];
		int i = printBranchBooks(branchId, ID);
		int choice = getChoice( "Please enter a valid choice: ", 1, i );

		if(choice < i)	libUpdateBranchCopies( branchId, ID[choice] );
		else			libPickBranch(branchId);
	}

	public static void libUpdateBranchCopies(int branchId, int bookId) throws SQLException
	{
		String sql = "SELECT noOfCopies FROM tbl_book_copies WHERE bookId = ? AND branchId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, bookId);
		ps.setInt(2, branchId);

		ResultSet rs = ps.executeQuery();
		rs.next();

		int noOfCopies = rs.getInt("noOfCopies");
		System.out.println("Current number of copies: " + noOfCopies);
		System.out.print("New number of copies: ");
		int newNoOfCopies = in.nextInt();

		sql = "UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, newNoOfCopies);
		ps.setInt(2, bookId);
		ps.setInt(3, branchId);
		ps.executeUpdate();

		System.out.println();
		libPrintBranchBooks(branchId);
	}
}