//
//---------------------------------------
//Assignment #3
//Armando Mancino (40078466)
//---------------------------------------
//
	import java.util.Scanner;
	import java.util.regex.Pattern;
	import java.io.FileInputStream;
	import java.io.PrintWriter;
	import java.io.FileOutputStream;
	import java.io.FileNotFoundException;
	/**
	 * Class designated to format files into NJ format.
	 * @author Armando Mancino (40078466)
	 *
	 */
	public class NJ 
	{	
	private static String Author;
	private static String journal;
	private static String title;
	private static String year;
	private static String volume;
	private static String Pages;
	/**
	 * To string method for simple output to file.
	 * @return String
	 */
	public static String ToString() 
	{
		return (NJ.Author+"."+" "+NJ.title+". "+NJ.journal+". "+NJ.volume+", "+NJ.Pages+"("+NJ.year+")"+".");
	}
	/**
	 * Method called to read the File and format it into NJ.
	 * @param fileName Filename passed for streams.
	 * @param Counter Tracks the current file number.
	 * @throws FileInvalidException Custom to indicate blanks.
	 * @throws FileNotFoundException File not found when opening.
	 */
	public static void readFileNJ(String fileName, int Counter) throws FileInvalidException, FileNotFoundException
	{
		Scanner Sc= new Scanner(new FileInputStream(fileName));
		String currentLine;
		String temp;
		Sc.useDelimiter(Pattern.quote("{"));
		PrintWriter pw = new PrintWriter(new FileOutputStream("NJ"+Counter+".json"));
		while (Sc.hasNextLine())
		{		
			currentLine=Sc.nextLine();
			int LHS=currentLine.indexOf("{")+1;
			int RHS=currentLine.indexOf("}");
			if (currentLine.contains("@ARTICLE")) 
			{
				Sc.nextLine();
			}
			else if (currentLine.contains("author")) 
			{
				NJ.Author=currentLine;
				NJ.Author=NJ.Author.substring(LHS,RHS);
				String [] authors=NJ.Author.split(" and ");
				NJ.Author=" ";
				for (int i=0; i<authors.length;i++) 
				{
					if (i==authors.length-1) 
					{
						NJ.Author+=authors[i];
					}
					else 
					{
						NJ.Author+=authors[i]+" & ";
					}
				}
			}
			else if (currentLine.contains("journal")) 
			{
				NJ.journal=currentLine;
				NJ.journal=NJ.journal.substring(LHS,RHS);
			}
			else if (currentLine.contains("title")) 
			{
				NJ.title=currentLine;
				NJ.title=NJ.title.substring(LHS,RHS);
			}
			else if (currentLine.contains("year")) 
			{
				NJ.year=currentLine;
				NJ.year=NJ.year.substring(LHS,RHS);
			}
			else if (currentLine.contains("volume")) 
			{
				NJ.volume=currentLine;
				NJ.volume=NJ.volume.substring(LHS,RHS);
			}
			else if (currentLine.contains("pages")) 
			{
				NJ.Pages=currentLine;
				NJ.Pages=NJ.Pages.substring(LHS,RHS);
			}
			else if (currentLine.contains("{}"))
			{
				throw new FileInvalidException(currentLine.substring(0,LHS-2));
			}
			else if (currentLine.indexOf("}")==0)
			{
				pw.println(NJ.ToString());
				pw.println();
			}
		}
		Sc.close();
		pw.close();
	}
	}


