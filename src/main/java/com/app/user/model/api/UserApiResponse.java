package com.app.user.model.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a response to a operation on the User object.
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-09-29T05:27:03.802Z[GMT]")
public class UserApiResponse   {
  @JsonProperty("statusCode")
  private Integer statusCode = null;

  @JsonProperty("statusMessage")
  private String statusMessage = null;

  @JsonProperty("pagination")
  private Pagination pagination = null;

  @JsonProperty("results")
  @Valid
  private List<User> results = new ArrayList<User>();

  @JsonProperty("errorMessages")
  @Valid
  private List<String> errorMessages = null;

  @JsonProperty("messages")
  @Valid
  private List<String> messages = null;

  public UserApiResponse statusCode(Integer statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  /**
   * Get statusCode
   * @return statusCode
  **/
  @NotNull

  public Integer getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public UserApiResponse statusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
    return this;
  }

  /**
   * Get statusMessage
   * @return statusMessage
  **/
  @NotNull

  public String getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  public UserApiResponse pagination(Pagination pagination) {
    this.pagination = pagination;
    return this;
  }

  /**
   * Get pagination
   * @return pagination
  **/
  @NotNull

  @Valid
  public Pagination getPagination() {
    return pagination;
  }

  public void setPagination(Pagination pagination) {
    this.pagination = pagination;
  }

  public UserApiResponse results(List<User> results) {
    this.results = results;
    return this;
  }

  public UserApiResponse addResultsItem(User resultsItem) {
    this.results.add(resultsItem);
    return this;
  }

  /**
   * Get results
   * @return results
  **/
  @NotNull
  @Valid
  public List<User> getResults() {
    return results;
  }

  public void setResults(List<User> results) {
    this.results = results;
  }

  public UserApiResponse errorMessages(List<String> errorMessages) {
    this.errorMessages = errorMessages;
    return this;
  }

  public UserApiResponse addErrorMessagesItem(String errorMessagesItem) {
    if (this.errorMessages == null) {
      this.errorMessages = new ArrayList<String>();
    }
    this.errorMessages.add(errorMessagesItem);
    return this;
  }

  /**
   * Get errorMessages
   * @return errorMessages
  **/

  public List<String> getErrorMessages() {
    return errorMessages;
  }

  public void setErrorMessages(List<String> errorMessages) {
    this.errorMessages = errorMessages;
  }

  public UserApiResponse messages(List<String> messages) {
    this.messages = messages;
    return this;
  }

  public UserApiResponse addMessagesItem(String messagesItem) {
    if (this.messages == null) {
      this.messages = new ArrayList<String>();
    }
    this.messages.add(messagesItem);
    return this;
  }

  /**
   * Get messages
   * @return messages
  **/

  public List<String> getMessages() {
    return messages;
  }

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserApiResponse userApiResponse = (UserApiResponse) o;
    return Objects.equals(this.statusCode, userApiResponse.statusCode) &&
        Objects.equals(this.statusMessage, userApiResponse.statusMessage) &&
        Objects.equals(this.pagination, userApiResponse.pagination) &&
        Objects.equals(this.results, userApiResponse.results) &&
        Objects.equals(this.errorMessages, userApiResponse.errorMessages) &&
        Objects.equals(this.messages, userApiResponse.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusCode, statusMessage, pagination, results, errorMessages, messages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserApiResponse {\n");
    
    sb.append("    statusCode: ").append(toIndentedString(statusCode)).append("\n");
    sb.append("    statusMessage: ").append(toIndentedString(statusMessage)).append("\n");
    sb.append("    pagination: ").append(toIndentedString(pagination)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
    sb.append("    errorMessages: ").append(toIndentedString(errorMessages)).append("\n");
    sb.append("    messages: ").append(toIndentedString(messages)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
