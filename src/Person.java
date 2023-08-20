import java.util.Calendar;

public class Person {

    String id, firstName, lastName, title;
    int yearOfBirth;

    public String fullName() {
        return firstName + ' ' + lastName;
    }

    public String formalName() {
        return title + fullName();
    }

    public String getAge() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - yearOfBirth);
    }

    public String toCSVDataRecord() {
        return id + ", " + firstName + ", " + lastName + ", " + title + ", " + yearOfBirth;
    }
}