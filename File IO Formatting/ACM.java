//
//---------------------------------------
//Assignment #3
//Armando Mancino (40078466)
//---------------------------------------
//
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * Class designated to format files into ACM format.
 * @author Armando Mancino (40078466)
 *
 */
public class ACM 
{
	private static String Author;
	private static String journal;
	private static String title;
	private static String year;
	private static String volume;
	private static String number;
	private static String Pages;
	private static String doi;
	private static int EntryCounter;
	/**
	 * To string method for simple output to file.
	 * @return String
	 */
	public static String ToString() 
	{
		return ("["+EntryCounter+"]     "+ACM.Author+". "+ACM.year+". "+ACM.title+". "+ACM.journal+". "+ACM.volume+", "+ACM.number+" ("+ACM.year+")"+", "+ACM.Pages+". DOI:https://doi.org/"+ACM.doi+".");
	}
	/**
	 * Method called to read the File and format it into ACM.
	 * @param fileName Filename passed for streams.
	 * @param Counter Tracks the current file number.
	 * @throws FileInvalidException Custom to indicate blanks.
	 * @throws FileNotFoundException File not found when opening.
	 */
	public static void readFileACM(String fileName, int Counter) throws FileInvalidException, FileNotFoundException
	{
		Scanner Sc= new Scanner(new FileInputStream(fileName));
		String currentLine;
		String temp;
		Sc.useDelimiter(Pattern.quote("{"));
		PrintWriter pw = new PrintWriter(new FileOutputStream("ACM"+Counter+".json"));
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
				ACM.Author=currentLine;
				ACM.Author=ACM.Author.substring(LHS,RHS);
				String [] authors=ACM.Author.split(" and ");
				ACM.Author=authors[0]+ " et al";
			}
			else if (currentLine.contains("journal")) 
			{
				ACM.journal=currentLine;
				ACM.journal=ACM.journal.substring(LHS,RHS);
			}
			else if (currentLine.contains("title")) 
			{
				ACM.title=currentLine;
				ACM.title=ACM.title.substring(LHS,RHS);
			}
			else if (currentLine.contains("year")) 
			{
				ACM.year=currentLine;
				ACM.year=ACM.year.substring(LHS,RHS);
			}
			else if (currentLine.contains("volume")) 
			{
				ACM.volume=currentLine;
				ACM.volume=ACM.volume.substring(LHS,RHS);
			}
			else if (currentLine.contains("number")) 
			{
				ACM.number=currentLine;
				ACM.number=ACM.number.substring(LHS,RHS);
			}
			else if (currentLine.contains("pages")) 
			{
				ACM.Pages=currentLine;
				ACM.Pages=ACM.Pages.substring(LHS,RHS);
			}
			else if (currentLine.contains("doi")) 
			{
				ACM.doi=currentLine;
				ACM.doi=ACM.doi.substring(LHS,RHS);
			}
			else if (currentLine.contains("{}"))
			{
				throw new FileInvalidException(currentLine.substring(0,LHS-2));
			}
			else if (currentLine.indexOf("}")==0)
			{
				EntryCounter++;
				pw.println(ACM.ToString());
				pw.println();
			}
		}
		Sc.close();
		pw.close();
	}
}
