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

            // for storing the individual components to be extracted from each line of the file
            String id,
                    firstName,
                    lastName,
                    title,
                    yearOfBirth;

            // prints out the header to the table
            String tableHeader = "ID%6s | First Name%2s | Last Name%3s | Title | YOB%n";
            System.out.printf(tableHeader, " ", " ", " ");

            for (int i = 0; i < tableHeader.length(); i++)
                System.out.print("=");

            // iterates through each person in the list, separating the elements and printing them in table format
            for (String person : peopleData) {

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
                for (right = 0; right < person.length() && person.charAt(right) != ','; right++);

                // then it sets the ID as a substring between zero and the index of the comma
                id = person.substring(0, right);

                // since the width variables are for counting the whitespace, if the ID's length exceeds the default
                // width, it gets set to zero.
                if (id.length() > width1)
                    width1 = 0;
                // otherwise, the length of the string gets subtracted from the whitespace.
                else
                    width1 -= id.length();

                // now the left pointer is initialized. Since right marks the comma separator, right + 2 would
                // put the pointer at the first character of the person's first name.
                left = right + 2;

                // this whole process repeats for each person in the file.
                for (right = left; right < person.length() && person.charAt(right) != ','; right++);
                firstName = person.substring(left, right);

                if (firstName.length() > width2)
                    width2 = 0;
                else
                    width2 -= firstName.length();

                left = right + 2;
                for (right = left; right < person.length() && person.charAt(right) != ','; right++);
                lastName = person.substring(left, right);

                if (lastName.length() > width3)
                    width3 = 0;
                else
                    width3 -= lastName.length();

                left = right + 2;
                for (right = left; right < person.length() && person.charAt(right) != ','; right++);
                title = person.substring(left, right);

                if (title.length() > width4)
                    width4 = 0;
                else
                    width4 -= title.length();

                yearOfBirth = person.substring(right + 2);

                if (yearOfBirth.length() > width5)
                    width5 = 0;
                else
                    width5 -= yearOfBirth.length();

                // before the loop continues to the next person in the file, it prints out the full row to console
                System.out.printf("%n" + id + "%" + width1 + "s | " + firstName + "%" + width2 + "s | " + lastName + "%" + width3 + "s | " + title + "%" + width4 + "s | " + yearOfBirth, " ", " ", " ", " ");
            }
            System.out.println();
        }
    }


}
