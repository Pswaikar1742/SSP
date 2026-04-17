package com.greenscale.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Custom tag that renders a colored health badge based on node status.
 */
public class HealthBadgeTag extends TagSupport {

    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            if ("Available".equalsIgnoreCase(status)) {
                out.print("<span style=\"color:#fff;background-color:#28a745;padding:2px 6px;border-radius:4px;\">Available</span>");
            } else if ("In-Use".equalsIgnoreCase(status)) {
                out.print("<span style=\"color:#fff;background-color:#dc3545;padding:2px 6px;border-radius:4px;\">In-Use</span>");
            } else {
                out.print("<span>" + (status != null ? status : "Unknown") + "</span>");
            }
        } catch (IOException e) {
            throw new JspException("Error rendering HealthBadge", e);
        }
        return SKIP_BODY;
    }
}
