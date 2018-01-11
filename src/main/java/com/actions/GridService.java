package com.actions;

import com.component.grid.Grid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridService {

    public Grid createGrid(List<String> instructionList) {
        String[] gridSize = instructionList.get(0).split(" ");
        int x = Integer.valueOf(gridSize[0]);
        int y = Integer.valueOf(gridSize[1]);
        return new Grid(x + 1, y + 1);
    }

    public void showGrid(Grid grid) {
        StringBuilder GridCase = new StringBuilder();
        for (int x = 0; x < grid.getWidth(); x++) {
            GridCase.append("|").append(x);
        }
        System.out.print("    " + GridCase + "| \n");
        for (int y = grid.getHeight() - 1; y >= 0; y--) {
            System.out.print(y + "==> ");
            for (int x = 0; x < grid.getWidth(); x++) {
                System.out.print(grid.getGrid()[x][y] + "|");
            }
            System.out.println(" ");
        }
    }
}
