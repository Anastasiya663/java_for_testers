import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    /*@AfterEach // либо можно сделать проверки beforeAll и afterAll
    public void tearDown() {
        //driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }*/

    @Test
    public void canCreateGroup() {
        app.openGroupsPage();
        app.createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.openGroupsPage();
        app.createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.openGroupsPage();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        app.createGroup(groupWithName);
    }
}
