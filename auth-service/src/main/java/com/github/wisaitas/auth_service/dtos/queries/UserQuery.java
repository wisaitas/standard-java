package com.github.wisaitas.auth_service.dtos.queries;

import com.github.wisaitas.sharelib.dtos.queries.PaginationQuery;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserQuery extends PaginationQuery {
    private String fullname;

}
