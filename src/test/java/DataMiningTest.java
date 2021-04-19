import Java3.Lesson6.DataMining;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class DataMiningTest {
    private DataMining dataMining;
    private final static Random random = new Random();
    private final static int max = 100;
    private final static int min = -100;
    private final static int arrLength = 100;
    private final static int testCases = 4;

    @BeforeEach
    public void init() {
        dataMining = new DataMining();
    }

    @ParameterizedTest
    @MethodSource("dataForExtractNumbersAfterFour")
    public void testExtractNumbersAfterFour(List<Integer> list, List<Integer> result, boolean bool) {
        if (bool) {
            Assertions.assertEquals(result, dataMining.extractNumbersAfterFour(list));
        } else {
            Assertions.assertThrows(RuntimeException.class, () -> {
                throw new RuntimeException();
            });

        }
    }

    public static Stream<Arguments> dataForExtractNumbersAfterFour() {
        List<Arguments> listOfArguments = new ArrayList<>();
        for (int testCase = 0; testCase < testCases; testCase++) {
            List<Integer> list = new ArrayList<>();
            List<Integer> result = new ArrayList<>();
            boolean isFour = false;
            for (int i = 0; i < arrLength; i++) {
                int randomInt = random.nextInt(max + 1 - min) + min;
                list.add(randomInt);
                if (randomInt == 4) {
                    result = new ArrayList<>();
                    isFour = true;
                } else {
                    result.add(randomInt);
                }
            }
            listOfArguments.add(Arguments.arguments(list, result, isFour));

        }
        return listOfArguments.stream();
    }


    @ParameterizedTest
    @MethodSource("dataForCheckForOneAndFour")
    public void testCheckForOneAndFour(List<Integer> list, boolean result) {
        Assertions.assertEquals(result, dataMining.checkForOneAndFour(list));
    }

    public static Stream<Arguments> dataForCheckForOneAndFour() {
        List<Arguments> listOfArguments = new ArrayList<>();
        for (int testCase = 0; testCase < testCases; testCase++) {
            List<Integer> list = new ArrayList<>();
            boolean four = false;
            boolean one = false;
            for (int i = 1; i < arrLength; i++) {
                int randomNumber = random.nextInt(max + 1 - min) + min;
                if (randomNumber == 4) {
                    four = true;
                } else if (randomNumber == 1) {
                    one = true;
                }
                list.add(randomNumber);
            }
            listOfArguments.add(Arguments.arguments(list, four && one));
        }
        return listOfArguments.stream();
    }
}
