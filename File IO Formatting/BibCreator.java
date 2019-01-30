//
//---------------------------------------
//Assignment #3
//Armando Mancino (40078466)
//---------------------------------------
//
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * The driver class that loops to open 10 files and formats them if possible.
 * @author Armando Mancino (40078466)
 *
 */
public class BibCreator {
protected static int Counter=0;
protected static int tries=0;
	public static void main(String[] args) throws FileInvalidException, FileNotFoundException
	{
		System.out.println("Welcome to BibCreator!");
		System.out.println();
		for (int C=1; C<11; C++) 
		{
			try 
			{
				IEEE.readFileIEEE("Latex"+C+".bib", C);
				ACM.readFileACM("Latex"+C+".bib",C);
				NJ.readFileNJ("Latex"+C+".bib",C);
			}
			catch (FileInvalidException e)
			{
				Counter++;
				System.out.println(e);
				System.out.println("==================================================");
				System.out.println("Problem detected with input file: Latex"+C+".bib");
				System.out.println("File is Invalid: Field \""+e.error+"\" is Empty. Processing stopped at this point. Other empty fields may be present as well!");
				
				File f1 = new File ("IEEE"+C+".json"); //exception cancels the 
				f1.delete();
			}
		}
		System.out.println();
		System.out.println("A total of "+Counter+" files were invalid, and could not be processed. All other "+(10-Counter)+" \"Valid\" files have been created.");
		System.out.println();
		String file=" ";
		while (tries<=3) 
		{
			if (tries>=3) 
			{
				System.out.println("Error! 3 attempts have been used");
				System.exit(0);
			}
			else 
			{
			try 
			{
				System.out.print("Please enter the name of one of the files that you need to review:");
				Scanner theScanner= new Scanner(System.in);
				file=theScanner.nextLine();
				File f=new File(file);
				if (f.exists()) 
				{
					break;
				}
				else 
				{
					throw new FileNotFoundException();	
				}
			}
			catch (FileNotFoundException e) 
			{
				System.out.println("Could not open input file. File does not exist; possibly it could not be created");
				tries++;
				System.out.println();
			}
			}
		}
		System.out.println("Here are the contents of the successfully created json File:"+file);
		BufferedReader readObject=null;
		try 
		{
			readObject= new BufferedReader(new FileReader(file));
			String s;
			s=readObject.readLine();
			while (s!=null) 
			{
				System.out.println(s);
				s=readObject.readLine();
			}
			readObject.close();
		}
		catch (IOException e) 
		{
			System.out.println("Error opening file");
		}
		
	}
}
