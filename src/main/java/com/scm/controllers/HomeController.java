package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/path")
    public String test(){
        System.out.println("SAHARI");
        return "home";
    }

    @GetMapping("/home")
    public String home(){
        System.out.println("HOME");
        return "home";
    }

    @GetMapping("/about")
    public String about(){
        System.out.println("ABOUT");
        return "about";
    }

    @GetMapping("/services")
    public String services(){
        System.out.println("SERVICES");
        return "services";
    }

    @GetMapping("/contact")
    public String sample(){
        System.out.println("CONTACT");
        return "contact";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("LOGIN");
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "signup";
    }

    //registering user
    @PostMapping("/do-register")
    public String registerUser(@ModelAttribute UserForm userForm, HttpSession session){
        System.out.println("redirecting");

        //fetch data from form
        //userform
        System.out.println(userForm);
        // validate the form data
        // save the data to database

        // converted data from userForn -> user
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .phoneNumber(userForm.getPhoneNumber())
        // .about(userForm.getAbout())
        // .password(userForm.getPassword())
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setAbout(userForm.getAbout());
        user.setPassword(userForm.getPassword());

        userService.saveUser(user);

        Message message = Message.builder()
                                 .content("Registration Successful")
                                 .type(MessageType.green)
                                 .build();

        session.setAttribute("message", message);
        // message = "Registration Successful"
        // redirect to login page

        return "redirect:/signup";
    }

}
