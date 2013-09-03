package net.damaki.recipe.web.actions;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 19/08/13
 * Time: 16:59
 */
public enum Errors {
    PERSISTENCE_ERROR(50010);

    Errors(int code) {
        this.code = code;
    }

    public final int code;
}
