package com.tukapai.lambda;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import jakarta.enterprise.context.ApplicationScoped;
import com.amazonaws.services.lambda.runtime.Context;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

@Named("main")
@ApplicationScoped
public class LambdaHandler implements RequestHandler<Map<String,String>, String> {

    @Inject
    DataSource dataSource;

    @Override
    public String handleRequest(Map<String,String> input, Context context) {

        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "200 OK";
    }

}


