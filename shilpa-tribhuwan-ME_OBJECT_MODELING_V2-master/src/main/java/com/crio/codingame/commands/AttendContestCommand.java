package com.crio.codingame.commands;

import java.util.List;

//import com.crio.codingame.dtos.UserRegistrationDto;
//import com.crio.codingame.entities.Contest;
//import com.crio.codingame.entities.ContestStatus;
import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.exceptions.InvalidOperationException;
import com.crio.codingame.exceptions.UserNotFoundException;
//import com.crio.codingame.repositories.ContestRepository;
//import com.crio.codingame.services.ContestService;
import com.crio.codingame.services.IUserService;

public class AttendContestCommand implements ICommand{

    private final IUserService userService;
    
    public AttendContestCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute attendContest method of IUserService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["ATTEND_CONTEST","3","Joey"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens)
            throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {

        String contestId = tokens.get(1);
        String userName = tokens.get(2);

        try {
            System.out.println(userService.attendContest(contestId, userName));
        } catch (ContestNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
