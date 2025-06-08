package com.github.chiarelli.apiclientes.dtos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"page", "size", "length", "total_query_count", "total_pages", "content"})
public class PageCollectionJsonResponse<T> {

  private long length;

  @JsonProperty("page")
  private long currentPage;

  private long size;

  @JsonProperty("total_query_count")
  private long queryCount;
  
  @JsonProperty("total_pages")
  private long totalPages;
  
  @JsonProperty("content")
  private List<T> content;

  // Construtor padrão
  public PageCollectionJsonResponse() {}

  public PageCollectionJsonResponse(Page<T> pageable) {
    this.length = pageable.getTotalElements();
    this.currentPage = pageable.getNumber();
    this.size = pageable.getSize();
    this.queryCount = pageable.getTotalElements();
    this.totalPages = pageable.getTotalPages();
    setContent(pageable.getContent());
  }

  // Construtor com parâmetros
  public PageCollectionJsonResponse(
    long length, 
    long currentPage,
    long size,
    long queryCount, 
    long totalPages, 
    List<T> content
  ) {
    this.length = length;
    this.currentPage = currentPage;
    this.size = size;
    this.queryCount = queryCount;
    this.totalPages = totalPages;
    setContent(content);
  }

  public long getLength() {
    return length;
  }

  public void setLength(long length) {
    this.length = length;
  }

  public long getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(long currentPage) {
    this.currentPage = currentPage;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public long getQueryCount() {
    return queryCount;
  }

  public void setQueryCount(long queryCount) {
    this.queryCount = queryCount;
  }

  public long getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(long totalPages) {
    this.totalPages = totalPages;
  }

  public List<T> getContent() {
    if(content == null) {
      content = new ArrayList<>();
    }
    return content;
  }

  public void setContent(List<T> data) {
    Objects.requireNonNull(data);
    this.content = Collections.unmodifiableList(data);
  }

}
