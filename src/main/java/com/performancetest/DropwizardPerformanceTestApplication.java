package com.performancetest;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.performancetest.configuration.DropwizardConfiguration;
import com.performancetest.resources.HelloWorldResource;
import com.performancetest.health.TemplateHealthCheck;

public class DropwizardPerformanceTestApplication extends Application<DropwizardConfiguration> {
    public static void main(String[] args) throws Exception {
        new DropwizardPerformanceTestApplication().run(new String[]{"server", System.getProperty("dropwizard.config")});
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DropwizardConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DropwizardConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
        configuration.getTemplate(),
        configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
        new TemplateHealthCheck(configuration.getTemplate());
        
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        
    }

}