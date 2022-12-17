package web.note.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorControlle implements ErrorController {

    @GetMapping(value = "/error")
    public String renderErrorPage(HttpServletRequest httpRequest) {
        Object status = httpRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {

            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error-403";
            }
        }

        return "failAdminPage";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
