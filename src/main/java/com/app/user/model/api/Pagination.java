package com.app.user.model.api;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a paginated search result.
 */
@Validated
public class Pagination   {
  @JsonProperty("offset")
  private Integer offset = null;

  @JsonProperty("limit")
  private Integer limit = null;

  @JsonProperty("total")
  private long total = 0;

  public Pagination offset(Integer offset) {
    this.offset = offset;
    return this;
  }

  /**
   * The start index of the result set, 0 based
   * @return offset
  **/
  @NotNull

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Pagination limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  /**
   * Size of the page in the result set
   * @return limit
  **/
  @NotNull

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Pagination total(long total) {
    this.total = total;
    return this;
  }

  /**
   * Total number of results in result set
   * @return total
  **/
  @NotNull

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pagination pagination = (Pagination) o;
    return Objects.equals(this.offset, pagination.offset) &&
        Objects.equals(this.limit, pagination.limit) &&
        Objects.equals(this.total, pagination.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(offset, limit, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pagination {\n");
    
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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
