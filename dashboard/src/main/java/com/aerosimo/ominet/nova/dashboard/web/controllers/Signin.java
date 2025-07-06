/**************************************************************************************************
 * This piece of work is to enhance nova project functionality.                                   *
 *                                                                                                *
 * Author:    eomisore                                                                            *
 * File:      Signin.java                                                                         *
 * Created:   21/04/2025, 17:30                                                                   *
 * Modified:  21/04/2025, 17:30                                                                   *
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
import com.aerosimo.ominet.nova.identity.dto.LoginResult;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "signin",
        description = "A simple login servlet to validate user credentials",
        value = "/signin")
public class Signin extends HttpServlet {

    private static final Logger log = LogManager.getLogger(Signin.class.getName());

    static String uname;
    static String pword;
    static HttpSession sess;
    static LoginResult response;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html; charset=UTF-8");
        sess = req.getSession();
        uname = req.getParameter("username-input");
        pword = req.getParameter("password-input");
        log.info("Preparing to sign in {}", uname);
        response = Authentication.loginUser(uname,pword);
        log.info("Logging response of sign in {}", response);
    }
}