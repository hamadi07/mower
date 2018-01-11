
import com.component.coordinates.Coordinates;
import com.component.mower.MowerOrientationEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.component.mower.MowerOrientationEnum.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class MowerOrientationEnumTest {
    private int x;
    private int y;

    @Before
    public void setUp() {
        x = 1;
        y = 0;
    }

    @Test
    public void getNewCoordinatesToTurnLeft_shouldReturnCoodinates() {
        //when
        Coordinates newCoordinatesToTurnLeft = getNewCoordinatesToTurnLeft(x, y);

        //then
        assertThat(newCoordinatesToTurnLeft.getX()).isEqualTo(0);
        assertThat(newCoordinatesToTurnLeft.getY()).isEqualTo(1);
    }

    @Test
    public void getNewCoordinatesToTurnRight_shouldReturnCoodinates() {
        //when
        Coordinates newCoordinatesToTurnLeft = getNewCoordinatesToTurnRight(x, y);

        //then
        assertThat(newCoordinatesToTurnLeft.getX()).isEqualTo(0);
        assertThat(newCoordinatesToTurnLeft.getY()).isEqualTo(-1);

    }

    @Test
    public void getOrientationByCode_shouldReturnMowerOrientationEnum_whenCodeIsValid() {
        //when
        MowerOrientationEnum mowerOrientationEnum = getOrientationByCode("N");

        //then
        assertThat(mowerOrientationEnum).isEqualTo(NORTH);
    }

    @Test
    public void getOrientationByCode_shouldThrowExecption_whenCodeIsValid() {
        //when
        Throwable throwable = catchThrowable(() -> getOrientationByCode("X"));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }
}