package com.sample.web.controller;

import com.sample.model.Person;
import com.sample.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Arrays;
import java.util.Properties;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Created by U0128754 on 3/23/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PersonService serviceMock;

    @Before
    public void setUp() {
        PersonController ctl = new PersonController();
        ctl.setPersonService(serviceMock);

        mockMvc = MockMvcBuilders.standaloneSetup(ctl)
                .setHandlerExceptionResolvers(exceptionResolver())
                .setValidator(validator())
                .setViewResolvers(viewResolver())
                .build();
    }


    private HandlerExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        Properties exceptionMappings = new Properties();

        exceptionMappings.put("java.lang.Exception", "errors");
        exceptionMappings.put("java.lang.RuntimeException", "errors");

        exceptionResolver.setExceptionMappings(exceptionMappings);

        Properties statusCodes = new Properties();

        statusCodes.put("error/404", "404");
        statusCodes.put("error/error", "500");

        exceptionResolver.setStatusCodes(statusCodes);

        return exceptionResolver;
    }

    private LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    private ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }


    @Test
    public void listPersons() throws Exception {

        Person p1 = new Person();
        p1.setId(1);
        p1.setName("first");

        Person p2 = new Person();
        p2.setId(2);
        p2.setName("second");

        when(serviceMock.listPersons()).thenReturn(Arrays.asList(p1, p2));

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(view().name("persons"))
                .andExpect(forwardedUrl("/WEB-INF/pages/persons.jsp"))
                .andExpect(model().attribute("listOfPersons", hasSize(2)))
                .andExpect(model().attribute("listOfPersons", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("name", is("first"))
                        )
                )))
                .andExpect(model().attribute("listOfPersons", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("name", is("second"))
                        )
                )
                ))
 ;
        verify(serviceMock, times(1)).listPersons();
        verifyNoMoreInteractions(serviceMock);
    }

//    @Test
    public void testNonExistsDelete() throws Exception {
        mockMvc.perform(get("/persons/remove/-1"))
                .andDo(print())
                .andExpect(forwardedUrl("/WEB-INF/pages/errors.jsp"))
                 .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(content().string(containsString("Exception")));
    }
}
