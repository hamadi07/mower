
import com.actions.GridService;
import com.component.grid.Grid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class GridServiceTest {

    @InjectMocks
    private GridService gridService;

    @Test
    public void createGride_shouldCreateGridFromFile() {
        //given
        List<String> instructionList = new ArrayList<>();
        instructionList.add("5 5");

        //when
        Grid returnedGrid = gridService.createGrid(instructionList);

        //then
        assertThat(returnedGrid).isNotNull();
    }

    @Test
    public void createGride_shouldThrowExeptionWhenXIsNoValid() {
        //given
        List<String> instructionList = new ArrayList<>();
        instructionList.add("X 5");

        //when
        Throwable throwable = catchThrowable(() -> gridService.createGrid(instructionList));

        //then
        assertThat(throwable).isInstanceOf(NumberFormatException.class);
    }

    @Test
    public void createGride_shouldThrowExeptionWhenYIsNoValid() {
        //given
        List<String> instructionList = new ArrayList<>();
        instructionList.add("5 X");

        //when
        Throwable throwable = catchThrowable(() -> gridService.createGrid(instructionList));

        //then
        assertThat(throwable).isInstanceOf(NumberFormatException.class);
    }

}