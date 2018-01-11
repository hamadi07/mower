import com.actions.InstructionService;
import com.util.FileUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InstructionServiceTest {

    private static final String INSTRUCTION_TXT = "instruction.txt";

    @InjectMocks
    private InstructionService instructionService;

    @Mock
    private FileUtil fileUtil;

    @Mock
    private File file;

    private List<String> instructions;


    @Before
    public void setUp() {
        instructions = new ArrayList<String>();
    }

    @Test
    public void getListInstructionFromFile_shouldReturnListOfInstructionFromFile() throws IOException {
        //given
        when(fileUtil.readFile(INSTRUCTION_TXT)).thenReturn(file);

        //when
        List<String> returnedInstructions = instructionService.getListInstructionFromFile(INSTRUCTION_TXT);

        //then
        assertThat(returnedInstructions).isEqualTo(instructions);
    }

    @Test
    public void getListInstructionFromFile_shouldReadLinesFromFile() throws IOException {
        //given
        instructions.add("5 5");
        when(fileUtil.readFile(INSTRUCTION_TXT)).thenReturn(file);
        when(fileUtil.redLines(file.getAbsoluteFile())).thenReturn(instructions);

        //when
        List<String> returnedInstructions = instructionService.getListInstructionFromFile(INSTRUCTION_TXT);

        //then
        assertThat(returnedInstructions.get(0)).isEqualTo("5 5");
    }
}