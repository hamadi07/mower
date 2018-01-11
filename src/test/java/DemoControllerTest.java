import com.DemoController;
import com.actions.GridService;
import com.actions.InstructionService;
import com.actions.MowerActionService;
import com.util.ConsoleOverride;
import com.util.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@ContextConfiguration(classes = {DemoController.class, MowerActionService.class, InstructionService.class, GridService.class, FileUtil.class, ConsoleOverride.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoControllerTest {

    @Autowired
    DemoController demoController;

    @Autowired
    InstructionService instructionService;

    @Autowired
    MowerActionService mowerActionService;

    @Autowired
    GridService gridService;


    @Test
    public void testApplication() throws IOException {
        demoController.testApplication("resources/instruction.txt");
    }
}