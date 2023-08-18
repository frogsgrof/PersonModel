import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {

    public static void main(String[] args) {

        // for storing all people
        ArrayList<String> people = new ArrayList<>();

        // input scanner
        Scanner scanner = new Scanner(System.in);

        // for storing everyone's data
        String id,
                firstName,
                lastName,
                title;
        int yearOfBirth;

        // used to control the main input loop
        boolean createNewPerson = true;

        // main input loop
        do {

            // for holding the person currently being entered
            String personData;

            // gets id
            id = SafeInput.getRegExString(scanner, "Enter id", "[0-9]");

            // gets first name
            firstName = SafeInput.getRegExString(scanner, "Enter first name", "[A-Za-z]");

            // gets last name
            lastName = SafeInput.getRegExString(scanner, "Enter last name", "[A-Za-z]");

            // gets title
            title = SafeInput.getRegExString(scanner, "Enter title (e.g. Mr., Mrs., etc.)", "([A-Za-z])+.?");

            // gets year of birth
            yearOfBirth = SafeInput.getRangedInt(scanner, "Enter year of birth", 1, 2023);

            // asks for confirmation
            boolean edit = SafeInput.getYNConfirm(scanner, "Would you like to edit? (y/n)");

            // if they chose to edit, loops until they confirm
            while (edit)
            {
                // shows the edit options
                System.out.println("\n1. ID: " + id + "\n" +
                        "2. First name: " + firstName + "\n" +
                        "3. Last name: " + lastName + "\n" +
                        "4. Title: " + title + "\n" +
                        "5. Year of birth: " + yearOfBirth);

                // gets their choice
                int toEdit = SafeInput.getRangedInt(scanner, "Enter the item you'd like to change (1-5) or 6 to cancel", 1, 6);

                // makes the change specified
                switch (toEdit)
                {
                    case 1 -> {
                        id = SafeInput.getRegExString(scanner, "Enter id", "[0-9]");
                    }
                    case 2 -> {
                        firstName = SafeInput.getRegExString(scanner, "Enter first name", "[A-Za-z]+");
                    }
                    case 3 -> {
                        lastName = SafeInput.getRegExString(scanner, "Enter last name", "[a-zA-Z]+");
                    }
                    case 4 -> {
                        title = SafeInput.getRegExString(scanner, "Enter title (e.g. Mr., Mrs., etc.)", "([A-Za-z])+.?");
                    }
                    case 5 -> {
                        yearOfBirth = SafeInput.getRangedInt(scanner, "Enter year of birth", 1, 2023);
                    }
                    case 6 -> {
                        // if they chose to cancel, sets edit to false to break the edit loop
                        edit = false;
                    }
                }

                if (!edit) break; // checks if edit was set to false by the user cancelling so that it doesn't ask again

                // if they don't cancel, it continues asking for confirmation to move on to the next person
                edit = SafeInput.getYNConfirm(scanner, "Continue editing? (y/n)");
            }

            // concatenates all the data into a string and enters it into the list
            personData = id + ", " + firstName + ", " + lastName + ", " + title + ", " + yearOfBirth;
            people.add(personData);

            // asks if they want to add more people, thus continuing main loop
            createNewPerson = SafeInput.getYNConfirm(scanner, "Add another person? (y/n)");

        } while (createNewPerson);

        // saves the people into a file
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "\\src\\" + "PersonTestData.txt");
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(path, CREATE));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));

            // writes each string in the list to the file, followed by a line break
            for (String person : people) {
                bufferedWriter.write(person, 0, person.length());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
