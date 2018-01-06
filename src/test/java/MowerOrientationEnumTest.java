
import coordinates.Coordinates;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static mower.MowerOrientationEnum.getNewCoordinatesToTurnLeft;
import static mower.MowerOrientationEnum.getNewCoordinatesToTurnRight;
import static org.assertj.core.api.Assertions.assertThat;

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
}