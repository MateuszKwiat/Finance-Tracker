package com.financeTracker.FinanceTracker;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;
    private ExpenseService expenseService;

    public AuthController(UserService userService, ExpenseService expenseService) {
        this.userService = userService;
        this.expenseService = expenseService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user    ", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @PostMapping("/tracker/save")
    public String addExpense(@Valid @ModelAttribute("expense") ExpenseDto expenseDto,
                             BindingResult result,
                             Model model) {
        expenseService.saveExpense(expenseDto);
        return "redirect:/tracker?success";
    }

    @GetMapping("/tracker")
    public String tracker(Model model) {
        List<ExpenseDto> expenses = expenseService.findAllExpenses();
        model.addAttribute("expenses", expenses);

        return "tracker";
    }
}