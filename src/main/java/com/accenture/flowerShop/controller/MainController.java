package com.accenture.flowerShop.controller;

import com.accenture.flowerShop.controller.JMS.MessageSender;
import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.dao.FlowerDAO;
import com.accenture.flowerShop.dao.OrderDAO;
import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.form.FlowerCartFormLine;
import com.accenture.flowerShop.form.RegistrationForm;
import com.accenture.flowerShop.model.CartLine;
import com.accenture.flowerShop.model.FlowerInfo;
import com.accenture.flowerShop.model.OrderDto;
import com.accenture.flowerShop.model.PaginationResult;
import com.accenture.flowerShop.service.UserMarshallingServiceImpl;
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
import java.math.BigDecimal;


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
    private OrderDAO orderDAO;
    @Autowired
    private SessionScopeAccountData accountData;
    @Autowired
    UserMarshallingServiceImpl marshallingService;
    @Autowired
    MessageSender messageSender;




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
        Account account;
        try {
            account = accountDAO.save(registrationForm);

        } catch (Exception e) {
            String message = e.getMessage();
            model.addAttribute("message", message);
            return "registration";

        }
        try {
            messageSender.sendMessage(account);
        }catch (Exception e){}
        if(account != null){
            try{
                marshallingService.convertFromObjectToXMLAndSave(account);
            }catch (Exception e){
                model.addAttribute("error", e.getMessage());
            }
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
            @RequestParam(value = "cartError", defaultValue = "") String cartError,
            @Valid @ModelAttribute("flowerCartFormLine")FlowerCartFormLine line,
            BindingResult result){

        final int maxResult = 5;
        final int maxNavigationPage = 10;

        PaginationResult<FlowerInfo> paginationResult = flowerDAO.queryFlowers(page, //
                maxResult, maxNavigationPage, likeName, priceMin, priceMax);
        model.addAttribute("name", likeName);
        model.addAttribute("priceMin", priceMin);
        model.addAttribute("priceMax", priceMax);
        model.addAttribute("accountData", accountData);
        model.addAttribute("paginationFlowers", paginationResult);
        model.addAttribute("cartError", cartError);
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
        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }
    @GetMapping("/account_data_initialisation")
    public String accountDataInitialisation(Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        accountData.initialize(accountDAO.findAccount(userDetails.getUsername()).get());
        return "redirect:/";
    }
    @GetMapping("/CreateNewOrder")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String creatingOrder(Model model,
            HttpServletRequest request){

        try {
            orderDAO.save(accountData.getCartInfo());
        }catch (Exception e){
            model.addAttribute("cartError", e.getMessage());
            accountData.clearCart();
            return "redirect:/";
        }

        accountData.clearCart();
        return "redirect:/orders";
    }
    @RequestMapping("/orders")
    public String listOrders(
            Model model,//
            @RequestParam(value = "page", defaultValue = "1") int page){

        final int maxResult = 5;
        final int maxNavigationPage = 10;

        PaginationResult<OrderDto> paginationResult = orderDAO.queryOrders(page, //
                maxResult, maxNavigationPage, "sortByDate");
        model.addAttribute("accountData", accountData);
        model.addAttribute("paginationOrders", paginationResult);
        return "orders";
    }
    @PostMapping("/payOrder")
    public String payOrder(HttpServletRequest request,
                           @RequestParam(value = "OrderId") long orderId){

        try {
            BigDecimal newBalance;
            newBalance = orderDAO.payOrder(orderId);

            accountData.setBalance(newBalance);
        }catch (Exception e){
            return "redirect:"+request.getHeader("referer");
        }
        return "redirect:"+request.getHeader("referer");
    }
    @RequestMapping("/orderList")
    public String listAdminOrders(
            Model model,//
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "sortType", defaultValue = "sortByDate")String sortType){

        final int maxResult = 5;
        final int maxNavigationPage = 10;

        PaginationResult<OrderDto> paginationResult = orderDAO.queryOrders(page, //
                maxResult, maxNavigationPage, sortType, false);
        model.addAttribute("accountData", accountData);
        model.addAttribute("paginationOrders", paginationResult);
        return "orderList";
    }
    @PostMapping("/closeOrder")
    public String closeOrder(HttpServletRequest request,
                             @RequestParam(value = "OrderId") long orderId){
        try {
            orderDAO.closeOrder(orderId);
        }catch (Exception e){
            return "redirect:"+request.getHeader("referer");
        }
        return "redirect:"+request.getHeader("referer");
    }
    @GetMapping("/test")
    public String testPage(){return "test";}
}