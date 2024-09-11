package com.hdfc.notificationadminportal;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.notificationadmin.controller.CUGUserController;
import com.notification.notificationadmin.dto.CUGUserDto;
import com.notification.notificationadmin.exceptions.CUGUserAlreadyExistsException;
import com.notification.notificationadmin.service.ICUGUserService;

public class CUGUserControllerTest {
	private MockMvc mockMvc;

    @Mock
    private ICUGUserService iCUGUserService;

    @InjectMocks
    private CUGUserController cugUserController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cugUserController).build();
    }

    @Test
    void testCreateCUGUsers() throws Exception {

        List<CUGUserDto> cugUserDtos = List.of(new CUGUserDto("8765435672", "Vikas"));
        Integer expectedCount = 1;

        when(iCUGUserService.createCUGUser(anyList())).thenReturn(expectedCount);

        MockHttpServletRequestBuilder requestBuilder = post("/notification-portal/cug/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(cugUserDtos));

        
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("CUG user created successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.count").value(expectedCount));
    }
    
    @Test
    void testCreateCUGUsersAlreadyExist() throws Exception {

        List<CUGUserDto> cugUserDtos = List.of(new CUGUserDto("8765435672", "Vikas"));
        Integer expectedCount = 1;

        when(iCUGUserService.createCUGUser(anyList())).thenReturn(expectedCount);

        MockHttpServletRequestBuilder requestBuilder = post("/notification-portal/cug/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(cugUserDtos));

        
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("CUG user created successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.count").value(expectedCount));
    }
    
    @Test
    void testCreateCUGUsersAlreadyExists() throws Exception {
        List<CUGUserDto> cugUserDtos = List.of(new CUGUserDto("8765435672", "Vikas"));
        String errorMessage = "User already exists with mobile numbers 8765435672";

        when(iCUGUserService.createCUGUser(anyList())).thenThrow(new CUGUserAlreadyExistsException(errorMessage));

        MockHttpServletRequestBuilder requestBuilder = post("/notification-portal/cug/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(cugUserDtos));
        System.out.println("response");
        System.out.println(mockMvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.apiPath").value("uri=/notification-portal/cug/users"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value(errorMessage))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorTime").exists());
    }
}
