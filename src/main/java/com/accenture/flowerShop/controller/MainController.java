package com.accenture.flowerShop.controller;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.dao.FlowerDAO;
import com.accenture.flowerShop.form.RegistrationForm;
import com.accenture.flowerShop.model.FlowerInfo;
import com.accenture.flowerShop.model.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;


@Controller
// Enable Hibernate Transaction.
@Transactional
// Need to use RedirectAttributes
@EnableWebMvc
public class MainController {

    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private FlowerDAO flowerDAO;
    @Autowired
    private LocalValidatorFactoryBean validator;


    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }
    @PostMapping("/registrationCheck")
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String validateRegistration(Model model, @Valid @ModelAttribute("registrationForm")RegistrationForm registrationForm, //
                                       BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }
        try {
            accountDAO.save(registrationForm);
        } catch (Exception e) {
            String message = e.getMessage();
            model.addAttribute("message", message);
            return "registration";

        }
        model.addAttribute("registered", "You have successfully registered");
        return "redirect:/login";
    }
    @GetMapping( "/login" )
    public String login(Model model) {

        return "login";
    }
    @RequestMapping("/403")
    public String accessDenied() {
        return "/403";
    }

    @RequestMapping("/")
    public String listFlowersHandler(Model model, //
            @RequestParam(value = "name", defaultValue = "") String likeName,
    @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;

        PaginationResult<FlowerInfo> result = flowerDAO.queryFlowers(page, //
                maxResult, maxNavigationPage, likeName);

        model.addAttribute("paginationFlowers", result);
        return "index";
    }
    @GetMapping("/accountInfo")
    public String accountInfo(Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());
        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }

}