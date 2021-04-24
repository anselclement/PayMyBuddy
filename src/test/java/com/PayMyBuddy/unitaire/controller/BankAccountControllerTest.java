package com.PayMyBuddy.unitaire.controller;

import com.PayMyBuddy.controllers.BankAccountController;
import com.PayMyBuddy.repository.UserRepository;
import com.PayMyBuddy.security.MyUserDetails;
import com.PayMyBuddy.services.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"com.PayMyBuddy" })
@ContextConfiguration(classes = {WebAppConfiguration.class})
@WebMvcTest
public class BankAccountControllerTest {


    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private BankAccountService bankAccountService;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private UserConnectionService userConnectionService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MyUserDetails userDetails;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
    public void saveBankAccountTest() throws Exception{
        mockMvc.perform(post("/addBankAccount"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home/profile"));
    }

    @Test
    public void deleteUserBankAccountTest() throws Exception{
        mockMvc.perform(get("/deleteBankAccount/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home/profile"));
    }
}
