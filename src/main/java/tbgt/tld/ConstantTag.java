package tbgt.tld;

import org.springframework.web.util.ExpressionEvaluationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;




public class ConstantTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String sName;

    // Sets the variable to be put into session
    private String sVar;

    public int doStartTag() {

        JspWriter out = pageContext.getOut();

        String sConstant = "";

        if (sName.equalsIgnoreCase("ContextPath")) {
            sConstant = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
        } else if (sName.equalsIgnoreCase("USI")) {
            //todo..
        }

        if (!sConstant.equals("")) {
            try {
                if (sVar != null) {
                    pageContext.setAttribute(sVar, sConstant);
                } else {
                    out.print(sConstant);
                }
            } catch (Exception e) {
            }
        }

        return (SKIP_BODY);
    }

    /**
     * @return sName of the specified attribute
     */
    public String getName() {
        return sName;
    }

    /**
     * @param sName
     *                The attribute in UserSession to be displayed
     */
    public void setName(String sName) throws JspException {
        this.sName = ExpressionEvaluationUtils.evaluateString("name", sName, pageContext);
    }

    public String getVar() {
        return sVar;
    }

    public void setVar(String sVar) throws JspException {
        this.sVar =  ExpressionEvaluationUtils.evaluateString("var", sVar, pageContext);
    }
}
