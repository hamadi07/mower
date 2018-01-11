package com.actions;

import com.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class InstructionService {

    @Autowired
    private FileUtil fileUtil;

    public List<String> getListInstructionFromFile(String path) throws IOException {
        File file = fileUtil.readFile(path);
        return fileUtil.redLines(file.getAbsoluteFile());
    }

}
