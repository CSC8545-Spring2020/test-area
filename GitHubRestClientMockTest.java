package edu.studio.issue;

import static org.junit.jupiter.api.Assertions.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kong.unirest.HttpResponse;


class GitHubRestClientMockTest {
    
    private Mockery context = new Mockery();

    private GitHubRestClient restClient = new GitHubRestClient();

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testGetIssues() {
        //mock the HttpResponse<T> which is an interface
        @SuppressWarnings("unchecked")
        final HttpResponse<String> mockResponse = 
               (HttpResponse<String>) context.mock(HttpResponse.class, "mockResponse");
        final String fakeResponse = "\"title\": \"fizzbuzz\"";
        
        context.checking(new Expectations() {
            {
                oneOf(mockResponse).getStatus(); 
                will(returnValue(200));
               
                oneOf(mockResponse).getBody(); 
                will(returnValue(fakeResponse));
            }
        });
        
        String result = restClient.extractJsonFromResponse(mockResponse);
        
        context.assertIsSatisfied();

        assertEquals(fakeResponse, result);
    }

}
