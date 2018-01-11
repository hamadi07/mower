import com.util.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class FileUtilTest {

    @InjectMocks
    private FileUtil fileUtil;

    @Mock
    private File file;

    @Test
    public void readInstruction_shouldReturnFile() {
        //when
        File returnedFile = fileUtil.readFile("path");

        //then
        assertThat(returnedFile).isNotNull();
    }

    @Test
    public void redLines_shouldThrowEception_whenFileDoesNotExist() {
        //when
        Throwable throwable = catchThrowable(() -> fileUtil.redLines(file));

        //then
        assertThat(throwable).isInstanceOf(IOException.class);
    }

}