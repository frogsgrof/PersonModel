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
        ArrayList<Person> people = new ArrayList<>();

        // input scanner
        Scanner scanner = new Scanner(System.in);

        // used to control the main input loop
        boolean createNewPerson = true;

        // main input loop
        do {

            // for holding the person currently being entered
            Person person = new Person();

            // gets id
            person.setID(SafeInput.getRegExString(scanner, "Enter id", "[0-9]+"));

            // gets first name
            person.setFirstName(SafeInput.getRegExString(scanner, "Enter first name", "[A-Za-z]+"));

            // gets last name
            person.setLastName(SafeInput.getRegExString(scanner, "Enter last name", "[A-Za-z]+"));

            // gets title
            person.setTitle(SafeInput.getRegExString(scanner, "Enter title (e.g. Mr., Mrs., etc.)", "([A-Za-z])+.?"));

            // gets year of birth
            person.setYearOfBirth(SafeInput.getRangedInt(scanner, "Enter year of birth", 1, 2023));

            // asks for confirmation
            boolean edit = SafeInput.getYNConfirm(scanner, "Would you like to edit? (y/n)");

            // if they chose to edit, loops until they confirm
            while (edit)
            {
                // shows the edit options
                System.out.println("\n1. ID: " + person.getID() + "\n" +
                        "2. First name: " + person.getFirstName() + "\n" +
                        "3. Last name: " + person.getLastName() + "\n" +
                        "4. Title: " + person.getTitle() + "\n" +
                        "5. Year of birth: " + person.getYearOfBirth());

                // gets their choice
                int toEdit = SafeInput.getRangedInt(scanner, "Enter the item you'd like to change (1-5) or 6 to cancel", 1, 6);

                // makes the change specified
                switch (toEdit)
                {
                    case 1 -> {
                        person.setID(SafeInput.getRegExString(scanner, "Enter id", "[0-9]+"));
                    }
                    case 2 -> {
                        person.setFirstName(SafeInput.getRegExString(scanner, "Enter first name", "[A-Za-z]+"));
                    }
                    case 3 -> {
                        person.setLastName(SafeInput.getRegExString(scanner, "Enter last name", "[a-zA-Z]+"));
                    }
                    case 4 -> {
                        person.setTitle(SafeInput.getRegExString(scanner, "Enter title (e.g. Mr., Mrs., etc.)", "([A-Za-z])+.?"));
                    }
                    case 5 -> {
                        person.setYearOfBirth(SafeInput.getRangedInt(scanner, "Enter year of birth", 1, 2023));
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

            // adds the completed person into the list
            people.add(person);

            // asks if they want to add more people, thus continuing main loop
            createNewPerson = SafeInput.getYNConfirm(scanner, "Add another person? (y/n)");

        } while (createNewPerson);

        // saves the people into a file
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "\\src\\" + "PersonTestData.txt");
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(path, CREATE));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));

            // writes each string in the list to the file, followed by a line break
            for (Person person : people) {
                bufferedWriter.write(person.toCSVDataRecord(), 0, person.toCSVDataRecord().length());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
