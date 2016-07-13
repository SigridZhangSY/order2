package com.thoughtworks.api.support;

import com.thoughtworks.api.web.jersey.CORSResponseFilter;
import com.thoughtworks.api.web.jersey.OpenSessionInViewRequestFilter;
import com.thoughtworks.api.web.jersey.OpenSessionInViewResponseFilter;
import com.thoughtworks.api.web.jersey.RoutesFeature;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class ApiForTest extends ResourceConfig {
    public ApiForTest() {
        property(org.glassfish.jersey.server.ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
        packages("com.thoughtworks.api.web");
        register(RoutesFeature.class);
        register(LoggingFilter.class);
        register(CORSResponseFilter.class);
        register(OpenSessionInViewRequestFilter.class);
        register(OpenSessionInViewResponseFilter.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
            }
        });
    }
}
