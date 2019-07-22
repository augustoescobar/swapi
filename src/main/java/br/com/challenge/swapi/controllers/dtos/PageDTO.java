package br.com.challenge.swapi.controllers.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class PageDTO<T> {

    private List<T> content;
    private Boolean last;
    private Boolean first;
    private Integer totalPages;
    private Long totalElements;
    private Integer size;
    private Integer number;
}
