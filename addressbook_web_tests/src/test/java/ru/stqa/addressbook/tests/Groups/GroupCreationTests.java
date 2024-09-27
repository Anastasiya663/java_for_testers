package ru.stqa.addressbook.tests.Groups;

import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.tests.TestBase;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {

        app.groups().createGroup(new GroupData().withName("some name"));
    }
}
