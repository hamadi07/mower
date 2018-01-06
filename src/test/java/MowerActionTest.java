import actions.MowerAction;
import coordinates.Coordinates;
import mower.Mower;
import mower.MowerOrientationEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import util.ConsoleOverride;

import static mower.MowerOrientationEnum.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MowerActionTest {

    @Mock
    private ConsoleOverride console;

    private Coordinates startPosition;

    @Before
    public void setUp() {
        startPosition = new Coordinates(1, 2);
    }

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
    public void turnLeft_shouldReturnNull_whenOrientationIsNullAndPrintException() {
        //when
        MowerOrientationEnum returnedMowerOrientation = mowerAction.turnLeft(null);

        //then
        assertThat(returnedMowerOrientation).isNull();
        verify(console).print("Invalid Orientation");
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
        verify(console).print("Invalid Orientation");
    }

    @Test
    public void movingForward_shouldMovingMowerToNewPosition_whenDirectionIsNorth() {
        //given
        Mower mower = new Mower(NORTH, startPosition);

        //when
        mowerAction.movingForward(mower);

        //then
        assertThat(mower.getPosition().getX()).isEqualTo(1);
        assertThat(mower.getPosition().getY()).isEqualTo(3);
    }

    @Test
    public void movingForward_shouldMovingMowerToNewPosition_whenDirectionIsEast() {
        //given
        Mower mower = new Mower(EAST, startPosition);

        //when
        mowerAction.movingForward(mower);

        //then
        assertThat(mower.getPosition().getX()).isEqualTo(2);
        assertThat(mower.getPosition().getY()).isEqualTo(2);
    }

    @Test
    public void movingForward_shouldMovingMowerToNewPosition_whenDirectionIsWest() {
        //given
        Mower mower = new Mower(WEST, startPosition);

        //when
        mowerAction.movingForward(mower);

        //then
        assertThat(mower.getPosition().getX()).isEqualTo(0);
        assertThat(mower.getPosition().getY()).isEqualTo(2);
    }

    @Test
    public void movingForward_shouldMovingMowerToNewPosition_whenDirectionIsSud() {
        //given
        Mower mower = new Mower(SOUTH, startPosition);

        //when
        mowerAction.movingForward(mower);

        //then
        assertThat(mower.getPosition().getX()).isEqualTo(1);
        assertThat(mower.getPosition().getY()).isEqualTo(1);
    }

    @Test
    public void movingForward_shouldPrintException_whenMowerIsNull() {
        //when
        mowerAction.movingForward(null);

        //then
        verify(console).print("Invalid Mower");
    }
}
