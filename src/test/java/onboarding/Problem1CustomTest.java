package onboarding;

import onboarding.problem1Validation.Problem1Validation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/*
*
* - pobi와 crong의 input이 길이가 2인지 판단한다.
* - pobi와 crong의 input의 원소들이 페이지의 최소값인 1과 최대값인 400사이인지 확인한다.
* - pobi와 crong의 input이 각각 연속된 숫자 리스트인지 확인한다. ex) [97, 98] -> (O), [98,100] -> (X)
* - pobi와 crong의 input이 각각 [홀수, 짝수] 쌍인지 확인한다.
* - pobi와 crong의 input이 [홀수, 짝수]인 쌍에서 짝수값이 홀수 값보다 1 큰지 확인한다.
 */
public class Problem1CustomTest {
    private final static int USER_INPUT_LENGTH = 2;
    private final static int BOOK_MIN = 1;
    private final static int BOOK_MAX = 400;
    @Nested
    class Problem1Test {
        @Test
        void 길이가2보다큰경우테스트() {
            List<Integer> list = List.of(97, 98, 100);
            assertThat(
                    Problem1Validation.userInputSizeEquals(list, USER_INPUT_LENGTH))
                    .isFalse();
        }
        @Test
        void 길이가2보다작은경우테스트() {
            List<Integer> list = List.of(97);
            assertThat(
                    Problem1Validation.userInputSizeEquals(list, USER_INPUT_LENGTH))
                    .isFalse();
        }
        @Test
        void 길이가2인경우테스트() {
            List<Integer> list = List.of(97, 98);
            assertThat(
                    Problem1Validation.userInputSizeEquals(list, USER_INPUT_LENGTH))
                    .isTrue();
        }
        @Test
        void 책페이지가1미만이포함되있다면() {
            List<Integer> list = List.of(0, 3);
            assertThat(
                    Problem1Validation.isBookNumberBetweenMinAndMax(list, BOOK_MIN, BOOK_MAX))
                    .isFalse();

        }
        @Test
        void 책페이지가400초과인값이포함되어있다면() {
            List<Integer> list = List.of(401, 398);
            assertThat(
                    Problem1Validation.isBookNumberBetweenMinAndMax(list, BOOK_MIN, BOOK_MAX))
                    .isFalse();
        }
        @Test
        void 책페이지가1과400사이인유효한값인지() {
            List<Integer> list = List.of(97, 98);
            assertThat(
                    Problem1Validation.isBookNumberBetweenMinAndMax(list, BOOK_MIN, BOOK_MAX))
                    .isTrue();
        }
        @Test
        void 리스트속원소가연속된값이아니면() {
            List<Integer> list = List.of(39,41);
            assertThat(
                    Problem1Validation.isBookNumberContinuous(list))
                    .isFalse();
        }
        @Test
        void 리스트속원소가연속된값이면() {
            List<Integer> list = List.of(39, 40);
            assertThat(
                    Problem1Validation.isBookNumberContinuous(list))
                    .isTrue();
        }
        @Test
        void 리스트의쌍이홀수홀수쌍인경우() {
            List<Integer> list = List.of(39, 41);
            assertThat(
                    Problem1Validation.isBookNumberOddEvenPair(list))
                    .isFalse();
        }
        @Test
        void 리스트의쌍이짝수짝수쌍인경우() {
            List<Integer> list = List.of(38, 40);
            assertThat(
                    Problem1Validation.isBookNumberOddEvenPair(list))
                    .isFalse();
        }
        @Test
        void 리스트의쌍이짝수홀수쌍인경우() {
            List<Integer> list = List.of(38, 39);
            assertThat(
                    Problem1Validation.isBookNumberOddEvenPair(list))
                    .isFalse();
        }
        @Test
        void 리스트의쌍이홀수짝수쌍인경우() {
            List<Integer> list = List.of(39, 40);
            assertThat(
                    Problem1Validation.isBookNumberOddEvenPair(list))
                    .isTrue();
        }
        @Test
        void 첫번째값에1더한값이두번째값이안되는경우() {
            List<Integer> list = List.of(39, 42);
            assertThat(
                    Problem1Validation.isFirstAddOneEqualsSecondValue(list))
                    .isFalse();
        }
        @Test
        void 첫번째값에1더한값이두번째값이되는경우() {
            List<Integer> list = List.of(39, 40);
            assertThat(
                    Problem1Validation.isFirstAddOneEqualsSecondValue(list))
                    .isTrue();
        }

        @Test
        void validation메써드들이lazyChecking하는지확인하자() {
            List<Integer> list = List.of(39);
            assertThat(Problem1Validation.userInputSizeEquals(list, USER_INPUT_LENGTH) && Problem1Validation.isBookNumberContinuous(list))
                    .isFalse();

        }

        @Test
        void validation메써드들을합쳐서확인해보자() {
            List<List<Integer>> testList = new ArrayList<>();
            testList.add(List.of(2, 3));
            testList.add(List.of(0, 3));
            testList.add(List.of(0, 1));
            testList.add(List.of(400, 401));
            testList.add(List.of(399, 398));
            testList.add(List.of(5, 7));
            testList.add(List.of(0));
            testList.add(List.of(0,1,2));
            testList.add(List.of(1));
            testList.add(List.of(1,2,3));
            long count = testList.stream()
                    .filter(list -> Problem1Validation.problem1InputValidation(list, USER_INPUT_LENGTH, BOOK_MIN, BOOK_MAX))
                    .count();
            assertThat(count).isEqualTo(0L);
        }
        void validation메써드들을통과하는가() {
            List<List<Integer>> testList = new ArrayList<>();
            testList.add(List.of(1, 2));
            testList.add(List.of(0, 3));
            testList.add(List.of(0, 1));
            testList.add(List.of(399, 400));
            testList.add(List.of(399, 398));
            testList.add(List.of(5, 7));
            testList.add(List.of(0));
            testList.add(List.of(0,1,2));
            testList.add(List.of(1));
            testList.add(List.of(1,2,3));
            long count = testList.stream()
                    .filter(list -> Problem1Validation.problem1InputValidation(list, USER_INPUT_LENGTH, BOOK_MIN, BOOK_MAX))
                    .count();
            assertThat(count).isEqualTo(2L);
        }
    }
}
