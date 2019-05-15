package com.accenture.flowerShop.controller;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.dao.FlowerDAO;
import com.accenture.flowerShop.form.FlowerCartFormLine;
import com.accenture.flowerShop.form.RegistrationForm;
import com.accenture.flowerShop.model.FlowerInfo;
import com.accenture.flowerShop.model.PaginationResult;
import com.accenture.flowerShop.session.CartLine;
import com.accenture.flowerShop.session.SessionScopeAccountData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
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
    private SessionScopeAccountData accountData;




    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }
    @PostMapping("/registrationCheck")
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
    public String listFlowersHandler(
            Model model, //
            @RequestParam(value = "name", defaultValue = "") String likeName,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "priceMin", defaultValue = "-1") double priceMin,
            @RequestParam(value = "priceMax", defaultValue = "-1") double priceMax,
            @Valid @ModelAttribute("flowerCartFormLine")FlowerCartFormLine line,
            BindingResult result){

        final int maxResult = 5;
        final int maxNavigationPage = 10;

        PaginationResult<FlowerInfo> paginationResult = flowerDAO.queryFlowers(page, //
                maxResult, maxNavigationPage, likeName, priceMin, priceMax);
        model.addAttribute("accountData", accountData);
        model.addAttribute("paginationFlowers", paginationResult);
        return "index";
    }
    @PostMapping("/newCartItem")
    public String CheckNewCartItem(
            Model model,
            @Valid @ModelAttribute("flowerCartFormLine")FlowerCartFormLine line,
            BindingResult result,
            HttpServletRequest request){
        if (result.hasErrors()) {
            return "redirect:"+request.getHeader("referer");
        }
        accountData.getCartInfo().addCartLine(new CartLine(line.getName(),line.getPrice(), line.getQuantity()));
                return "redirect:"+request.getHeader("referer");
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
    @GetMapping("/account_data_initialisation")
    public String accountDataInitialisation(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        accountData.initialize(accountDAO.findAccount(userDetails.getUsername()));
        return "redirect:/";
    }
    @PostMapping("/CreateNewOrder")
    public String creatingOrder(Model model){
        return "";
    }

}