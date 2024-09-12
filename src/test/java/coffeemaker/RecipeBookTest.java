package coffeemaker;

import static org.junit.jupiter.api.Assertions.assertAll;
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

    /** Verify correct instantiation from constructor */
    @Test
    public void verifyConstructor(){
        assertAll("Should contain an array of recipes w/ the names 'recipe0', 'recipe1', 'recipe2', and a null object",
        () -> assertEquals("recipe0", CuT.getRecipes()[0].getName()),
        () -> assertEquals("recipe1", CuT.getRecipes()[1].getName()),
        () -> assertEquals("recipe2", CuT.getRecipes()[2].getName()),
        () -> assertEquals(null, CuT.getRecipes()[3])
        );
    }

    /** Adding a recipe with a duplicate name should return false - recipe was not added */
    @Test
    public void testAddRecipeDuplicateName(){
        Recipe r = new Recipe();
        r.setName("recipe2");
        assertEquals(false, CuT.addRecipe(r), "Recipe should not of been added, duplicate name is present");
    }

    /** Adding existing recipe should return false */
    @Test
    public void testAddRecipeDuplicateObject(){
        Recipe dup = new Recipe();
        dup.setName("dup");
        CuT.addRecipe(dup);
        assertEquals(false, CuT.addRecipe(dup), "Recipe should not of been added, object already in array");
    }

    /** Adding a new recipe should return true - recipe was added */
    @Test
    public void testAddRecipe(){
        Recipe r = new Recipe();
        r.setName("recipe3");
        assertEquals(true, CuT.addRecipe(r), "Recipe should of been added, no duplicates are present in the array");
    }

    /** Adding a null recipe should return false, there is no reason for a null object to be added */
    @Test
    public void testAddRecipeNull(){
        assertEquals(false, CuT.addRecipe(null));
    }


    /** Deleting a recipe should leave null in object array */
    @Test
    public void testDeleteRecipe(){
        CuT.deleteRecipe((0));
        Recipe[] recipes = CuT.getRecipes();
        assertEquals(null, recipes[0], "Deletion of a recipe should leave a null item in the array");
    }

    /** Deleting a recipe in an index with no null should return null */
    @Test
    public void testDeleteRecipeNullAtIndex(){
        assertEquals(null, CuT.deleteRecipe(3), "Requested index to delete at should be null");
    }

    /** Test deletion with an index that is out of bounds */
    @Test
    public void testDeleteRecipeOutOfBounds(){
        assertEquals(null, CuT.deleteRecipe(-1));
    }

    /** Test deletion with an index that is out of bounds */
    @Test
    public void testDeleteRecipeOutOfBounds1(){
        assertEquals(null, CuT.deleteRecipe(5));
    }

    /** New Recipe name should not be replaced */
    @Test
    public void testReplaceRecipe(){
        Recipe r = new Recipe();
        String s = "replacementName";
        r.setName(s);
        CuT.replaceRecipe(0, r);

        Recipe[] recipes  = CuT.getRecipes();
        assertEquals(s, recipes[0].getName());
    }

    /** 
     * [1, 2, 3, null]
     * 
     * Try to replace null w/ null
     * 
     * Replacing recipe at index should return null
     * The index contains a null object
    */
    @Test
    public void testReplaceRecipeNullPresent(){
        Recipe r = new Recipe();
        assertEquals(null, CuT.replaceRecipe(3, r), "There is no recipe located at index 3");
    }

    /** 
     * Replacing recipe at index with a null object should return null 
     * Object inserted is null
    */
    @Test
    public void testReplaceRecipeWithNullObject(){
        assertEquals(null, CuT.replaceRecipe(3, null), "Recipe cannot be replaced with null object");
    }
}
