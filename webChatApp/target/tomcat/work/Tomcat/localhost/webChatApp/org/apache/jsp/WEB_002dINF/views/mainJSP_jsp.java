/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-04-27 01:43:23 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class mainJSP_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Chat</title>\r\n");
      out.write("    <style>\r\n");
      out.write("        textarea#message {\r\n");
      out.write("            width: 485px;\r\n");
      out.write("            height: 50px;\r\n");
      out.write("            border: 1px solid #cccccc;\r\n");
      out.write("            padding: 5px;\r\n");
      out.write("            font-family: Tahoma, sans-serif;\r\n");
      out.write("            resize: none;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .scrolling-messages {\r\n");
      out.write("            height: 400px;\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            overflow-y: auto;\r\n");
      out.write("            overflow-x: auto;\r\n");
      out.write("            border-bottom: 1px solid #cccccc;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .message-section {\r\n");
      out.write("            height: 95px;\r\n");
      out.write("            width: 100%;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .main {\r\n");
      out.write("            display: flex;\r\n");
      out.write("            flex-direction: column;\r\n");
      out.write("            max-width: 500px;\r\n");
      out.write("            max-height: 570px;\r\n");
      out.write("            border: 3px solid #cccccc;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .message-label {\r\n");
      out.write("            height: 20px;\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            font-size: 12px;\r\n");
      out.write("            font-family: Tahoma, sans-serif;\r\n");
      out.write("            padding-bottom: 2px;\r\n");
      out.write("            padding-top: 2px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .button {\r\n");
      out.write("            background-color: #4CAF50;\r\n");
      out.write("            border: none;\r\n");
      out.write("            color: white;\r\n");
      out.write("            text-align: center;\r\n");
      out.write("            display: inline-block;\r\n");
      out.write("            font-size: 12px;\r\n");
      out.write("            cursor: pointer;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .exitButton {\r\n");
      out.write("            background-color: #4CAF50;\r\n");
      out.write("            border: none;\r\n");
      out.write("            color: white;\r\n");
      out.write("            text-align: center;\r\n");
      out.write("            display: inline-block;\r\n");
      out.write("            font-size: 12px;\r\n");
      out.write("            cursor: pointer;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        p {\r\n");
      out.write("            width: 485px;\r\n");
      out.write("            word-wrap: break-word;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("    <script>\r\n");
      out.write("        let chatUnit = {\r\n");
      out.write("            init() {\r\n");
      out.write("                this.regbox = document.querySelector(\".reg\");\r\n");
      out.write("                this.chatbox = document.querySelector(\".main\");\r\n");
      out.write("\r\n");
      out.write("                this.regBtn = this.regbox.querySelector(\"button\");\r\n");
      out.write("                this.nameInput = this.regbox.querySelector(\".username\")\r\n");
      out.write("                this.bindEvent();\r\n");
      out.write("            },\r\n");
      out.write("\r\n");
      out.write("            bindEvent() {\r\n");
      out.write("                this.regBtn.addEventListener(\"click\", e=>this.openSocket())\r\n");
      out.write("            },\r\n");
      out.write("\r\n");
      out.write("            openSocket() {\r\n");
      out.write("                this.ws = new WebSocket(\"ws://localhost:8080/webChatApp/chat\");\r\n");
      out.write("                this.name = this.nameInput.value;\r\n");
      out.write("                this.regbox.style.display=\"none\";\r\n");
      out.write("                this.chatbox.style.display=\"block\";\r\n");
      out.write("            }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        };\r\n");
      out.write("\r\n");
      out.write("        window.addEventListener(\"load\", e=>chatUnit.init());\r\n");
      out.write("    </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"welcome\"><h1>Welcome use chat!!)))</h1></div>\r\n");
      out.write("<div id=\"bye\" style=\"display: none\"><h1>Good bye!!)))</h1></div>\r\n");
      out.write("<div id=\"reg\" class=\"reg\" style=\"display: block\">\r\n");
      out.write("    <h3>Choose your role and enter you name:</h3>\r\n");
      out.write("    <div id=\"form\" class=\"helloForm\">\r\n");
      out.write("        <h4>Enter you name:</h4>\r\n");
      out.write("        <input id=\"username\" name=\"username\" type=\"text\" placeholder=\"enter you name...\" maxlength=\"14\" value=\"guest\">\r\n");
      out.write("        <h4>You client:</h4>\r\n");
      out.write("        <input id=\"client\" type=\"radio\" name=\"user\"><br>\r\n");
      out.write("        <h4>You agent:</h4>\r\n");
      out.write("        <input id=\"agent\" type=\"radio\" name=\"user\"><br>\r\n");
      out.write("        <input id=\"send\" type=\"button\" name=\"send\" value=\"send\">\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"main\" class=\"main\" style=\"display: none\">\r\n");
      out.write("    <div id=\"scrolling-messages\" class=\"scrolling-messages\"></div>\r\n");
      out.write("    <div class=\"message-label\">\r\n");
      out.write("        <span>Enter message:</span>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"message-section\">\r\n");
      out.write("        <div>\r\n");
      out.write("            <textarea id=\"message\"></textarea>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div style=\"float: right\">\r\n");
      out.write("            <input type=\"button\" value=\"submit\" onclick=\"sendMessage();\"\r\n");
      out.write("                   class=\"button\" />\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div>\r\n");
      out.write("        <input type=\"button\" value=\"exit\" onclick=\"closeSession();\" class=\"exitButton\" />\r\n");
      out.write("        <input type=\"button\" value=\"leave\" onclick=\"leave();\" class=\"button\" />\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
