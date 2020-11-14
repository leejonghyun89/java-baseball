package baseball.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by JongHyun Lee on 2020-11-11
 */
class BaseBallTest {

  private BaseBall baseBall;

  @BeforeEach
  void setUp() {
    baseBall = new BaseBall();
  }

  @DisplayName("숫자 랜덤 크기 체크")
  @ParameterizedTest
  @ValueSource(ints = { 3 })
  void random_base_ball_size(int maxSize) {
    assertEquals(baseBall.getBaseBalls().size(), maxSize);
  }

  @DisplayName("현재 등록 된 랜덤 숫자 최소값 0인 경우")
  @ParameterizedTest(name = "{index} => oneNumber={0}, twoNumber={1}, threeNumber={2}")
  @CsvSource({
    "0, 5, 9",
    "5, 8, 0"
  })
  void random_base_ball_min_number(int oneNumber, int twoNumber, int threeNumber) {
    IllegalArgumentException exception = assertThrows(
      IllegalArgumentException.class,
        () -> baseBall.validateRandomNumberMax(new HashSet<>(Arrays.asList(oneNumber, twoNumber, threeNumber)))
    );

    assertTrue(exception.getMessage().contains("랜덤으로 나온 숫자가 0이거나 10보다 큽니다."));
  }

  @DisplayName("현재 등록 된 랜덤 숫자 최대값이 10인 경우")
  @ParameterizedTest(name = "{index} => oneNumber={0}, twoNumber={1}, threeNumber={2}")
  @CsvSource({
      "10, 5, 9",
      "5, 8, 10"
  })
  void random_base_ball_max_number(int oneNumber, int twoNumber, int threeNumber) {
    IllegalArgumentException exception = assertThrows(
      IllegalArgumentException.class,
        () -> baseBall.validateRandomNumberMax(new HashSet<>(Arrays.asList(oneNumber, twoNumber, threeNumber)))
    );

    assertTrue(exception.getMessage().contains("랜덤으로 나온 숫자가 0이거나 10보다 큽니다."));
  }

  @DisplayName("현재 등록 된 랜덤 숫자의 길이가 3이 아닌 경우 (HashSet)의 중복 여부 체크")
  @ParameterizedTest(name = "{index} => oneNumber={0}, twoNumber={1}, threeNumber={2}")
  @CsvSource({
      "5, 5, 9",
      "5, 8, 8"
  })
  void validate_random_base_ball_size(int oneNumber, int twoNumber, int threeNumber) {
    IllegalArgumentException exception = assertThrows(
      IllegalArgumentException.class,
        () -> baseBall.validateRandomBaseBallSize(new HashSet<>(Arrays.asList(oneNumber, twoNumber, threeNumber)))
    );

    assertTrue(exception.getMessage().contains("랜덤으로 추출 된 크기가 3이 아닙니다."));
  }

}