package ru.stqa.addressbook.tests.Groups;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.tests.TestBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

    public static Stream<GroupData> RandomGroups() throws IOException {
        Supplier<GroupData> randomGroup = () -> new GroupData()
                    .withName(CommonFunctions.randomString(5))
                    .withHeader(CommonFunctions.randomString(10))
                    .withFooter(CommonFunctions.randomString(15));
        return Stream.generate(randomGroup).limit(3);

//        return List.of(new GroupData()
//                .withName(CommonFunctions.randomString(5))
//                .withHeader(CommonFunctions.randomString(10))
//                .withFooter(CommonFunctions.randomString(15)));
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
    @MethodSource ("RandomGroups")
    public void canCreateGroup(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
//        Comparator<GroupData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//        newGroups.sort(compareById);
//        var maxId = newGroups.get(newGroups.size() - 1).id();
        var extraGroups = newGroups.stream().filter(g -> !oldGroups.contains(g)).toList(); //список групп, которых не было в старом списке
        var newId = extraGroups.get(0).id(); //идентификатор группы, который отсутствовал в старом/первоначальном списке

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));
//        expectedList.sort(compareById);
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }

    @ParameterizedTest
    @MethodSource ("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
        Assertions.assertEquals(newGroups, oldGroups);
    }
}
