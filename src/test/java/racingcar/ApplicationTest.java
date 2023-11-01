package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void valid한_이름인지_확인() {
        new Car("torch", 0);
    }

    @Test
    void valid한_이름인지_확인_예외() {
        assertThatThrownBy(() -> new Car("hyunwoo", 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 자동차_전진() {
        String name = "torch";
        Car car = new Car(name, 0);

        assertRandomNumberInRangeTest(
                () -> {
                    car.move();
                    assertThat(car.path()).isEqualTo(name + " : -");
                    car.move();
                    assertThat(car.path()).isEqualTo(name + " : -");
                    car.move();
                    assertThat(car.path()).isEqualTo(name + " : --");
                    car.move();
                    assertThat(car.path()).isEqualTo(name + " : ---");
                },
                MOVING_FORWARD, STOP, MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Test
    void 전진_정지() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni,jun", "5");
                    assertThat(output()).contains("pobi : -", "woni : ", "jun : -",
                            "pobi : -", "woni : -", "jun : --",
                            "pobi : --", "woni : --", "jun : --",
                            "pobi : --", "woni : ---", "jun : ---",
                            "pobi : ---", "woni : ----", "jun : ----",
                            "최종 우승자 : woni, jun");
                },
                MOVING_FORWARD, STOP, MOVING_FORWARD,
                STOP, MOVING_FORWARD, MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD, STOP,
                STOP, MOVING_FORWARD, MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Test
    void 이름에_대한_예외_처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
