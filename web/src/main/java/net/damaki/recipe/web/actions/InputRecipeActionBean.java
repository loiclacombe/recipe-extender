package net.damaki.recipe.web.actions;

import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import net.damaki.recipe.storage.PersistService;
import net.damaki.recipe.storage.PersistenceException;
import net.damaki.recipe.storage.beans.Recipe;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 23:42
 */
public class InputRecipeActionBean implements ActionBean {
    private static final Logger LOG = LoggerFactory.getLogger(InputRecipeActionBean.class);

    private ActionBeanContext context;

    private PersistService persistService;

    @Inject
    public InputRecipeActionBean(PersistService persistService) {
        this.persistService = persistService;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    @DefaultHandler
    public Resolution persistNewRecipe() {

        Recipe recipe = parseRecipe();
        try {
            persistService.persist(recipe);
            LOG.info("Received recipe: {}", this);
            return new ForwardResolution("/ok.jsp");
        } catch (PersistenceException e) {
            LOG.error(e.getMessage(), e);
            return new ErrorResolution(Errors.PERSISTENCE_ERROR.code);
        }
    }

    private Recipe parseRecipe() {
        Recipe recipe = new Recipe();
        recipe.setDescription(getDescription());
        recipe.setName(getName());

        recipe.getIngredients().addAll(splitLines(getIngredients()));
        recipe.getSteps().addAll(splitLines(getSteps()));

        return recipe;
    }

    private List<String> splitLines(String text) {
        Splitter lineSplitter = Splitter.onPattern("\r?\n");

        return Lists.newArrayList(lineSplitter.split(text));
    }


    @Validate(required = true)
    private String name;
    @Validate(required = true)
    private String description;
    @Validate(required = true)
    private String ingredients;
    @Validate(required = true)
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
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
