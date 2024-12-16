package com.wg.banking;

import com.amazonaws.Request;
import com.amazonaws.Response;
import me.ccampo.spring.aws.lambda.SpringRequestHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainHandler extends SpringRequestHandler<Request, Response> {

    /**
     * Here we create the Spring ApplicationContext that will
     * be used throughout our application.
     */
    private static final ApplicationContext context =
            new AnnotationConfigApplicationContext(Application.class);

    @Override
    public ApplicationContext getApplicationContext() {
        return context;
    }
}