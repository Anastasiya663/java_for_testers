import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    /*@AfterEach // либо можно сделать проверки beforeAll и afterAll
    public void tearDown() {
        //driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }*/

    @Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup("group name", "group header", "group footer");
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup("", "", "");
    }
}
