package lt.task_13.task1_3.controllers;

import lt.task_13.task1_3.exceptions.BadEmailException;
import lt.task_13.task1_3.exceptions.BadPasswordException;
import lt.task_13.task1_3.exceptions.BadPhoneNumberException;
import lt.task_13.task1_3.model.User;
import lt.task_13.task1_3.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping("/users")
    public String showAll(ModelMap model) {
        model.put("users", userService.findAll());
        return "users";
    }

    @GetMapping("/add-user")
    public String showAddPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/add-user")
    public String add(ModelMap model, @ModelAttribute("user") User user, BindingResult result) {

        if(result.hasErrors()) {
            return "add-user";
        }


        try {
            userService.add(user);
        } catch (BadPasswordException e) {
            return "error-password";
        } catch (BadEmailException e) {
            return "error-email";
        } catch (BadPhoneNumberException e) {
            return "error-phone";
        }

        return "redirect:/users";
    }

    @GetMapping("/delete-user/{id}")
    @Transactional
    public String delete(@PathVariable int id) {
        userService.deleteById(id);

        return "redirect:/users";
    }

    @GetMapping("/update-user/{id}")
    public String showUpdatePage(ModelMap model, @PathVariable int id) {
        model.addAttribute("user", userService.findById(id));
        return "add-user";
    }

    @PostMapping("/update-user/{id}")
    public String update(ModelMap model, @ModelAttribute("user") User user, @PathVariable int id,
                         BindingResult result) {
        if(result.hasErrors()) {
            return "add-user";
        }

        try {
            userService.update(user, id);
        } catch (BadPasswordException e) {
            return "error-password";
        } catch (BadEmailException e) {
            return "error-email";
        } catch (BadPhoneNumberException e) {
            return "error-phone";
        }

        return "redirect:/users";
    }

    @GetMapping("/find")
    public String showFindPage(ModelMap model) {

        List<User> userList = new ArrayList<>();
        model.addAttribute("users", userList);
        model.addAttribute("userID", new User());
        return "find";
    }

    @PostMapping("/find")
    public String findUser(ModelMap model, @ModelAttribute("userID") User user, BindingResult result) throws Exception {

        if(result.hasErrors()) {
            return "find";
        }



        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/user/{id}")
    public String showUser(ModelMap model, @ModelAttribute("users") User user, @PathVariable int id, BindingResult result) {

        if(result.hasErrors()) {
            return "find";
        }

        User found = userService.findById(id);
        List<User> userList = new ArrayList<>();
        userList.add(found);
        model.put("users", userList);
        return "user";
    }
}
