import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static javafx.scene.input.KeyCode.T;

public class KWICMain
{
    private static Scanner input;
    private static ArrayList<LinkedList> textFile = new ArrayList<>();

    public static void main(String[] args)
    {
        boolean done = false;
        Scanner console = new Scanner(System.in);

        //do
        //{
            input(console);

            circularShift();
            //output();
        //}
        //while (!done);

    }

    public static void input(Scanner console)
    {
        System.out.println("Please enter a file name (poem)");
        String fileName = console.nextLine();

        try
        {
            input = new Scanner(new FileInputStream(fileName + ".txt"));

            while(input.hasNextLine())
            {
                String line = input.nextLine();
                String delims = "[ ]+";

                LinkedList<String> lineWords = new LinkedList(Arrays.asList(line.split(delims)));
                textFile.add(lineWords);
            }


        }
        catch (FileNotFoundException e)
        {
            e.getMessage();
        }
        finally
        {
            input.close();
        }
    }

    public static void circularShift()
    {
        for(int i = 0; i < textFile.size(); i++)
        {
            String first = (String)textFile.get(i).getFirst();
            textFile.get(i).removeFirst();
            textFile.get(i).add(first);

            System.out.println(textFile.get(i));
        }
        System.out.println();

        Collections.sort(textFile, new Comparator<LinkedList>()
        {
            @Override
            public int compare(LinkedList first, LinkedList second)
            {
                String list1 = first.getFirst().toString().toLowerCase();
                String list2 = second.getFirst().toString().toLowerCase();
                return list1.compareTo(list2);
            }
        });



        for(int i = 0; i < textFile.size(); i++)
        {
            System.out.println(textFile.get(i));
        }
    }

    public static void output()
    {

    }
}
