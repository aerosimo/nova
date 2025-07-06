/**************************************************************************************************
 * This piece of work is to enhance nova project functionality.                                   *
 *                                                                                                *
 * Author:    eomisore                                                                            *
 * File:      LoginResult.java                                                                    *
 * Created:   19/04/2025, 22:42                                                                   *
 * Modified:  19/04/2025, 22:42                                                                   *
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

package com.aerosimo.ominet.nova.identity.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "LoginResult")
@XmlType(propOrder = {"account", "token", "response"})
public class LoginResult {

    private String account;
    private String token;
    private String response;

    public LoginResult(String account, String token, String response) {
        this.account = account;
        this.token = token;
        this.response = response;
    }

    public LoginResult() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "account='" + account + '\'' +
                "token='" + token + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}