package com.rm.demo.interceptor;

import com.rm.demo.dao.History;
import com.rm.demo.dao.HistoryRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private HistoryRepositories historyRepositories;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Date current = new Date();
        Timestamp ts = new Timestamp(current.getTime());
        History history = new History(request.getRequestURL().toString(), request.getQueryString(), ts, request.getRemoteAddr());
        historyRepositories.save(history);
        return true;
    }
}
