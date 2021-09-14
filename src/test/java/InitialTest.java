
import client.PeopleApiClient;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

public class InitialTest {

    PeopleApiClient peopleApiClient = new PeopleApiClient();
    HttpResponse response;
    HttpResponse getPeople;
    HttpResponse getOnePerson;
    @Test
    public void InitialTest() throws Exception{


       // HttpResponse welcomeRequest = peopleApiClient.getWelcomeRequest();
        response =peopleApiClient.getWelcomeRequest();
        getPeople =peopleApiClient.getAllPeople();
        getOnePerson = peopleApiClient.getOnePerson();
      String body = EntityUtils.toString(response.getEntity());
      String body1 = EntityUtils.toString(getPeople.getEntity());
      String body2 = EntityUtils.toString(getOnePerson.getEntity());

    }
}
