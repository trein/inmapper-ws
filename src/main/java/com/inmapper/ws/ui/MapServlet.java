package com.inmapper.ws.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapServlet extends HttpServlet {
    
    private static final long serialVersionUID = 5454176246970942149L;
    private static final Logger LOGGER = LoggerFactory.getLogger(MapServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomId = request.getRequestURI().replace("/web/", "");
        
        LOGGER.debug("Received map request for room id [{}]", roomId);
        
        request.setAttribute("room_id", roomId);
        request.getRequestDispatcher("/pages/map.jsp").forward(request, response);
    }
    
}
