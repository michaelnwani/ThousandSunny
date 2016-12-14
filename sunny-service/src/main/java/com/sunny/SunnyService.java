package com.sunny;

import com.ib.client.*;
import com.ib.contracts.OptContract;
import com.ib.contracts.StkContract;
import com.ib.controller.ApiConnection;
import com.ib.controller.ApiController;
import com.sunny.config.HelloWorldConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.List;

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
        // ApiController implements EReaderSignal, EClientSocket, EWrapper all off the bat.
        // Trader Workstation socket port should be set to 7497 (IB Gateway uses a different port, see github wiki)
        getApiController().connect("127.0.0.1", 7497, 0, null);
//        Contract contract = new Contract();
//        contract.symbol("AAPL");
//        contract.secType("OPT");
//        contract.currency("USD");
//        contract.exchange("SMART");

        StkContract contract = new StkContract("AAPL");

        List<ContractDetails> contractsDetails = Util.lookupContract(getApiController(), contract);
        System.out.println("contractsDetails="+contractsDetails);
        Thread.sleep(10000);
        System.out.println("Disconnecting:");
        getApiController().disconnect();
        new SunnyService().run(args);
    }
}
