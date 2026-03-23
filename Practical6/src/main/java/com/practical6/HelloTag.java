package com.practical6;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

public class HelloTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print("Hello, World! from custom tag");
        } catch (IOException ex) {
            throw new JspException("Failed to render HelloTag", ex);
        }
        return SKIP_BODY;
    }
}
