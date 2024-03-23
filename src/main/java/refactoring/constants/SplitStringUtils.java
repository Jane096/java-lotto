package refactoring.constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SplitStringUtils {

    public static List<Integer> split(String value){
        return Arrays.stream(value.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
