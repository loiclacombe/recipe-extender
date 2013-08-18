<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<stripes:layout-render name="/WEB-INF/layouts/default.jsp">
    <stripes:layout-component name="html_head_title">
        Your new recipe
    </stripes:layout-component>
    <stripes:layout-component name="contents">
       <div class="span9">

            <stripes:form beanclass="net.damaki.recipe.web.actions.InputRecipeActionBean" focus="">

            <stripes:errors/>

              <fieldset>
                <legend><fmt:message key="recipe.input.legend"/></legend>

                <label><fmt:message key="recipe.input.name.label"/></label>
                <stripes:text name="name" value="Type something..."/>
                <span class="help-block"><fmt:message key="recipe.input.name.help"/></span>

                <label><fmt:message key="recipe.input.description.legend"/></label>
                <stripes:textarea name="description" value="Type something..."/>
                <span class="help-block"><fmt:message key="recipe.input.description.help"/></span>

                <label><fmt:message key="recipe.input.steps.legend"/></label>
                <stripes:textarea name="steps" value="Type something..."/>
                <span class="help-block"><fmt:message key="recipe.input.steps.help"/></span>

                 <stripes:submit class="btn" name="submit_recipe"><fmt:message key="recipe.input.button"/></stripes:submit>
                </fieldset>
            </stripes:form>
       </div>
    </stripes:layout-component>
</stripes:layout-render>

