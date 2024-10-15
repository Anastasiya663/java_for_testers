package ru.stqa.addressbook.tests.Groups;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.tests.TestBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>(List.of());
//        for (var name : (List.of("", "group name"))) {
//            for (var header : List.of("", "group header")) {
//                for (var footer : List.of("", "group footer")) {
//                   result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
//               }
//           }
//        }
        /*var json = "";
        try (var reader = new FileReader("groups.json");
            var breader = new BufferedReader(reader)) {
                var line = breader.readLine(); // построчно (полезно,если файл большой и из него нужно взять какие-то конкретные кусочки,а не всё)
                while (line != null) {
                    json = json + line;
                    line = breader.readLine();
                }
        }*/
        var json = Files.readString(Paths.get("groups.json")); //читает файл целиком
        var mapper = new ObjectMapper(); // или YAMLMapper(); или XmlMapper(); соответственно
        var value = mapper.readValue(json,  new TypeReference<List<GroupData>> () {});
        result.addAll(value);
        return result;
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<>(List.of(
                new GroupData("", "group name'", "", "")));
        return result;
    }

    /*@ParameterizedTest
    @ValueSource (strings = {"group name", "group name'"})
    public void canCreateGroup(String name) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData(name, "group header", "group footer"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }*/

    /*@Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }*/

    @ParameterizedTest
    @MethodSource ("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @ParameterizedTest
    @MethodSource ("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups, oldGroups);
    }
}
