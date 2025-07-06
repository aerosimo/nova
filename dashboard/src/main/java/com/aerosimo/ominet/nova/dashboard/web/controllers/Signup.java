/**************************************************************************************************
 * This piece of work is to enhance nova project functionality.                                   *
 *                                                                                                *
 * Author:    eomisore                                                                            *
 * File:      Signup.java                                                                         *
 * Created:   21/04/2025, 17:27                                                                   *
 * Modified:  21/04/2025, 17:27                                                                   *
 *                                                                                                *
 * Copyright (c)  2025.  Aerosimo Ltd                                                             *
 *                                                                                                *
 * Permission is hereby granted, free of charge, to any person obtaining a                        *
 * copy of this software and associated documentation files (the "Software"),                     *
 * to deal in the Software without restriction, including without limitation                      *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,                       *
 * and/or sell copies of the Software, and to permit persons to whom the                          *
 * Software is furnished to do so, subject to the following conditions:                           *
 *                                                                                                *
 * The above copyright notice and this permission notice shall be included                        *
 * in all copies or substantial portions of the Software.                                         *
 *                                                                                                *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,                                *
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES                                *
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND                                       *
 * NONINFINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT                                     *
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,                                   *
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING                                   *
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE                                     *
 * OR OTHER DEALINGS IN THE SOFTWARE.                                                             *
 *                                                                                                *
 **************************************************************************************************/

package com.aerosimo.ominet.nova.dashboard.web.controllers;

import com.aerosimo.ominet.nova.identity.dao.Authentication;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "signup",
        description = "A simple registration servlet to capture the information from registration form",
        value = "/signup")
public class Signup extends HttpServlet {
    private static final Logger log = LogManager.getLogger(Signup.class.getName());

    static String uname;
    static String pword;
    static String email;
    static String response;
    static String modifyby;
    static PrintWriter writer;
    static String html;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        uname = req.getParameter("username");
        email = req.getParameter("email");
        pword = req.getParameter("password");
        modifyby = "WebPortal";
        log.info("Preparing to register new user account for {}", uname);
        response = Authentication.createUser(uname,pword,email,modifyby);
        log.info("Logging response of Account registration {}", response);
        writer = resp.getWriter();
        html = "<html>";
        html += "<h2>Your username is: " + uname + "<br/>";
        html += "Your password is: " + pword + "<br/>";
        html += "<h2>Your email address is: " + email + "<br/>";
        html += "<br/>";
        html += "<br/>>";
        html += "<br/>>";
        html += "Your response : " + response + "</h2>";
        html += "</html>";
        writer.println(html);
    }
}