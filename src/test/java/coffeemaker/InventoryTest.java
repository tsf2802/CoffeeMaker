package coffeemaker;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import coffeemaker.domain.Inventory;
import coffeemaker.domain.Recipe;
import coffeemaker.exceptions.InventoryException;

public class InventoryTest {
    private Inventory CuT;

    @BeforeEach
    public void setUp() {
        CuT = new Inventory();
    }

    @AfterEach
    public void tearDown() {
        CuT = null;
    }

    /** Verify the correct instantiation of the Inventory object */
    @Test
    public void verifyConstructor() {
        assertAll("Should contain 15 units of each ingredient",
                () -> assertTrue(CuT.getCoffee() == 15),
                () -> assertTrue(CuT.getMilk() == 15),
                () -> assertTrue(CuT.getSugar() == 15),
                () -> assertTrue(CuT.getChocolate() == 15));
    }

    /**
     * Test that given a positive value for coffee, the amount of coffee in the
     * inventory is updated
     */
    @Test
    public void testAddCoffee() {
        CuT.addCoffee("5");
        assertEquals(20, CuT.getCoffee());
    }

    /**
     * Test that given a negative value or non-integer for coffee, an
     * InventoryException is thrown
     */
    @Test
    public void testAddCoffeeException() {
        Exception e = assertThrows(InventoryException.class, () -> CuT.addCoffee("-1"));
        assertTrue("Units of coffee must be a positive integer".equals(e.getMessage()));

        e = assertThrows(InventoryException.class, () -> CuT.addCoffee("a"));
        assertTrue("Units of coffee must be a positive integer".equals(e.getMessage()));
    }

    /**
     * Test that given a positive value for milk, the amount of milk in the
     * inventory is updated
     */
    @Test
    public void testAddMilk() {
        CuT.addMilk("5");
        assertEquals(20, CuT.getMilk());
    }

    /**
     * Test that given a negative value or non-integer for milk, an
     * InventoryException is thrown
     */
    @Test
    public void testAddMilkeException() {
        Exception e = assertThrows(InventoryException.class, () -> CuT.addMilk("-1"));
        assertTrue("Units of milk must be a positive integer".equals(e.getMessage()));

        e = assertThrows(InventoryException.class, () -> CuT.addMilk("a"));
        assertTrue("Units of milk must be a positive integer".equals(e.getMessage()));
    }

    /**
     * Test that given a positive value for sugar, the amount of sugar in the
     * inventory is updated
     */
    @Test
    public void testAddSugar() {
        CuT.addSugar("5");
        assertEquals(20, CuT.getSugar());
    }

    /**
     * Test that given a negative value or non-integer for sugar, an
     * InventoryException is thrown
     */
    @Test
    public void testAddSugarException() {
        Exception e = assertThrows(InventoryException.class, () -> CuT.addSugar("-1"));
        assertTrue("Units of sugar must be a positive integer".equals(e.getMessage()));

        e = assertThrows(InventoryException.class, () -> CuT.addSugar("a"));
        assertTrue("Units of sugar must be a positive integer".equals(e.getMessage()));
    }

    /**
     * Test that given a positive value for chocolate, the amount of chocolate in
     * the
     * inventory is updated
     */
    @Test
    public void testAddChocolate() {
        CuT.addChocolate("5");
        assertEquals(20, CuT.getChocolate());
    }

    /**
     * Test that given a negative value or non-integer for chocolate, an
     * InventoryException is thrown
     */
    @Test
    public void testAddChocolateException() {
        Exception e = assertThrows(InventoryException.class, () -> CuT.addChocolate("-1"));
        assertTrue("Units of chocolate must be a positive integer".equals(e.getMessage()));

        e = assertThrows(InventoryException.class, () -> CuT.addChocolate("a"));
        assertTrue("Units of chocolate must be a positive integer".equals(e.getMessage()));
    }

    /**
     * Test that enoughIngredients correctly verifies ingredient sufficiency.
     * Test indirectly using useIngredients.
     */
    @Test
    public void testEnoughIngredientsSufficient() {
        Recipe r = new Recipe();
        r.setAmtCoffee("5");
        r.setAmtMilk("5");
        r.setAmtSugar("5");
        r.setAmtChocolate("5");

        assertTrue(CuT.useIngredients(r), "Should be able to use ingredients");

        // Verify all ingredients have been reduced correctly
        assertAll("Inventory should be updated after use",
                () -> assertEquals(10, CuT.getCoffee()),
                () -> assertEquals(10, CuT.getMilk()),
                () -> assertEquals(10, CuT.getSugar()),
                () -> assertEquals(10, CuT.getChocolate()));
    }

    /**
     * Test that enoughIngredients fails when ingredients are insufficient.
     * Test indirectly using useIngredients.
     */
    @Test
    public void testEnoughIngredientsInsufficient() {
        Recipe r = new Recipe();
        r.setAmtCoffee("16");
        r.setAmtMilk("16");
        r.setAmtSugar("16");
        r.setAmtChocolate("16");

        assertFalse(CuT.useIngredients(r), "Should not be able to use ingredients due to insufficient inventory");

        // Verify inventory remains unchanged
        assertAll("Inventory should not change if ingredients are insufficient",
                () -> assertEquals(15, CuT.getCoffee()),
                () -> assertEquals(15, CuT.getMilk()),
                () -> assertEquals(15, CuT.getSugar()),
                () -> assertEquals(15, CuT.getChocolate()));
    }

    // /**
    // * Test that should remove the correct amounts of all ingredients from the
    // * inventory
    // */
    // @Test
    // public void testUseIngredients() {
    // Recipe r = new Recipe();
    // r.setAmtCoffee("5");
    // r.setAmtMilk("5");
    // r.setAmtSugar("5");
    // r.setAmtChocolate("5");

    // // Initial amount should be 15 for each ingredient as per the constructor
    // assertTrue(CuT.useIngredients(r), "Should be able to use ingredients");

    // // Check that the inventory has been updated
    // assertAll("Inventory should be updated",
    // () -> assertEquals(10, CuT.getCoffee()),
    // () -> assertEquals(10, CuT.getMilk()),
    // () -> assertEquals(10, CuT.getSugar()),
    // () -> assertEquals(10, CuT.getChocolate()));
    // }

    // @Test
    // public void testToString() {
    // ToStringVerifier.forClass(Inventory.class).verify();
    // }
    
    @Test
    public void testToString() {
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", CuT.toString());
    }
}