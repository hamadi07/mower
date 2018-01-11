package com;

import com.actions.GridService;
import com.actions.InstructionService;
import com.actions.MowerActionService;
import com.component.grid.Grid;
import com.component.mower.Mower;
import com.util.ConsoleOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;


@Controller
public class DemoController {

    private static final String REGEX = " ";

    @Autowired
    InstructionService instructionService;

    @Autowired
    MowerActionService mowerActionService;

    @Autowired
    GridService gridService;

    @Autowired
    ConsoleOverride consoleOverride;

    public void testApplication(String path) throws IOException {
        List<String> listInstructionFromFile = instructionService.getListInstructionFromFile(path);
        Grid grid = gridService.createGrid(listInstructionFromFile);

        int instructionIndice = 1;
        while (instructionIndice <= listInstructionFromFile.size() - 1) {
            String[] mowerInformation = listInstructionFromFile.get(instructionIndice).split(REGEX);
            Mower mower = mowerActionService.createMower(mowerInformation);
            String instructions = listInstructionFromFile.get(instructionIndice + 1);
            mowerActionService.movingMower(grid, mower, instructions);
            grid.add(mower);
            instructionIndice = instructionIndice + 2;

            consoleOverride.print(mower.getPosition().getX() + "," + mower.getPosition().getY() + " " + mower.getOrientation().getCode());
        }
        gridService.showGrid(grid);
    }
}
