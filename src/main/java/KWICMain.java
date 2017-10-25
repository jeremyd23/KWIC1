import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * Main program version of KWIC with method calls, all accessing shared data.
 * Accepts a file with lines of text. Circularly shifts the first word in each line to the end of that line.
 * Sorts the lines of text in order and then prints to results.txt and the console.
 */
public class KWICMain
{
    private static Scanner input;
    private static ArrayList<LinkedList> textFile = new ArrayList<>();

    /**
     * Main controller
     * @param args
     */
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);

        System.out.println("Please enter a file name (poem OR long)");
        String fileName = console.nextLine();

        input(fileName);
        circularShift();
        sort();
        output();
    }

    private static void input(String fileName)
    {
        try
        {
            input = new Scanner(new FileInputStream(fileName + ".txt"));

            while(input.hasNextLine())
            {
                String line = input.nextLine();
                String delims = "[ ]+";

                LinkedList<String> lineWords = new LinkedList(Arrays.asList(line.replace(",", "").split(delims)));
                textFile.add(lineWords);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        finally
        {
            input.close();
        }
    }

    private static void circularShift()
    {
        for(int i = 0; i < textFile.size(); i++)
        {
            String first = (String)textFile.get(i).getFirst();
            textFile.get(i).removeFirst();
            textFile.get(i).add(first);
        }
    }

    private static void sort()
    {
        Collections.sort(textFile, new Comparator<LinkedList>()
        {
            @Override
            public int compare(LinkedList first, LinkedList second)
            {
                String list1 = first.getFirst().toString().toLowerCase();
                String list2 = second.getFirst().toString().toLowerCase();
                if(list1.compareTo(list2) == 0)
                {
                    list1 = first.get(1).toString().toLowerCase();
                    list2 = second.get(1).toString().toLowerCase();
                }
                return list1.compareTo(list2);
            }
        });
    }

    private static void output()
    {
        PrintWriter output = null;
        try
        {
            output = new PrintWriter(new FileOutputStream("results.txt"));

            for (LinkedList line : textFile)
            {
                output.println(line);
                System.out.println(line);
            }

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File name does not exist");
        }
        finally
        {
           output.close();
        }
    }
}