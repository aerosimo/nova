/**************************************************************************************************
 * This piece of work is to enhance nova project functionality.                                   *
 *                                                                                                *
 * Author:    eomisore                                                                            *
 * File:      Authentication.java                                                                 *
 * Created:   19/04/2025, 21:23                                                                   *
 * Modified:  19/04/2025, 21:23                                                                   *
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

package com.aerosimo.ominet.nova.identity.dao;

import com.aerosimo.ominet.nova.core.config.Connect;
import com.aerosimo.ominet.nova.identity.dto.LoginResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class Authentication {
    private static final Logger log = LogManager.getLogger(Authentication.class.getName());
    static String response;
    static String token;
    static String account;
    static String sql;
    static Connection con = Connect.dbase();
    static CallableStatement stmt;

    public static String createUser(String username, String userpassword, String useremail, String modifyby){
        log.info("Preparing to register new user");
        try {
            sql = "{call account_pkg.CreateAccount(?,?,?,?,?)}";
            stmt = con.prepareCall(sql);
            stmt.setString(1, username);
            stmt.setString(2, userpassword);
            stmt.setString(3, useremail);
            stmt.setString(4, modifyby);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.execute();
            response = stmt.getString(5);
            log.info("Successfully register new user");
        } catch (SQLException err) {
            response = "Authentication.createUser attempt failed";
            log.error("Unknown error occurred in Authentication.createUser with the following - {}", Authentication.class.getName(), err);
        }
        return response;
    }

    public static LoginResult loginUser(String username, String userpassword){
        log.info("Preparing to validate and login user");
        try {
            sql = "{call authentication_pkg.loginUser(?,?,?,?,?)}";
            stmt = con.prepareCall(sql);
            stmt.setString(1, username);
            stmt.setString(2, userpassword);
            stmt.registerOutParameter(3, Types.VARCHAR);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.execute();
            account = stmt.getString(3);
            token = stmt.getString(4);
            response = stmt.getString(5);
        } catch (SQLException err) {
            account = null;
            token = null;
            response = "Authentication.loginUser attempt failed";
            log.error("Unknown error occurred in Authentication.loginUser with the following - {}", Authentication.class.getName(), err);
        }
        return new LoginResult(account,token,response);
    }
}