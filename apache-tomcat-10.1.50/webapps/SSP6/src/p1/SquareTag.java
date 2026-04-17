package p1;

import jakarta.servlet.jsp.tagext.TagSupport;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import java.io.IOException;

public class SquareTag extends TagSupport {
    private int number;
    public void setNumber(int number) { this.number = number; }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            int square = number * number;
            out.println("Square of a number is " + square);
        } catch(IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
