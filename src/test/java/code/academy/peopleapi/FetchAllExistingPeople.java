package code.academy.peopleapi;

import client.PeopleApiClient;
import model.requests.PostNewPersonRequest;
import model.responses.GetAllPeopleResponse;
import model.responses.PostNewPersonResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.PostNewPersonPayload;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static utils.ConversionUtils.jsonStringToObject;

public class FetchAllExistingPeople {
    public FetchAllExistingPeople() throws Exception {
    }


    HttpResponse response;
    PeopleApiClient peopleApiClient = new PeopleApiClient();
    GetAllPeopleResponse getAllPeopleResponse;

    @Test
    public void FetchAllExistingPeople() throws Exception {
        response = peopleApiClient.httpGet("https://people-api1.herokuapp.com/api/people");
        String body = EntityUtils.toString(response.getEntity());
        getAllPeopleResponse = jsonStringToObject(body, GetAllPeopleResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_OK);
        Assert.assertEquals(getAllPeopleResponse.getCode(), "P200");
        Assert.assertEquals(getAllPeopleResponse.getMessage(), "List of people successfully fetched");
        Assert.assertNotNull(getAllPeopleResponse.getNumberOfPeople());
        Assert.assertNotNull(getAllPeopleResponse.getPeopleData().size());


    }
    @Test

    public void GetAllPeopleNumberOfPeople() throws Exception {
        response = peopleApiClient.httpGet("https://people-api1.herokuapp.com/api/people");
        String body = EntityUtils.toString(response.getEntity());
        getAllPeopleResponse = jsonStringToObject(body, GetAllPeopleResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_OK);
        Assert.assertEquals(getAllPeopleResponse.getCode(), "P200");
        Assert.assertEquals(getAllPeopleResponse.getMessage(), "List of people successfully fetched");
        Assert.assertEquals(getAllPeopleResponse.getPeopleData().size(), getAllPeopleResponse.getNumberOfPeople());

    }
}