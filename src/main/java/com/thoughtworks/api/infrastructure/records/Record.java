package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.web.jersey.Routes;

import java.util.Map;

public interface Record {
    Map<String, Object> toRefJson(Routes routes);

    Map<String, Object> toJson(Routes routes);
}
