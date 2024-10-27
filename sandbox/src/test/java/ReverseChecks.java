import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ReverseChecks {
//обратная проверка - совершить с полученым результатом обратное действие и уже его сравнить
    @Test
    void testSqrt () {
        var input = 5.0;
        var result = Math.sqrt(input);
        var reverse = result * result;
        Assertions.assertEquals(input, reverse, 0000.1);
    }

    @Test
    void testSort() {
        var input = new ArrayList<>(List.of(3, 7, 4, 9, 0));
        input.sort(Integer::compareTo);
        for(int i = 0; i < input.size() - 1; i++) { //проверили, что список действительно отсортирован
            Assertions.assertTrue(input.get(i) <= input.get(i + 1));
        }
    }
}
