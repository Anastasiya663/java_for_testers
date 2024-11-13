package ru.stqa.addressbook.tests.Groups;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            }
        });
        var oldGroups = app.hbm().getGroupList(); // создаем список имеющихся групп
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size()); // выбираем случайный индекс от 0 до размера списка всех групп
        app.groups().removeGroup(oldGroups.get(index)); // удаляем именно выбранную группу
        var newGroups = app.hbm().getGroupList(); // создаем список оставшихся групп

        var expectedList = new ArrayList<>(oldGroups); // копия получившегося после удаления группы списка
        expectedList.remove(index);
        Allure.step("Validating results", step -> {
                    Assertions.assertEquals(newGroups, expectedList);
        });
    }

    @Test
    void canRemoveAllGroupsAtOnes () {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.hbm().getGroupCount());
    }

}
