package net.damaki.recipe.storage.beans;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 15:47
 */
public class Recipe {
    private Recipe parent;
    private final Collection<Ingredient> ingredients = new ArrayList<>();
    private String id;
    private String name;
    private String description;
    private Date creationDate;
    private final Collection<String> steps = new ArrayList<>();
    private final Collection<String> tags = new ArrayList<>();

    private final String type = getClass().getCanonicalName();
    private final String objectVersion = "1.0.0";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Recipe getParent() {
        return parent;
    }

    public void setParent(Recipe parent) {
        this.parent = parent;
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<String> getSteps() {
        return steps;
    }

    public String getType() {
        return type;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getObjectVersion() {
        return objectVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (creationDate != null ? !creationDate.equals(recipe.creationDate) : recipe.creationDate != null)
            return false;
        if (description != null ? !description.equals(recipe.description) : recipe.description != null) return false;
        if (ingredients != null ? !ingredients.equals(recipe.ingredients) : recipe.ingredients != null) return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        if (objectVersion != null ? !objectVersion.equals(recipe.objectVersion) : recipe.objectVersion != null)
            return false;
        if (parent != null ? !parent.equals(recipe.parent) : recipe.parent != null) return false;
        if (steps != null ? !steps.equals(recipe.steps) : recipe.steps != null) return false;
        if (tags != null ? !tags.equals(recipe.tags) : recipe.tags != null) return false;
        if (type != null ? !type.equals(recipe.type) : recipe.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parent != null ? parent.hashCode() : 0;
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (steps != null ? steps.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (objectVersion != null ? objectVersion.hashCode() : 0);
        return result;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("parent", parent)
                .add("ingredients", ingredients)
                .add("name", name)
                .add("description", description)
                .add("creationDate", creationDate)
                .add("steps", steps)
                .add("tags", tags)
                .add("type", type)
                .toString();
    }
}
