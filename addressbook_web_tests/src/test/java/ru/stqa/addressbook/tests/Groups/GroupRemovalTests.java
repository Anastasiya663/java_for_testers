package ru.stqa.addressbook.tests.Groups;

import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.tests.TestBase;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().removeGroup();
    }

}