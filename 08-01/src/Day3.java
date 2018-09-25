import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Day3
{
	public static void main(String[] args)
	{
		char targetChar = 'e';
		String targetFile = "dummy.txt";

		if(args.length >= 1)
			targetChar = args[0].charAt(0);

		File[] files = new File(".").listFiles();
		int chars = 0;

		for(File f: files)
		{
			String fileOrFolder = f.getName();

			if( f.isFile() && fileOrFolder.equals(targetFile) )
			{
				try( FileWriter fw = new FileWriter(fileOrFolder, true) )
				{
					fw.write(targetChar + "\n");
				}
				catch(IOException e)
				{
					System.out.println("Could not access file or folder.");
				}

				try( Scanner in = new Scanner(f) )
				{
					while( in.hasNextLine() )
					{
						String line = in.nextLine();
						chars += line.length();
					}
				}
				catch(FileNotFoundException e)
				{
					System.out.println("Could not access file or folder.");
				}
			}

			System.out.println(fileOrFolder);
		}

		System.out.format("%n%s has %d '%s' characters", targetFile, chars, targetChar);
	}
}