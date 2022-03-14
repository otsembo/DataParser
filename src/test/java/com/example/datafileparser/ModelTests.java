package com.example.datafileparser;

import com.example.datafileparser.data.model.User;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.mock;

public class ModelTests {

    @Test
    public void testModelPropertiesExist(){
        User testUser = mock(User.class);

        MatcherAssert.assertThat("id property does not exist", testUser,  hasProperty("id"));
        MatcherAssert.assertThat("first_name property does not exist", testUser,  hasProperty("first_name"));
        MatcherAssert.assertThat("last_name property does not exist", testUser,  hasProperty("last_name"));
        MatcherAssert.assertThat("email property does not exist", testUser,  hasProperty("email"));
        MatcherAssert.assertThat("gender property does not exist", testUser,  hasProperty("gender"));
        MatcherAssert.assertThat("phone property does not exist", testUser,  hasProperty("phone"));
    }

}
