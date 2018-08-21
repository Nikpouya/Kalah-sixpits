package com.kalahgame.sixpits.controller.handler;

import com.kalahgame.sixpits.exceptions.Error.RestErrorInfo;
import com.kalahgame.sixpits.exceptions.InvalidGameException;
import com.kalahgame.sixpits.exceptions.InvalidIdentifierException;
import com.kalahgame.sixpits.exceptions.InvalidMovementException;
import com.kalahgame.sixpits.exceptions.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.zip.DataFormatException;

public abstract class AbstractionRestHandler implements ApplicationEventPublisherAware {

    private final static Logger logger = Logger.getLogger(AbstractionRestHandler.class);
    protected ApplicationEventPublisher eventPublisher;


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataFormatException.class)
    public
    @ResponseBody
    RestErrorInfo handleDataStoreException(DataFormatException ex, WebRequest request, HttpServletResponse response) {
        logger.error("Converting Data Store exception to RestResponse : " + ex.getMessage());
        return new RestErrorInfo(ex, "Data Format is not compatible.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public
    @ResponseBody
    RestErrorInfo handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, HttpServletResponse response) {
        logger.error("ResourceNotFoundException handler:" + ex.getMessage());
        return new RestErrorInfo(ex, "Resource Not Found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidIdentifierException.class)
    public
    @ResponseBody
    RestErrorInfo handleInvalidIdentifierException(InvalidIdentifierException ex, WebRequest request, HttpServletResponse response) {
        logger.error("InvalidIdentifierException handler:" + ex.getMessage());
        return new RestErrorInfo(ex, "Invalid Identifier");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidMovementException.class)
    public
    @ResponseBody
    RestErrorInfo handleInvalidMovementException(InvalidMovementException ex, WebRequest request, HttpServletResponse response) {
        logger.error("InvalidMovementException handler:" + ex.getMessage());
        return new RestErrorInfo(ex, "Invalid Movement");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidGameException.class)
    public
    @ResponseBody
    RestErrorInfo handleInvalidGameException(InvalidGameException ex, WebRequest request, HttpServletResponse response) {
        logger.error("InvalidGameException handler:" + ex.getMessage());
        return new RestErrorInfo(ex, "Invalid Game");
    }

    @ExceptionHandler(RuntimeException.class)
    public
    @ResponseBody
    RestErrorInfo handleRuntimeException(RuntimeException ex, WebRequest request, HttpServletResponse response) {
        logger.error("RuntimeException handler:" + ex.getMessage());
        return new RestErrorInfo(ex, "A game play problem occurred.");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

}

