import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person p1, p2, p3, p4, p5, p6;

    @BeforeEach
    void setUp() {
        p1 = new Person("000001", "Bob", "Jones", "Mr.", 1963);
        p2 = new Person("000002", "Susan", "Jones", "Dr.", 1960);
        p3 = new Person("000003", "Tara", "Delosreyes", "Ms.", 1958);
        p4 = new Person("000004", "Gladys", "McDonough", "Mrs.", 1999);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void Person() {
        p5 = new Person();
        assertNotNull(p5);
    }

    @Test
    void setID() {
        p1.setID("111111");
        assertEquals("111111", p1.getID());
    }

    @Test
    void setFirstName() {
        p1.setFirstName("Robert");
        assertEquals("Robert", p1.getFirstName());
    }

    @Test
    void setLastName() {
        p1.setLastName("Sponge");
        assertEquals("Sponge", p1.getLastName());
    }

    @Test
    void setTitle() {
        p1.setTitle("Dr.");
        assertEquals("Dr.", p1.getTitle());
    }

    @Test
    void setYearOfBirth() {
        p1.setYearOfBirth(1900);
        assertEquals(1900, p1.getYearOfBirth());
    }

    @Test
    static void setIDSeed(int IDSeed) {
        Person.setIDSeed(12);
        assertEquals(12, Person.getIDSeed());
    }

    @Test
    void genIDNum() {
        p5 = new Person("Lisa", "Smith", "Ms.", 1980);
        assertEquals("     1", p5.getID());
        assertEquals(2, Person.getIDSeed());
    }

    @Test
    void fullName() {
        assertEquals("Bob Jones", p1.fullName());
    }

    @Test
    void formalName() {
        assertEquals("Dr. Susan Jones", p2.formalName());
    }

    @Test
    void getAge() {
        assertEquals("24", p4.getAge());
    }

    @Test
    void toCSVDataRecord() {
        String CSVTest = "000001, Bob, Jones, Mr., 1963";
        assertEquals(CSVTest, p1.toCSVDataRecord());
    }

    @Test
    void getIDSeed() {
        assertEquals(1, Person.getIDSeed());
    }

    @Test
    void getTableRow() {
        String tableRowTest = "  000003               Tara         Delosreyes     Ms.   1958";
        assertEquals(tableRowTest, p3.getTableRow());
    }
}