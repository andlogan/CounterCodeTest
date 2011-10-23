package com.codeTest.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

public class counterHttpServer extends HttpServlet {

    counterObject cObj = new counterObject();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pWriter = response.getWriter();
        pWriter.write("counter is " + cObj.getCount());

        response.setStatus(200);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int increment = 0;
        int decrement = 0;

        Enumeration paramNames = request.getParameterNames();
        ArrayList paramValues = new ArrayList();

        while (paramNames.hasMoreElements()) {
            paramValues.add(request.getParameter((String) paramNames.nextElement()));
        }
        for (int i = 0; i < paramValues.size(); i++) {
            if(paramValues.get(i).equals(request.getParameter("increment")))
                increment = Integer.valueOf(request.getParameter("increment"));
            if(paramValues.get(i).equals(request.getParameter("decrement")))
                decrement = Integer.valueOf(request.getParameter("decrement"));
        }
        int prevCount = cObj.getCount();
        if (increment != 0)
            cObj.incrementCount(increment);
        if (decrement != 0)
            cObj.decrementCount(decrement);
        int afterCount = cObj.getCount();

        PrintWriter pWriter = response.getWriter();
        pWriter.write("Initial count " + prevCount + "\n");
        pWriter.write("Final count " + afterCount);
        response.setStatus(201);
    }
}
