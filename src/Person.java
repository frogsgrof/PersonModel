import java.util.Calendar;

public class Person {

    /* fields */

    private String ID, firstName, lastName, title;
    private int yearOfBirth;
    static private int IDSeed = 1;

    /* constructors */

    // uses every field
    public Person(String id, String firstName, String lastName, String title, int yearOfBirth) {
        this.ID = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.yearOfBirth = yearOfBirth;
    }

    // uses every field except the ID
    public Person(String firstName, String lastName, String title, int yearOfBirth) {
        ID = Person.genIDNum();
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.yearOfBirth = yearOfBirth;
    }

    // empty constructor
    public Person() {
        ID = "";
        firstName = "";
        lastName = "";
        title = "";
        yearOfBirth = -1;
    }

    /* getters */

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public static int getIDSeed() {
        return IDSeed;
    }

    // returns the year of birth as a String instead of an int
    public String getYearOfBirthAsString() {
        return String.valueOf(yearOfBirth);
    }

    /* setters */

    public void setID(String id) {
        this.ID = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public static void setIDSeed(int IDSeed) {
        Person.IDSeed = IDSeed;
    }

    /* misc. methods */

    private static String genIDNum() {
        String id = "" + IDSeed;

        while (id.length() < 6)
            id = ' ' + id;

        IDSeed++;
        return id;
    }

    public String fullName() {
        return firstName + ' ' + lastName;
    }

    public String formalName() {
        return title + " " + fullName();
    }

    public String getAge() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - yearOfBirth);
    }

    public String toCSVDataRecord() {
        return ID + ", " + firstName + ", " + lastName + ", " + title + ", " + yearOfBirth;
    }

    /**
     *
     * @return The object's data as a format String representing a table row.
     */
    public String getTableRow() {

        // first calculates column widths
        int width1 = Math.max(ID.length(), 8);
        int width2 = Math.max(firstName.length(), 16);
        int width3 = Math.max(lastName.length(), 16);
        int width4 = Math.max(title.length(), 5);
        int width5 = Math.max(getYearOfBirthAsString().length(), 4);

        // uses the String.format() method to create the table row and returns it
        return String.format("%" + width1 + "s   %" + width2 + "s   %" + width3 + "s   %" + width4 +
                "s   %" + width5 + "d", ID, firstName, lastName, title, yearOfBirth);
    }
}