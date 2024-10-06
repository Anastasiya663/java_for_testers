package ru.stqa.addressbook.tests.Groups;

import org.junit.jupiter.api.Assertions;
import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.tests.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        List<GroupData> oldGroups = app.groups().getList(); // создаем список имеющихся групп
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size()); // выбираем случайный индекс от 0 до размера списка всех групп
        app.groups().removeGroup(oldGroups.get(index)); // удаляем именно выбранную группу
        List<GroupData> newGroups = app.groups().getList(); // создаем список оставшихся групп

        var expectedList = new ArrayList<>(oldGroups); // копия получившегося после удаления группы списка
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    void canRemoveAllGroupsAtOnes () {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }

}
