package com.example.datafileparser;

import com.example.datafileparser.data.repository.DataRepository;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.mock;

public class RepositoryTests {

    public DataRepository dataRepository;


    @Before
    public void setUp(){
        dataRepository = mock(DataRepository.class);
    }

    @Test
    public void testRepoHasProperties(){
        MatcherAssert.assertThat("class does not have buffered reader property dataBuffer", dataRepository, hasProperty("dataBuffer"));
        MatcherAssert.assertThat("class does not have FileOperator property operator", dataRepository, hasProperty("operator"));
        MatcherAssert.assertThat("class does not have ArrayList<User> property userList", dataRepository, hasProperty("userList"));
    }



}
