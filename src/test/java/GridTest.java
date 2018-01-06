import coordinates.Coordinates;
import grid.Grid;
import mower.Mower;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static mower.MowerOrientationEnum.SOUTH;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GridTest {
    private Grid grid;

    @Before
    public void setUp() {
        grid = new Grid(5, 5);
    }

    @Test
    public void grid_shouldCreateGrid() {
        //then
        assertThat(grid.getGrid()).isNotEmpty();
        assertThat(grid.getGrid()[1][1]).isEqualTo("V");
    }

    @Test
    public void add_shouldAddStringIntoCell() {
        //given
        Coordinates coordinates = new Coordinates(2, 2);
        Mower mower = new Mower(SOUTH, coordinates);

        //when
        grid.add(mower);

        //then
        assertThat(grid.getGrid()[2][2]).isEqualTo("S");
    }
}