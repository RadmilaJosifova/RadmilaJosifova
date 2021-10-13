package code.academy.peopleapi;


import client.PeopleApiClient;
import model.requests.PostNewPersonRequest;
import model.responses.PostNewPersonResponse;
import model.responses.PutUpdateResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payloads.PostNewPersonPayload;

import static org.apache.http.HttpStatus.*;
import static utils.ConversionUtils.jsonStringToObject;
import static utils.ConversionUtils.objectToJsonString;

public class UpDateLocationFeatureTest {


    PeopleApiClient peopleApiClient = new PeopleApiClient();
    HttpResponse response;
    PostNewPersonPayload updateNewPersonPayload = new PostNewPersonPayload();
    PostNewPersonRequest updateLocationRequest = updateNewPersonPayload.createNewPersonPayload();
    PutUpdateResponse putUpdateResponse = new PutUpdateResponse();
    PostNewPersonPayload createNewPersonPayload = new PostNewPersonPayload();

    String invalidId = "87954";
    String createPersonID;
    public UpDateLocationFeatureTest() throws Exception {
    }
    @BeforeClass
    public void beforeClass() throws Exception {
        PostNewPersonRequest createNewPersonRequest =createNewPersonPayload.createNewPersonPayload();
        String requestBody = objectToJsonString(createNewPersonRequest);

        HttpResponse createPersonResponse = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", requestBody);

        Assert.assertEquals(createPersonResponse.getStatusLine().getStatusCode(), SC_CREATED);

        String responseBody = EntityUtils.toString(createPersonResponse.getEntity());
        PostNewPersonResponse postNewPersonResponse = jsonStringToObject(responseBody, PostNewPersonResponse.class);

        createPersonID = postNewPersonResponse.getPersonData().getId();
    }

    @Test
    public void updatePersonLocation() throws Exception {

        String requestBody = objectToJsonString(updateLocationRequest);
        response = peopleApiClient.httpPut("https://people-api1.herokuapp.com/api/person/" + createPersonID, requestBody);
        String responseBody = EntityUtils.toString(response.getEntity());
        putUpdateResponse = jsonStringToObject(responseBody, PutUpdateResponse.class);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_OK);
        Assert.assertEquals(putUpdateResponse.getCode(), "P200");
        Assert.assertEquals(putUpdateResponse.getMessage(), "Person's location succesfully updated !");
        Assert.assertEquals(putUpdateResponse.getPerson().getLocation(), updateLocationRequest.getLocation());
    }

    @Test
    public void notExistingPersonalId() throws Exception {

        String requestBody = objectToJsonString(updateLocationRequest);
        response = peopleApiClient.httpPut("https://people-api1.herokuapp.com/api/person/" +invalidId, requestBody);

        String responseBody = EntityUtils.toString(response.getEntity());
        putUpdateResponse = jsonStringToObject(responseBody, PutUpdateResponse.class);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_NOT_FOUND);
       // Assert.assertEquals(putUpdateResponse.getCode(), "P404");
       // Assert.assertEquals(putUpdateResponse.getMessage(), "Person with id="+invalidId+"not found");
       // Assert.assertEquals(putUpdateResponse.getPerson().getLocation(), updateLocationRequest.getLocation());

        Assert.assertEquals(putUpdateResponse.getMessage(), "Person with id="+ invalidId
                +" not found");
    }

    @Test
    public void updatePersonLocationAsNull() throws Exception {

        PostNewPersonRequest updateEmptyLocationRequest = updateNewPersonPayload.updateNewEmptyPersonPayload();
        String requestBody = objectToJsonString(updateEmptyLocationRequest);
        response = peopleApiClient.httpPut("https://people-api1.herokuapp.com/api/person/" + invalidId, requestBody);
        String responseBody = EntityUtils.toString(response.getEntity());
        putUpdateResponse = jsonStringToObject(responseBody, PutUpdateResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_BAD_REQUEST);
        Assert.assertEquals(putUpdateResponse.getCode(), "P400");
        Assert.assertEquals(putUpdateResponse.getMessage(), "Person's location must be provided to be updated !");
    }

    @Test
    public void updatePersonLocationEmptyRequest() throws Exception {

        PostNewPersonRequest updateEmptyLocationRequest = updateNewPersonPayload.updateNewEmptyPersonPayload();
        updateEmptyLocationRequest.setLocation(null);
        String requestBody = objectToJsonString(updateEmptyLocationRequest);
        response = peopleApiClient.httpPut("https://people-api1.herokuapp.com/api/person/" + invalidId, requestBody);
        String responseBody = EntityUtils.toString(response.getEntity());
        putUpdateResponse = jsonStringToObject(responseBody, PutUpdateResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_BAD_REQUEST);
        Assert.assertEquals(putUpdateResponse.getCode(), "P400");
        Assert.assertEquals(putUpdateResponse.getMessage(), "Request body cannot be empty");
    }

    @AfterClass
    public void afterClass() throws Exception {
        HttpResponse deleteResponse =peopleApiClient.httpDelete("https://people-api1.herokuapp.com/api/person/"+createPersonID);

        Assert.assertEquals(deleteResponse.getStatusLine().getStatusCode(),SC_OK);
    }
}