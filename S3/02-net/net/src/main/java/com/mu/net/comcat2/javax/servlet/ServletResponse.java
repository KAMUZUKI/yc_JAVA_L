package com.mu.net.comcat2.javax.servlet;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author : MUZUKI
 * @date : 2023-01-06 19:52
 **/

public interface ServletResponse {
    public void send();

    public OutputStream getOutputStream();

    public PrintWriter getWriter();
}
