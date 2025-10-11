package com.example.demo.controller;

import com.example.demo.bean.Sudoku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ToolsController {

    @GetMapping("/tools/sudoku")
    public String getSudoku(){
        return "/tools/sudoku";
    }

    @PostMapping("/tools/sudoku")
    public String postSudoku(Sudoku sudoku){
        sudoku.printSudoku();
        log.info("process sudoku...");
        sudoku.dfs(0,0);
        sudoku.printSudoku();
        return "/tools/sudoku";
    }

}
