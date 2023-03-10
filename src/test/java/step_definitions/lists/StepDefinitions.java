package step_definitions.lists;

import common.StatusAssertions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.AddToListRequestBody;
import models.CreateListRequestBody;
import org.assertj.core.api.Assertions;
import pages.lists.ListsPage;

import java.util.ArrayList;
import java.util.List;

public class StepDefinitions extends StatusAssertions {
    private final ListsPage listsPage = new ListsPage();
    private String listID;
    private List<CreateListRequestBody> listInformation;
    private Response response;

    @Given("the user has a list id: {string}")
    public void userHasAList(String listID) {
        listsPage.prepareRequest();
        this.listID = listID;
    }

    @Given("the user has the list information")
    public void userHasListInformation(List<CreateListRequestBody> createListRequestBodies) {
        listsPage.prepareRequest();
        listInformation = createListRequestBodies;
    }

    @Given("the user does not have any list information")
    public void userDoesNotHaveAnyListInformation() {
        listsPage.prepareRequest();
        listInformation = new ArrayList<>();
        listInformation.add(null);
    }

    @When("the user tries to get the list details")
    public void userTriesToGetListDetails() {
        response = listsPage.getListDetails(listID);
    }

    @When("the user tries to create the list")
    public void userTriesToCreateList() {
        response = listsPage.createList(listInformation.get(0));
    }

    @When("the user tries to add an item with id : {string} to the list")
    public void userTriesAddItem(String mediaID) {
        AddToListRequestBody addToListRequestBody = new AddToListRequestBody(mediaID);
        response = listsPage.addToList(addToListRequestBody, listID);
    }
    @When("the user tries to clear the list")
    public void userTriesClearList(){
        response = listsPage.clearList(listID);
    }
    @When("the user tries to delete the list")
    public void userDeletesList(){
        response = listsPage.deleteList(listID);
    }

    @Then("the user successfully gets the details of that list")
    public void userSuccessfullyGetsDetails() {
        assertOK(response);
        Assertions.assertThat(response.jsonPath().get("id").toString()).isEqualTo(listID);
        Assertions.assertThat(response.jsonPath().get("name").toString()).isEqualTo("The Marvel Universe");
        Assertions.assertThat(Integer.valueOf(response.jsonPath().get("item_count").toString())).isEqualTo(59);
    }

    @Then("the user can't get the details of that list")
    public void userCannotGetDetails() {
        assertNotFound(response);
    }

    @Then("the list is not created")
    public void listIsNotCreated() {
        assertBadRequest(response);
        Assertions.assertThat(Boolean.parseBoolean(response.jsonPath().get("success").toString())).isFalse();
    }

    @Then("the list is created")
    public void listIsCreated() {
        assertCreated(response);
        Assertions.assertThat(Boolean.parseBoolean(response.jsonPath().get("success").toString())).isTrue();
    }
    @Then("the item is added to the list")
    public void itemIsAdded() {
        assertCreated(response);
        Assertions.assertThat(Boolean.parseBoolean(response.jsonPath().get("success").toString())).isTrue();
    }
    @Then("the item is not added to the list")
    public void itemIsNotAdded() {
        assertForbidden(response);
        Assertions.assertThat(Boolean.parseBoolean(response.jsonPath().get("success").toString())).isFalse();
    }
    @Then("the list is cleared")
    public void listIsCleared(){
        assertCreated(response);
    }
    @Then("the list is not cleared")
    public void listIsNotCleared(){
        assertNotFound(response);
    }
    @Then("the list is deleted")
    public void listIsDeleted(){
        assertCreated(response);
    }
    @Then("the list is not deleted")
    public void listIsNotDeleted(){
        assertNotFound(response);
    }
    @And("the status message is {string}")
    public void validateStatusMessage(String statusMessage) {
        Assertions.assertThat(response.jsonPath().get("status_message").toString()).isEqualTo(statusMessage);
    }

}
