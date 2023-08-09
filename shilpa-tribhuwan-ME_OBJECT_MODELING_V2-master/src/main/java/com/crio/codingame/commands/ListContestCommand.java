package com.crio.codingame.commands;

import java.util.ArrayList;
import java.util.List;
//import javax.lang.model.util.ElementScanner6;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
//import com.crio.codingame.repositories.ContestRepository;
//import com.crio.codingame.entities.Contest;
//import com.crio.codingame.entities.Level;
import com.crio.codingame.services.IContestService;

public class ListContestCommand implements ICommand{

    private final IContestService contestService;
    
    public ListContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllContestLevelWise method of IContestService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LIST_CONTEST","HIGH"]
    // or
    // ["LIST_CONTEST"]

    @Override
    public void execute(List<String> tokens) {
        List <Contest> contests = new ArrayList<>();
        if(tokens.size() == 1)
        {
            contests = contestService.getAllContestLevelWise(null);
            System.out.println(contests.toString());
        }
        else
        {
            String level = tokens.get(1);
            if(level.equalsIgnoreCase("LOW"))
                contests = contestService.getAllContestLevelWise(Level.LOW);
            else if(level.equalsIgnoreCase("HIGH"))
                contests = contestService.getAllContestLevelWise(Level.HIGH);
            else
                contests = contestService.getAllContestLevelWise(Level.MEDIUM);
            System.out.println(contests.toString());
        }
    }
    
}
