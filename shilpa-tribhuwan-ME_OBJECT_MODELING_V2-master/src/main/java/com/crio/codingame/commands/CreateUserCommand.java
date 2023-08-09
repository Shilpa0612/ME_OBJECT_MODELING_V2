package com.crio.codingame.commands;

import java.util.List;
//import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.User;
import com.crio.codingame.services.IUserService;

public class CreateUserCommand implements ICommand{

    private final IUserService userService;
    
    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_QUESTION","Ross"]

    @Override
    public void execute(List<String> tokens) {
        /* String contestName = tokens.get(1); 
        Level level = Level.valueOf(tokens.get(2)); 
        String contestCreator = tokens.get(3); Integer numQuestion;
         * 
         * if (tokens.size() == 4) { numQuestion = null; } else { numQuestion =
         * Integer.parseInt(tokens.get(4)); }
         * 
         * try { Contest contest = contestService.create(contestName, level, contestCreator,
         * numQuestion); System.out.println(contest); } catch (UserNotFoundException e) {
         * System.out.println("Contest Creator for given name: creator not found!"); } catch
         * (QuestionNotFoundException e) { System.out.println(
         * "Request cannot be processed as enough number of questions can not found. Please try again later!"
         * ); }
         */
        String name = tokens.get(1);
        //List<Contest> contest = new ArrayList<>();
        User user = new User("1",name, 0);
        userService.create(name);
        //User user = new User(name, 0);
        
        System.out.println(user.toString());
    }
    
}
