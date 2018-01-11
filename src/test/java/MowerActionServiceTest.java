import com.actions.MowerActionService;
import com.component.coordinates.Coordinates;
import com.component.grid.Grid;
import com.component.mower.Mower;
import com.component.mower.MowerOrientationEnum;
import com.util.ConsoleOverride;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.component.mower.MowerOrientationEnum.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MowerActionServiceTest {

    @InjectMocks
    private MowerActionService mowerActionService;

    @Mock
    private ConsoleOverride console;

    private Coordinates startPosition;
    private Grid grid;

    @Before
    public void setUp() {
        startPosition = new Coordinates(1, 2);
        grid = new Grid(5, 5);
    }

    @Test
    public void turnLeft_shouldReturnOrientation() {
        //when
        MowerOrientationEnum returnedMowerOrientation = mowerActionService.turnLeft(NORTH);

        //then
        assertThat(returnedMowerOrientation).isEqualTo(WEST);
    }

    @Test
    public void turnLeft_shouldReturnNull_whenOrientationIsNullAndPrintException() {
        //when
        MowerOrientationEnum returnedMowerOrientation = mowerActionService.turnLeft(null);

        //then
        assertThat(returnedMowerOrientation).isNull();
        verify(console).print("Invalid Orientation");
    }

    @Test
    public void turnLeft_shouldReturnNorth_whenStartWithNorthAndTurnLeftFourTimes() {
        //when
        MowerOrientationEnum returnedMowerOrientation1 = mowerActionService.turnLeft(NORTH);
        MowerOrientationEnum returnedMowerOrientation2 = mowerActionService.turnLeft(returnedMowerOrientation1);
        MowerOrientationEnum returnedMowerOrientation3 = mowerActionService.turnLeft(returnedMowerOrientation2);
        MowerOrientationEnum returnedMowerOrientation4 = mowerActionService.turnLeft(returnedMowerOrientation3);

        //then
        assertThat(returnedMowerOrientation4).isEqualTo(NORTH);
    }

    @Test
    public void turnRight_shouldReturnOrientation() {
        //when
        MowerOrientationEnum returnedMowerOrientation = mowerActionService.turnRight(NORTH);

        //then
        assertThat(returnedMowerOrientation).isEqualTo(EAST);
    }

    @Test
    public void turnRight_shouldReturnEast_WhenStartWithEastAndTurnRightFourTimes() {
        //when
        MowerOrientationEnum returnedMowerOrientation1 = mowerActionService.turnRight(EAST);
        MowerOrientationEnum returnedMowerOrientation2 = mowerActionService.turnRight(returnedMowerOrientation1);
        MowerOrientationEnum returnedMowerOrientation3 = mowerActionService.turnRight(returnedMowerOrientation2);
        MowerOrientationEnum returnedMowerOrientation4 = mowerActionService.turnRight(returnedMowerOrientation3);

        //then
        assertThat(returnedMowerOrientation4).isEqualTo(EAST);
    }

    @Test
    public void turnRight_shouldReturnNull_whenOrientationIsNull() {
        //when
        MowerOrientationEnum returnedMowerOrientation = mowerActionService.turnRight(null);

        //then
        assertThat(returnedMowerOrientation).isNull();
        verify(console).print("Invalid Orientation");
    }

    @Test
    public void movingForward_shouldMovingMowerToNewPosition_whenDirectionIsNorth() {
        //given
        Mower mower = new Mower(NORTH, startPosition);

        //when
        mowerActionService.movingForward(mower, grid);

        //then
        assertThat(mower.getPosition().getX()).isEqualTo(1);
        assertThat(mower.getPosition().getY()).isEqualTo(3);
    }

    @Test
    public void movingForward_shouldMovingMowerToNewPosition_whenDirectionIsEast() {
        //given
        Mower mower = new Mower(EAST, startPosition);

        //when
        mowerActionService.movingForward(mower, grid);

        //then
        assertThat(mower.getPosition().getX()).isEqualTo(2);
        assertThat(mower.getPosition().getY()).isEqualTo(2);
    }

    @Test
    public void movingForward_shouldMovingMowerToNewPosition_whenDirectionIsWest() {
        //given
        Mower mower = new Mower(WEST, startPosition);

        //when
        mowerActionService.movingForward(mower, grid);

        //then
        assertThat(mower.getPosition().getX()).isEqualTo(0);
        assertThat(mower.getPosition().getY()).isEqualTo(2);
    }

    @Test
    public void movingForward_shouldMovingMowerToNewPosition_whenDirectionIsSud() {
        //given
        Mower mower = new Mower(SOUTH, startPosition);

        //when
        mowerActionService.movingForward(mower, grid);

        //then
        assertThat(mower.getPosition().getX()).isEqualTo(1);
        assertThat(mower.getPosition().getY()).isEqualTo(1);
    }

    @Test
    public void movingForward_shouldPrintException_whenMowerIsNull() {
        //when
        mowerActionService.movingForward(null, null);

        //then
        verify(console).print("Invalid Mower");
    }

    @Test
    public void movingForward_shouldNotMovingMowerToNewPosition_whenNewCellIsNotEmpty() {
        //given
        grid.getGrid()[1][1] = "N";
        Mower mower = new Mower(SOUTH, startPosition);

        //when
        mowerActionService.movingForward(mower, grid);

        //then
        assertThat(mower.getPosition()).isEqualTo(startPosition);
    }

    @Test
    public void movingForward_shouldNotMovingMowerToNewPosition_whenNewCellIsOutOfBounds() {
        //given
        startPosition = new Coordinates(6, 6);
        Mower mower = new Mower(SOUTH, startPosition);

        //when
        mowerActionService.movingForward(mower, grid);

        //then
        assertThat(mower.getPosition()).isEqualTo(startPosition);
        verify(console).print("The position [6][5] is out of bounds");
    }

    @Test
    public void createMower_shouldCreateMower() {
        //when
        String[] array = new String[]{"1", "2", "N"};
        Mower mower = mowerActionService.createMower(array);

        //then
        assertThat(mower.getOrientation()).isEqualTo(NORTH);
        assertThat(mower.getPosition().getX()).isEqualTo(1);
        assertThat(mower.getPosition().getY()).isEqualTo(2);
    }

    @Test
    public void movingMower_shouldForwardMower_WhenInstructionIsA_andCaseIsEmpty() {
        //given
        Coordinates coordinates = new Coordinates(1, 2);
        Mower mower = new Mower(EAST, coordinates);

        //when
        mowerActionService.movingMower(grid, mower, "A");

        //then
        assertThat(mower.getPosition().getX()).isEqualTo(2);
        assertThat(mower.getPosition().getY()).isEqualTo(2);
    }

    @Test
    public void movingMower_shouldNotForwardMower_WhenInstructionIsA_andCaseIsNotEmpty() {
        //given
        Coordinates coordinates = new Coordinates(1, 2);
        Mower mower = new Mower(EAST, coordinates);
        grid.getGrid()[2][2] = "N";

        //when
        mowerActionService.movingMower(grid, mower, "A");

        //then
        assertThat(mower.getPosition()).isEqualTo(coordinates);
    }

    @Test
    public void movingMower_shouldTurnRightMower_WhenInstructionIsD() {
        //given
        Coordinates coordinates = new Coordinates(1, 2);
        Mower mower = new Mower(NORTH, coordinates);

        //when
        mowerActionService.movingMower(grid, mower, "D");

        //then
        assertThat(mower.getPosition()).isEqualTo(coordinates);
        assertThat(mower.getOrientation()).isEqualTo(EAST);

    }

    @Test
    public void movingMower_shouldTurnLeft_WhenInstructionIsR() {
        //given
        Coordinates coordinates = new Coordinates(1, 2);
        Mower mower = new Mower(NORTH, coordinates);

        //when
        mowerActionService.movingMower(grid, mower, "G");

        //then
        assertThat(mower.getPosition()).isEqualTo(coordinates);
        assertThat(mower.getOrientation()).isEqualTo(WEST);
    }

}
