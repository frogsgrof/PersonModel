import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader {

    public static void main(String[] args) {

        // for storing the data read from the text file
        ArrayList<String> peopleData = new ArrayList<>();

        // for storing the Person objects
        ArrayList<Person> people = new ArrayList<>();

        // sets the working directory to the user's current directory (assuming that directory is this project)
        File directory = new File(System.getProperty("user.dir") + "//src//");
        File personFile; // for storing the file they pick

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(directory);

        // for limiting the JFileChooser's options to files with the .txt extension
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        jFileChooser.setAcceptAllFileFilterUsed(false);

        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            // when they select a file, it gets ready to read it

            personFile = jFileChooser.getSelectedFile();
            Path path = personFile.toPath();

            try {
                InputStream inputStream = new BufferedInputStream(Files.newInputStream(path, CREATE));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                while (bufferedReader.ready()) {
                    // each line of the file, it instantiates a new String, stores the line in the string, and adds it to the list
                    String line = bufferedReader.readLine();

                    peopleData.add(line);
                }

                bufferedReader.close(); // closes reader

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // for storing each String in peopleData after splitting them
            String[] personElems;

            // iterates through each person in the list, separating the elements and printing them in table format
            for (String line : peopleData) {

                // splits the String into the array
                personElems = line.split(", ", 5);

                // passes all the array elements into the Person constructor
                Person person = new Person(personElems[0], personElems[1],
                        personElems[2], personElems[3], Integer.parseInt(personElems[4]));

                // adds the completed person to the list
                people.add(person);
            }

            // finally prints the table

            // table header
            System.out.printf("%8s   %16s   %16s   %5s   %4s%n", "ID", "FIRST NAME", "LAST NAME", "TITLE", "YOB");
            int len = 8 + 16 + 16 + 5 + 4 + 12;
            for (int i = 0; i < len; i++)
                System.out.print("=");
            System.out.println();

            for (Person person : people)
                System.out.println(person.getTableRow());
        }
    }


}
