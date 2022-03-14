package com.example.datafileparser;

import com.example.datafileparser.data.model.User;
import com.example.datafileparser.domain.FileOperator;
import com.example.datafileparser.domain.UserParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class DomainTests {

    private FileOperator operator;
    private UserParser userParser;

    @Before
    public void setUp(){
        operator = mock(FileOperator.class);
        userParser = mock(UserParser.class);
    }

    @Test
    public void testFetchCSVReturnsInputStream() throws IOException{
        Method returnValue = operator.getClass().getDeclaredMethods()[0];
        assertSame("Method does not return an input stream ",InputStream.class, returnValue.getReturnType());
    }

    @Test
    public void testWriteCSVRequiresString() throws IOException{
        Method writeCsv = operator.getClass().getDeclaredMethods()[1];
        Parameter param = writeCsv.getParameters()[0];
        assertSame("Method does not require string param", String.class, param.getType());
    }

    @Test
    public void testUserParserReturnsUserObject(){
        Method returnValue = userParser.getClass().getDeclaredMethods()[0];
        assertSame("Method does not return user class", User.class, returnValue.getReturnType());
    }


}
