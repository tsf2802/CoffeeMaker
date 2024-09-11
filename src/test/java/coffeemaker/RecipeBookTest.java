package coffeemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import coffeemaker.domain.Recipe;
import coffeemaker.domain.RecipeBook;

public class RecipeBookTest {

    private RecipeBook CuT;

    @BeforeEach
    public void setUp(){
        this.CuT = new RecipeBook();

        // Initialize recipe book with [1, 2, 3, null]
        for(int i = 0; i < 3; i++){
            Recipe r = new Recipe();
            r.setName("recipe" + i);
            this.CuT.addRecipe(r);
        }
    }

    @Test
    public void testDeleteRecipe(){
        CuT.deleteRecipe((0));
        Recipe[] recipes = CuT.getRecipes();
        assertEquals(null, recipes[0], "Deletion of a recipe should leave a null item in the array");
    }

    @Test
    public void testReplaceRecipe(){
        Recipe r = new Recipe();
        String s = "replacementName";
        r.setName(s);
        CuT.replaceRecipe(0, r);

        Recipe[] recipes  = CuT.getRecipes();
        assertEquals(s, recipes[0].getName());
    }
    
}
