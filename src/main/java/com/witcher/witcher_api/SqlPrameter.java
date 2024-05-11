package com.witcher.witcher_api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SqlPrameter {

    String sqlQuery;
    MapSqlParameterSource parameters;
}
