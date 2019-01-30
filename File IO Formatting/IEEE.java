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
 * Class designated to format files into IEEE format.
 * @author Armando Mancino (40078466)
 *
 */
public class IEEE
{
	
private static String Author;
private static String journal;
private static String title;
private static String year;
private static String volume;
private static String number;
private static String Pages;
private static String month;

/**
 * To string method for simple output to file.
 * @return String
 */
public static String ToString() 
{
	return (IEEE.Author+"."+ " \""+IEEE.title+"\""+", "+IEEE.journal+", vol. "+IEEE.volume+", no. "+IEEE.number+", p. "+IEEE.Pages+", "+ IEEE.month+" "+ IEEE.year+".");
}
/**
 * Method called to read the File and format it into IEEE.
 * @param fileName Filename passed for streams.
 * @param Counter Tracks the current file number.
 * @throws FileInvalidException Custom to indicate blanks.
 * @throws FileNotFoundException File not found when opening.
 */
public static void readFileIEEE(String fileName, int Counter) throws FileInvalidException, FileNotFoundException
{
	Scanner Sc= new Scanner(new FileInputStream(fileName));
	String currentLine;
	String temp;
	Sc.useDelimiter(Pattern.quote("{"));
	PrintWriter pw = new PrintWriter(new FileOutputStream("IEEE"+Counter+".json"));
	while (Sc.hasNextLine())
	{		
		currentLine=Sc.nextLine();
		int LHS=currentLine.indexOf("{")+1;
		int RHS=currentLine.indexOf("}");
		if (currentLine.contains("@ARTICLE")) 
		{
			Sc.nextLine();
		}
		else if (currentLine.contains("{}"))
		{
			Sc.close();
			pw.close();
			throw new FileInvalidException(currentLine.substring(0,LHS-2));
		}
		else if (currentLine.contains("author")) 
		{
			IEEE.Author=currentLine;
			IEEE.Author=IEEE.Author.substring(LHS,RHS);
			String [] authors=IEEE.Author.split(" and ");
			IEEE.Author=" ";
			for (int i=0; i<authors.length;i++) 
			{
				if (i==authors.length-1) 
				{
					IEEE.Author+=authors[i];
				}
				else 
				{
					IEEE.Author+=authors[i]+", ";
				}
			}
		}
		else if (currentLine.contains("journal")) 
		{
			IEEE.journal=currentLine;
			IEEE.journal=IEEE.journal.substring(LHS,RHS);
		}
		else if (currentLine.contains("title")) 
		{
			IEEE.title=currentLine;
			IEEE.title=IEEE.title.substring(LHS,RHS);
		}
		else if (currentLine.contains("year")) 
		{
			temp=currentLine;
			temp=temp.substring(LHS,RHS);
			IEEE.year=temp;
		}
		else if (currentLine.contains("volume")) 
		{
			IEEE.volume=currentLine;
			IEEE.volume=IEEE.volume.substring(LHS,RHS);
		}
		else if (currentLine.contains("number")) 
		{
			temp=currentLine;
			temp=temp.substring(LHS,RHS);
			IEEE.number=temp;
		}
		else if (currentLine.contains("pages")) 
		{
			IEEE.Pages=currentLine;
			IEEE.Pages=IEEE.Pages.substring(LHS,RHS);
		}
		else if (currentLine.contains("month")) 
		{
			IEEE.month=currentLine;
			IEEE.month=IEEE.month.substring(LHS,RHS);
		}
		else if (currentLine.indexOf("}")==0)
		{
			pw.println(IEEE.ToString());
			pw.println();
		}
}
	Sc.close();
	pw.close();
}
}
