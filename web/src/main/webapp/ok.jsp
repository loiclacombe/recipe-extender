<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head><title>Extend the recipes</title></head>
  <body>
    <h1>Recipe accepted !</h1>

    <stripes:form beanclass="net.damaki.recipe.web.actions.InputRecipeActionBean" focus="">
        <table>
            <tr>
                <td>Name:</td>
                <td><stripes:text name="name"/></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><stripes:text name="description"/></td>
            </tr>
            <tr>
                <td>Step 1:</td>
                <td><stripes:text name="step1"/></td>
            </tr>
        </table>
    </stripes:form>
  </body>
</html>