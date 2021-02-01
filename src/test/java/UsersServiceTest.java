
import com.gmail.psyh2409.CustomFieldException;
import com.gmail.psyh2409.Users;
import com.gmail.psyh2409.UsersService;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UsersServiceTest {

    private UsersService usersService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup....");
        System.out.println("Code executes only once");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
        Users user1 = new Users("Serhii", LocalDate.of(2003, 11, 27));
        Users user2 = new Users("Katia", LocalDate.of(2006, 9, 5));
        Users user3 = new Users("Kirill", LocalDate.of(2011, 10, 2));
        List<Users> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        usersService = new UsersService(users);
    }

    @Test
    public void whenCreateNewUserThenReturnListWithNewUser() throws CustomFieldException {
        assertThat(usersService.getUsers().size(), is(3));
        usersService.createNewUser("NewUser", LocalDate.of(1990, 2, 1));
        assertThat(usersService.getUsers().size(), is(4));
    }

    @Test
    public void whenRemoveUserThenRemoveUserByName() {
        usersService.removeUser("Kirill");
        List<Users> users = usersService.getUsers();
        assertThat(users.size(), is(2));
    }

    @Test
    public void whenCreateNewUserWithoutNameThenThrowCustomFieldException() throws CustomFieldException {
        thrown.expect(CustomFieldException.class);
        thrown.expectMessage("Name could not be empty or null");
        usersService.createNewUser(null, LocalDate.of(1990, 2, 1));
    }

    @Test
    public void whenCreateNewUserWithoutDateOfBirthThenThrowCustomFieldException() throws CustomFieldException {
        thrown.expect(CustomFieldException.class);
        thrown.expectMessage("Date of birth could not be null");
        usersService.createNewUser("Unbirthed", null);
    }

    @Test
    public void whenIsBirthDayWhenNotBirthdayThenReturnTrue() throws CustomFieldException {
        boolean isBirthday = usersService.isBirthDay(
                usersService.getUsers().get(0), LocalDate.of(1990,2,1));
        assertFalse(isBirthday);
    }

    @Test
    public void whenIsBirthDayWhenNotBirthdayThenReturnFalse() throws CustomFieldException {
        boolean isBirthday = usersService.isBirthDay(
                usersService.getUsers().get(0), LocalDate.of(2003,11,27));
        assertTrue(isBirthday);
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Tests finished");
    }

    @After
    public void afterMethod() {
        System.out.println("Code executes after each test method");
    }
}
