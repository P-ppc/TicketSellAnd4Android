/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2015-05-02 15:25:32 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.Page;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class jobManage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<script src=\"js/jquery-1.8.0.js\"></script>\r\n");
      out.write("<script src=\"js/jquery-easyui-1.3.5/jquery.easyui.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<link href=\"js/jquery-easyui-1.3.5/themes/default/easyui.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<link href=\"js/jquery-easyui-1.3.5/themes/icon.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<script src=\"js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jobManage.js\"></script>\r\n");
      out.write("<title>职位管理</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("  <!-- toolbar -->\r\n");
      out.write("  <div id=\"jobTb\">\r\n");
      out.write("    <a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\" onclick=\"openDialog_add()\">添加</a>\r\n");
      out.write("    <a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" plain=\"true\" onclick=\"Deletes()\">删除</a>\r\n");
      out.write("    <br>\r\n");
      out.write("    <table>\r\n");
      out.write("    <tr>\r\n");
      out.write("    <td>&nbsp;职位编号：<input type=\"text\" style=\"width:80px\" id=\"jobNumQuery\" />\r\n");
      out.write("    &nbsp;职位名称:<input type=\"text\" id=\"jobNameQuery\" style=\"width:80px\" />&nbsp;</td>\r\n");
      out.write("    <td><a href=\"#\" iconCls=\"icon-search\" class=\"easyui-linkbutton\" onclick=\"doSearch()\">查询</a></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("  <!-- 表格 -->\r\n");
      out.write("  <div id=\"jobDg\"></div>\r\n");
      out.write("  \r\n");
      out.write("  <!-- 添加 -->\r\n");
      out.write("  <div id=\"jobAdd\" icon=\"icon-save\"\r\n");
      out.write("\tstyle=\"padding: 5px; width: 500px; height: 300px;\">\r\n");
      out.write("\t<h5 id=\"jobAdd_message\" style=\"color: red;\"></h5>\r\n");
      out.write("\t<div class=\"ToolTip_Form\" id=\"table_jobAdd\" onkeydown=\"if(event.keyCode==13){jobAdd();}\">\r\n");
      out.write("        <ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<label>职位编号：</label>\r\n");
      out.write("\t\t\t\t<input type=\"text\" class=\"easyui-validatebox\" id=\"jobAdd_jobNum\" maxlength=\"8\" required=\"true\"></input>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<label>职位名称：</label>\r\n");
      out.write("\t\t\t\t<input type=\"text\" class=\"easyui-validatebox\" id=\"jobAdd_jobName\" maxlength=\"8\" required=\"true\"></input>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" icon=\"icon-ok\" onclick=\"jobAdd();\">提交</a>\r\n");
      out.write("\t\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" icon=\"icon-redo\" onclick=\"jobAddReset();\">重置</a>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- 编辑 -->\r\n");
      out.write("    <div id=\"jobEdit\" icon=\"icon-save\"\r\n");
      out.write("\tstyle=\"padding: 5px; width: 500px; height: 300px;\">\r\n");
      out.write("\t<h5 id=\"jobEdit_message\" style=\"color: red;\"></h5>\r\n");
      out.write("\t<div class=\"ToolTip_Form\" id=\"table_jobEdit\" onkeydown=\"if(event.keyCode==13){jobEdit();}\">\r\n");
      out.write("        <ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<label>职位编号：</label>\r\n");
      out.write("\t\t\t\t<input type=\"text\" class=\"easyui-validatebox\" id=\"jobEdit_jobNum\" maxlength=\"8\" required=\"true\"></input>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<label>职位名称：</label>\r\n");
      out.write("\t\t\t\t<input type=\"text\" class=\"easyui-validatebox\" id=\"jobEdit_jobName\" maxlength=\"8\" required=\"true\"></input>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" icon=\"icon-ok\" onclick=\"jobEdit();\">提交</a>\r\n");
      out.write("\t\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" icon=\"icon-redo\" onclick=\"jobEditReset();\">重置</a>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
