package ng.com.workshop.web.advice;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ng.com.workshop.exception.AjaxException;
import ng.com.workshop.exception.RecordNotFoundException;


@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(AjaxException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody Map<String, Object> handleException(AjaxException ex) {
        ModelMap model = new ModelMap();
        model.addAttribute("message", ex.getMessage());
        return model;
    }


    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody Map<String, Object> handleException(RecordNotFoundException ex) {
        return Collections.singletonMap("message", ex.getMessage());
    }
}
