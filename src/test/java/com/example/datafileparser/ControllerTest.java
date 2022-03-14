package com.example.datafileparser;


import com.example.datafileparser.presentation.controllers.AppErrorController;
import com.example.datafileparser.presentation.controllers.CSVController;
import com.example.datafileparser.presentation.controllers.HomeController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class ControllerTest {

    private HomeController homeController;
    private CSVController csvController;
    private AppErrorController appErrorController;
    private Model model;


    @Before
    public void setUp(){
        homeController = new HomeController();
        csvController = new CSVController();
        appErrorController = new AppErrorController();
        model = mock(Model.class);
    }

    @Test
    public void testIndexReturnsString(){
        Object returnType = homeController.index(model);
        assertSame(returnType.getClass(), String.class);
    }

    @Test
    public void testIndexHasCorrectTemplate(){
        String template = homeController.index(model);
        assertEquals("index", template);
    }

    @Test
    public void testIndexHasGetAnnotation(){
        Method [] methods = homeController.getClass().getDeclaredMethods();
        for (Method method : methods) {
            Assert.assertNotNull(method.getName()+": is not annotated with GET mapping", method.getAnnotation(GetMapping.class));
        }
    }

    @Test
    public void testCSVControllerMethodsReturnStrings(){
        Method[] methods = csvController.getClass().getDeclaredMethods();
        for (Method method : methods) {
            Assert.assertSame(method.getName()+": does not return templates", String.class, method.getReturnType());
        }
    }

    @Test
    public void testErrorControllerIsAnErrorController(){
        Assert.assertTrue(ErrorController.class.isAssignableFrom(appErrorController.getClass()));
    }

    @Test
    public void testErrorControllerReturnsCorrectTemplate(){
        Assert.assertEquals("error", appErrorController.handleError());
    }

    @Test
    public void testErrorControllerHasCorrectMapping(){
        Assert.assertNotNull("Error controller has no mapping",appErrorController.getClass().getDeclaredMethods()[0].getAnnotation(RequestMapping.class));
    }



}
