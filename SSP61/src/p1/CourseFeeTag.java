package p1;

import jakarta.servlet.jsp.tagext.TagSupport;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import java.io.IOException;

public class CourseFeeTag extends TagSupport {
    private String courses; // expected comma-separated values like "Java:2500,DB:2000"

    public void setCourses(String courses) { this.courses = courses; }

    @Override
    public int doStartTag() throws JspException {
        double total = 0.0;
        if (courses != null && !courses.trim().isEmpty()) {
            String[] items = courses.split(",");
            for (String it : items) {
                String[] parts = it.split(":");
                if (parts.length == 2) {
                    try {
                        total += Double.parseDouble(parts[1]);
                    } catch (NumberFormatException e) {
                        // ignore malformed fee
                    }
                }
            }
        }
        // expose the computed total to the page
        pageContext.setAttribute("totalFee", total);
        try {
            JspWriter out = pageContext.getOut();
            out.println("<p><strong>Total Course Fee:</strong> ₹" + total + "</p>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
