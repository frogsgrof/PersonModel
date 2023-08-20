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

            // prints out the header to the table
            String tableHeader = "ID%6s | First Name%2s | Last Name%3s | Title | YOB%n";
            System.out.printf(tableHeader, " ", " ", " ");

            for (int i = 0; i < tableHeader.length(); i++)
                System.out.print("=");

            // iterates through each person in the list, separating the elements and printing them in table format
            for (String line : peopleData) {

                // for storing the person object
                Person person = new Person();

                // instantiates default widths for the table (one for each column)
                // in the printf function, these are used to mark the empty space
                int width1 = 8,
                        width2 = 12,
                        width3 = 12,
                        width4 = 5,
                        width5 = 4;

                // for marking endpoints of data elements in order to make substrings
                int left;
                int right;

                // the right pointer starts at zero and increments until it hits a comma
                for (right = 0; right < line.length() && line.charAt(right) != ','; right++);

                // then it sets the ID as a substring between zero and the index of the comma
                person.id = line.substring(0, right);

                // since the width variables are for counting the whitespace, if the ID's length exceeds the default
                // width, it gets set to zero.
                if (person.id.length() > width1)
                    width1 = 0;
                // otherwise, the length of the string gets subtracted from the whitespace.
                else
                    width1 -= person.id.length();

                // now the left pointer is initialized. Since right marks the comma separator, right + 2 would
                // put the pointer at the first character of the person's first name.
                left = right + 2;

                // this whole process repeats for each person in the file.
                for (right = left; right < line.length() && line.charAt(right) != ','; right++);
                person.firstName = line.substring(left, right);

                if (person.firstName.length() > width2)
                    width2 = 0;
                else
                    width2 -= person.firstName.length();

                left = right + 2;
                for (right = left; right < line.length() && line.charAt(right) != ','; right++);
                person.lastName = line.substring(left, right);

                if (person.lastName.length() > width3)
                    width3 = 0;
                else
                    width3 -= person.lastName.length();

                left = right + 2;
                for (right = left; right < line.length() && line.charAt(right) != ','; right++);
                person.title = line.substring(left, right);

                if (person.title.length() > width4)
                    width4 = 0;
                else
                    width4 -= person.title.length();

                person.yearOfBirth = Integer.parseInt(line.substring(right + 2));

                if (line.length() - right + 2 > width5)
                    width5 = 0;
                else
                    width5 -= (line.length() - right + 2);

                // adds the completed person to the list
                people.add(person);

                // MOVE THIS!
                // before the loop continues to the next person in the file, it prints out the full row to console
                System.out.printf("%n" + person.id + "%" + width1 + "s | " + person.firstName + "%" + width2 + "s | " + person.lastName + "%" + width3 + "s | " + person.title + "%" + width4 + "s | " + person.yearOfBirth, " ", " ", " ", " ");
            }

            // iterates through list of Persons
            for (Person person : people) {

            }

            System.out.println();
        }
    }


}
