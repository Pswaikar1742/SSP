package com.practical6;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

public class AddTag extends TagSupport {
    private String num1;
    private String num2;
    private String description;

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            int a = 0;
            int b = 0;
            try {
                if (num1 != null) a = Integer.parseInt(num1.trim());
            } catch (NumberFormatException e) {
                // ignore and keep 0
            }
            try {
                if (num2 != null) b = Integer.parseInt(num2.trim());
            } catch (NumberFormatException e) {
                // ignore and keep 0
            }
            int sum = a + b;
            if (description != null && !description.isEmpty()) {
                out.print(description + " ");
            }
            out.print(sum);
        } catch (IOException ex) {
            throw new JspException("Failed to render AddTag", ex);
        }
        return SKIP_BODY;
    }
}
