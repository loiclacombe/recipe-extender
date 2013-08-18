package net.damaki.recipe.web.actions;

import com.google.common.base.Objects;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 23:42
 */
public class InputRecipeActionBean implements ActionBean {
    private static final Logger LOG = LoggerFactory.getLogger(InputRecipeActionBean.class);

    private ActionBeanContext context;

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    @DefaultHandler
    public Resolution addition() {
        LOG.info("Received recipe: {}", this);
        return new ForwardResolution("/ok.jsp");
    }

    @Validate(required=true)
    private String name;
    @Validate(required=true)
    private String description;
    @Validate(required=true)
    private String steps;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("context", context)
                .add("name", name)
                .add("description", description)
                .add("steps", steps)
                .toString();
    }
}
