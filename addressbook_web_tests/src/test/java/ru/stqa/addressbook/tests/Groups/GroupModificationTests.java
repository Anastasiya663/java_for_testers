package ru.stqa.addressbook.tests.Groups;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup () {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testdata = new GroupData().withName("modified name");
        app.groups().modifyGroup(oldGroups.get(index), testdata);
        var newGroups = app.hbm().getGroupList();

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testdata.withId(oldGroups.get(index).id()));
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

}
