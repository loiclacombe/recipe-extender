<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<stripes:layout-definition>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title><stripes:layout-component name="html_head_title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-top: 60px;
            padding-bottom: 40px;
        }
        .sidebar-nav {
        padding: 9px 0;
        }

    @media (max-width: 980px) {
    /* Enable use of floated navbar text */
    .navbar-text.pull-right {
      float: none;
      padding-left: 5px;
      padding-right: 5px;
    }
    }
    </style>
    <link href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">


    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/ico/apple-touch-icon-72-precomposed.png">
                <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/ico/apple-touch-icon-57-precomposed.png">
                               <link rel="shortcut icon" href="${pageContext.request.contextPath}/ico/favicon.png">
    </head>

    <html>
    <head>
        <title><stripes:layout-component name="html_head_title"/></title>
    </head>
    <body>

    <div class="container">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container-fluid">
              <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="brand" href="#"><fmt:message key="recipe.site.name"/></a>
              <div class="nav-collapse collapse">
                <p class="navbar-text pull-right">
                  Logged in as <a href="#" class="navbar-link">Username</a>
                </p>
                <ul class="nav">
                  <li class="active"><a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="recipe.site.menu.home"/></a></li>
                  <li class="active"><a href="${pageContext.request.contextPath}/input.jsp"><fmt:message key="recipe.site.menu.new.recipe"/></a></li>
                  <li><a href="${pageContext.request.contextPath}/about.jsp"><fmt:message key="recipe.site.menu.about"/></a></li>
                  <li><a href="${pageContext.request.contextPath}/contact.jsp"><fmt:message key="recipe.site.menu.contact"/></a></li>
                </ul>
              </div><!--/.nav-collapse -->
            </div>
        </div>
    </div>

    <div class="container-fluid">
    <stripes:layout-component name="contents"/>
    </div><!--/row-->

    <hr>

    <footer>
    <p><fmt:message key="recipe.site.copyright"/></p>
    </footer>

    </div><!--/.fluid-container-->
    </div><!--/div container-->

        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/lib/jquery/js/jquery-2.0.3.js"></script>
        <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
        <stripes:layout-component name="html_body_script"/>
    </body>
    </html>


</stripes:layout-definition>