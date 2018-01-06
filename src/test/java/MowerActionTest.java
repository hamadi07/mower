import actions.MowerAction;
import mower.MowerOrientationEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static mower.MowerOrientationEnum.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MowerActionTest {

    @InjectMocks
    private MowerAction mowerAction;

    @Test
    public void turnLeft_shouldReturnOrientation() {
        //when
        MowerOrientationEnum returnedMowerOrientation = mowerAction.turnLeft(NORTH);

        //then
        assertThat(returnedMowerOrientation).isEqualTo(WEST);
    }

    @Test
    public void turnLeft_shouldReturnNull_whenOrientationIsNull() {
        //when
        MowerOrientationEnum returnedMowerOrientation = mowerAction.turnLeft(null);

        //then
        assertThat(returnedMowerOrientation).isNull();
    }

    @Test
    public void turnLeft_shouldReturnNorth_whenStartWithNorthAndTurnLeftFourTimes() {
        //when
        MowerOrientationEnum returnedMowerOrientation1 = mowerAction.turnLeft(NORTH);
        MowerOrientationEnum returnedMowerOrientation2 = mowerAction.turnLeft(returnedMowerOrientation1);
        MowerOrientationEnum returnedMowerOrientation3 = mowerAction.turnLeft(returnedMowerOrientation2);
        MowerOrientationEnum returnedMowerOrientation4 = mowerAction.turnLeft(returnedMowerOrientation3);

        //then
        assertThat(returnedMowerOrientation4).isEqualTo(NORTH);
    }

    @Test
    public void turnRight_shouldReturnOrientation() {
        //when
        MowerOrientationEnum returnedMowerOrientation = mowerAction.turnRight(NORTH);

        //then
        assertThat(returnedMowerOrientation).isEqualTo(EAST);
    }

    @Test
    public void turnRight_shouldReturnEast_WhenStartWithEastAndTurnRightFourTimes() {
        //when
        MowerOrientationEnum returnedMowerOrientation1 = mowerAction.turnRight(EAST);
        MowerOrientationEnum returnedMowerOrientation2 = mowerAction.turnRight(returnedMowerOrientation1);
        MowerOrientationEnum returnedMowerOrientation3 = mowerAction.turnRight(returnedMowerOrientation2);
        MowerOrientationEnum returnedMowerOrientation4 = mowerAction.turnRight(returnedMowerOrientation3);

        //then
        assertThat(returnedMowerOrientation4).isEqualTo(EAST);
    }

    @Test
    public void turnRight_shouldReturnNull_whenOrientationIsNull() {
        //when
        MowerOrientationEnum returnedMowerOrientation = mowerAction.turnRight(null);

        //then
        assertThat(returnedMowerOrientation).isNull();
    }
}
