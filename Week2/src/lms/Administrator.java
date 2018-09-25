package lms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator extends Welcome
{
	public static void adminMain() throws SQLException
	{
		System.out.println("1) Add/Update/Delete Author");
		System.out.println("2) Add/Update/Delete Book");
		System.out.println("3) Add/Update/Delete Borrower");
		System.out.println("4) Add/Update/Delete Branch");
		System.out.println("5) Add/Update/Delete Publisher");
		System.out.println("6) Overrride Due Date for a Book Loan");
		System.out.println("7) Quit to previous menu");
		System.out.println();
		
		int choice = getChoice("Please enter a valid choice: ", 1, 7);
		
		if (choice == 1) adminAUDChoice("authors");
		else if (choice == 2) adminAUDChoice("books");
		else if (choice == 3) adminAUDChoice("borrowers");
		else if (choice == 4) adminAUDChoice("branches");
		else if (choice == 5) adminAUDChoice("publishers");
		else if (choice == 6) adminOverrideDueDate();
		else lmsMain();
	}
	
	public static void adminAUDChoice(String s) throws SQLException
	{
		System.out.println("1) Add");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Quit to previous menu");
		System.out.println();
		
		int choice = getChoice("Please enter a valid choice: ", 1, 4);
		
		if (choice == 1) adminAdd(s);
		else if (choice == 2) adminUpdate(s);
		else if (choice == 3) adminDelete(s);
		else adminMain();
	}
	
	public static void adminAdd(String s) throws SQLException
	{
		String sql = "";
		PreparedStatement ps;
		in.nextLine();
		
		if (s.equalsIgnoreCase("authors"))
		{
			System.out.print("New Author Name: ");
			String authorName = in.nextLine();
			
			sql = "INSERT INTO tbl_author SELECT MAX(authorId) + 1, ? FROM tbl_author;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, authorName);
			ps.execute();
			
			System.out.println();
			System.out.println("New author added.");
		} else if (s.equalsIgnoreCase("books"))
		{
			System.out.print("New Book Title: ");
			String title = in.nextLine();
			
			System.out.print("New Author ID: ");
			int authId = in.nextInt();
			
			System.out.print("New Publisher ID: ");
			int pubId = in.nextInt();
			
			sql = "INSERT INTO tbl_book SELECT MAX(bookId) + 1, ?, ?, ? FROM tbl_book";
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setInt(2, authId);
			ps.setInt(3, pubId);
			ps.execute();
			
			System.out.println();
			System.out.println("New book added.");
		} else if (s.equalsIgnoreCase("borrowers"))
		{
			System.out.print("New Borrower Name: ");
			String name = in.nextLine();
			
			System.out.print("New Borrower Address: ");
			String address = in.nextLine();
			
			System.out.print("New Borrower Phone: ");
			String phone = in.nextLine();
			
			sql = "INSERT INTO tbl_borrower SELECT MAX(cardNo) + 1, ?, ?, ? FROM tbl_borrower";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, phone);
			ps.execute();
			
			System.out.println();
			System.out.println("New borrower added.");
		} else if (s.equalsIgnoreCase("branches"))
		{
			System.out.print("New Branch Name: ");
			String branchName = in.nextLine();
			
			System.out.print("New Branch Address: ");
			String branchAddress = in.nextLine();
			
			sql = "INSERT INTO tbl_library_branch SELECT MAX(branchId) + 1, ?, ? FROM tbl_library_branch";
			ps = conn.prepareStatement(sql);
			ps.setString(1, branchName);
			ps.setString(2, branchAddress);
			ps.execute();
			
			System.out.println();
			System.out.println("New branch added.");
		} else
		{
			System.out.print("New Publisher Name: ");
			String publisherName = in.nextLine();
			
			System.out.print("New Publisher Address: ");
			String publisherAddress = in.nextLine();
			
			System.out.print("New Publisher Phone: ");
			String publisherPhone = in.nextLine();
			
			sql = "INSERT INTO tbl_publisher SELECT MAX(publisherId) + 1, ?, ?, ? FROM tbl_publisher";
			ps = conn.prepareStatement(sql);
			ps.setString(1, publisherName);
			ps.setString(2, publisherAddress);
			ps.setString(3, publisherPhone);
			ps.execute();
			
			System.out.println();
			System.out.println("New publisher added.");
		}
		
		System.out.println();
		adminAUDChoice(s);
	}
	
	public static void adminUpdate(String s) throws SQLException
	{
		int[] ID = new int[10000];
		int i = printMenu(s, ID);
		int choice = getChoice("Please enter a valid choice: ", 1, i);
		
		if (choice < i)
		{
			String sql = "";
			PreparedStatement ps;
			in.nextLine();
			
			if (s.equalsIgnoreCase("authors"))
			{
				System.out.print("New Author Name: ");
				String authorName = in.nextLine();
				
				sql = "UPDATE tbl_author SET authorName = ? WHERE authorId = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, authorName);
				ps.setInt(2, ID[choice]);
				ps.execute();
				
				System.out.println();
				System.out.println("Author " + choice + " updated.");
			} else if (s.equalsIgnoreCase("books"))
			{
				System.out.print("New Book Title: ");
				String title = in.nextLine();
				
				System.out.print("New Author ID: ");
				int authId = in.nextInt();
				
				System.out.print("New Publisher ID: ");
				int pubId = in.nextInt();
				
				sql = "UPDATE tbl_book SET title = ?, authId = ?, pubId = ? WHERE bookId = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, title);
				ps.setInt(2, authId);
				ps.setInt(3, pubId);
				ps.setInt(4, ID[choice]);
				ps.execute();
				
				System.out.println();
				System.out.println("Book " + choice + " updated.");
			} else if (s.equalsIgnoreCase("borrowers"))
			{
				System.out.print("New Borrower Name: ");
				String name = in.nextLine();
				
				System.out.print("New Borrower Address: ");
				String address = in.nextLine();
				
				System.out.print("New Borrower Phone: ");
				String phone = in.nextLine();
				
				sql = "UPDATE tbl_borrower SET name = ?, address = ?, phone = ? WHERE cardNo = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, address);
				ps.setString(3, phone);
				ps.setInt(4, ID[choice]);
				ps.execute();
				
				System.out.println();
				System.out.println("Borrower " + choice + " updated.");
			} else if (s.equalsIgnoreCase("branches"))
			{
				System.out.print("New Branch Name: ");
				String branchName = in.nextLine();
				
				System.out.print("New Branch Address: ");
				String branchAddress = in.nextLine();
				
				sql = "UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, branchName);
				ps.setString(2, branchAddress);
				ps.setInt(3, ID[choice]);
				ps.execute();
				
				System.out.println();
				System.out.println("Branch " + choice + " updated.");
			} else
			{
				System.out.print("New Publisher Name: ");
				String publisherName = in.nextLine();
				
				System.out.print("New Publisher Address: ");
				String publisherAddress = in.nextLine();
				
				System.out.print("New Publisher Phone: ");
				String publisherPhone = in.nextLine();
				
				sql = "UPDATE tbl_publisher SET publisherName = ?, publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, publisherName);
				ps.setString(2, publisherAddress);
				ps.setString(3, publisherPhone);
				ps.setInt(4, ID[choice]);
				ps.execute();
				
				System.out.println();
				System.out.println("Publisher " + choice + " updated.");
			}
		}
		
		System.out.println();
		adminAUDChoice(s);
	}
	
	public static void adminDelete(String s) throws SQLException
	{
		int[] ID = new int[10000];
		int i = printMenu(s, ID);
		int choice = getChoice("Please enter a valid choice: ", 1, i);
		
		if (choice < i)
		{
			String sql = "";
			PreparedStatement ps;
			
			if (s.equalsIgnoreCase("authors")) sql = "DELETE FROM tbl_author WHERE authorId = ?";
			else if (s.equalsIgnoreCase("books")) sql = "DELETE FROM tbl_book WHERE bookId = ?";
			else if (s.equalsIgnoreCase("borrowers")) sql = "DELETE FROM tbl_borrower WHERE cardNo = ?";
			else if (s.equalsIgnoreCase("branches")) sql = "DELETE FROM tbl_library_branch WHERE branchId = ?";
			else sql = "DELETE FROM tbl_publisher WHERE publisherId = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ID[choice]);
			ps.execute();
			
			String s2 = Character.toUpperCase(s.charAt(0)) + s.substring(1, s.length() - 1);
			System.out.format("Deleted %s %d.%n%n", s2, choice);
			
			adminDelete(s);
		} else adminAUDChoice(s);
	}
	
	public static void adminOverrideDueDate() throws SQLException
	{
		String sql = "SELECT bookId, branchId, cardNo, dueDate FROM tbl_book_loans";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		int i = 0;
		int[][] memo = new int[10000][3];
		while (rs.next())
		{
			i++;
			
			int bookId = rs.getInt("bookId");
			int branchId = rs.getInt("branchId");
			int cardNo = rs.getInt("cardNo");
			
			memo[i][0] = bookId;
			memo[i][1] = branchId;
			memo[i][2] = cardNo;
			
			System.out.format("%d)\tBook: %d\tBranch: %d\tCard: %d\tDue: %s%n", i, bookId, branchId, cardNo, rs.getDate("dueDate"));
		}
		i++;
		
		System.out.println(i + ")\tQuit to previous menu");
		System.out.println();
		
		int choice = getChoice("Please enter a valid choice: ", 1, i);
		
		if (choice < i)
		{
			sql = "UPDATE tbl_book_loans SET dueDate = DATE_ADD(NOW(), INTERVAL 1 WEEK) WHERE bookId = ? AND branchId = ? AND cardNo = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memo[choice][0]);
			ps.setInt(2, memo[choice][1]);
			ps.setInt(3, memo[choice][2]);
			ps.executeUpdate();
			
			System.out.format("Book %d at Branch %d with Card %d is now due in 1 week.%n%n", memo[choice][0], memo[choice][1], memo[choice][2]);
			adminOverrideDueDate();
		} else adminMain();
	}
}