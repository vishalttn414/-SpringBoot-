//package Practice.Project1.Services;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handle(Exception e) {
//        logger.error("Unhandled exception occurred: ", e);
//        return ResponseEntity.status(500).body("Error: " + e.getMessage());
//    }
//}
//
