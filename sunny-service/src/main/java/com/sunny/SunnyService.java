package com.sunny;

import com.ib.controller.ApiConnection;
import com.ib.controller.ApiController;
import com.sunny.config.HelloWorldConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import static java.lang.Thread.sleep;


public class SunnyService extends Application<HelloWorldConfiguration> {
    private static final ConnectionHandler connectionHandler = new ConnectionHandler();
    private static final Logger inLogger = new Logger();
    private static final Logger outLogger = new Logger();
    private static final ApiController apiController = new ApiController(connectionHandler, inLogger, outLogger);
    private final Bootstrap<HelloWorldConfiguration> bootstrap;

    private static class Logger implements ApiConnection.ILogger {
        @Override
        public void log(String valueOf) {
            if (valueOf.equals("_")) {
                System.out.println();
            } else {
                System.out.print(valueOf);
            }
        }
    }

    public SunnyService() {
        this.bootstrap = new Bootstrap<HelloWorldConfiguration>(this);
    }
    public static ApiController getApiController() {
        return apiController;
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) throws Exception {
    }

    public static void main(String[] args) throws Exception{
        System.out.println("Connecting:");
        getApiController().connect("127.0.0.1", 7496, 0);
        System.out.println("Disconnecting:");
        getApiController().disconnect();
        new SunnyService().run(args);
    }
}
