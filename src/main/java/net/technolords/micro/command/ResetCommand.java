package net.technolords.micro.command;

import java.net.HttpURLConnection;

import org.eclipse.jetty.server.handler.StatisticsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.technolords.micro.ResponseContext;
import net.technolords.micro.registry.MockRegistry;

public class ResetCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatsCommand.class);
    private static StatisticsHandler statisticsHandler;

    public static ResponseContext executeCommand() {
        LOGGER.debug("Reset command called...");
        ResponseContext responseContext = new ResponseContext();
        responseContext.setContentType(ResponseContext.PLAIN_TEXT_CONTENT_TYPE);
        if (statisticsHandler == null) {
            statisticsHandler = MockRegistry.findStatisticsHandler();
        }
        if (statisticsHandler != null) {
            statisticsHandler.statsReset();
            responseContext.setResponse("Statistics has been reset");
        } else {
            responseContext.setResponse("Unable to retrieve statistics (no handler configured)");
            responseContext.setErrorCode(String.valueOf(HttpURLConnection.HTTP_NOT_FOUND));
        }
        return responseContext;
    }
}