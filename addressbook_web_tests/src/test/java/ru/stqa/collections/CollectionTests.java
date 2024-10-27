package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionTests {

    @Test
    void arrayTests() {
        //var array = new String[] {"a", "b", "c"};
        var array = new String[3];
        Assertions.assertEquals(3, array.length);
        array[0] = "a";
        array[1] = "b";
        array[2] = "c";
        Assertions.assertEquals("a", array[0]);
    }

    @Test
    void listTests() {
        //var list = List.of("a", "b", "c"); //сразу проинициализировать список значениями - такой список неизменяемый, поэтому можно совместить эти два подхода

        var list = new ArrayList<>(List.of("a", "b", "c"));
        Assertions.assertEquals(3, list.size());


        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("a", list.get(0));

        list.set(0, "d");
        Assertions.assertEquals("d", list.get(0));
    }

    @Test
    void  setTests() {
        //var set = Set.of("a", "b", "c");
        var set = new HashSet<>(List.of("a", "b", "c"));// модифицируемое множество - из списка
        Assertions.assertEquals(3, set.size());

        set.add("d");
        Assertions.assertEquals(4, set.size());

        //var element = set.stream().findAny().get(); //получить элемент из множества через преобразование его в поток

    }

    @Test
    void testMap() {
        var digits = new HashMap<Character, String>();
        digits.put('1', "one");
        digits.put('2', "two");
        digits.put('3', "three");
        Assertions.assertEquals("one", digits.get('1'));

        digits.put('1', "один");
        Assertions.assertEquals("один", digits.get('1'));
    }
}
