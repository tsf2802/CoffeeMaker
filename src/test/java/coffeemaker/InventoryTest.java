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
     * Test that given a negative value for coffee, it should not update the amount
     * of coffee in the inventory
     */
    @Test
    public void testSetCoffee_Negative() {
        CuT.setCoffee(-1);
        assertEquals(15, CuT.getCoffee());
    }

    /**
     * Test that given a negative value for milk, it should not update the amount of
     * milk in the inventory
     */
    @Test
    public void testSetMilk_Negative() {
        CuT.setMilk(-1);
        assertEquals(15, CuT.getMilk());
    }

    /**
     * Test that given a negative value for sugar, it should not update the amount
     * of sugar in the inventory
     */
    @Test
    public void testSetSugar_Negative() {
        CuT.setSugar(-1);
        assertEquals(15, CuT.getSugar());
    }

    /**
     * Test that given a negative value for chocolate, it should not update the
     * amount of chocolate in the inventory
     */
    @Test
    public void testSetChocolate_Negative() {
        CuT.setChocolate(-1);
        assertEquals(15, CuT.getChocolate());
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
     * Test that given a negative value for coffee, an
     * InventoryException is thrown
     */
    @Test
    public void testAddCoffee_Negative() {
        Exception e = assertThrows(InventoryException.class, () -> CuT.addCoffee("-1"));
        assertTrue("Units of coffee must be a positive integer".equals(e.getMessage()));
    }

    /**
     * Test that given a non-integer value for coffee, an
     * InventoryException is thrown
     */
    @Test
    public void testAddCoffee_NonInteger() {
        Exception e = assertThrows(InventoryException.class, () -> CuT.addCoffee("a"));
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
     * Test that given a negative value for milk, an
     * InventoryException is thrown
     */
    @Test
    public void testAddMilk_Negative() {
        Exception e = assertThrows(InventoryException.class, () -> CuT.addMilk("-1"));
        assertTrue("Units of milk must be a positive integer".equals(e.getMessage()));
    }

    /**
     * Test that given a non-integer value for milk, an
     * InventoryException is thrown
     */
    @Test
    public void testAddMilk_NonInteger() {
        Exception e = assertThrows(InventoryException.class, () -> CuT.addMilk("a"));
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
    public void testAddSugar_Negative() {
        Exception e = assertThrows(InventoryException.class, () -> CuT.addSugar("-1"));
        assertTrue("Units of sugar must be a positive integer".equals(e.getMessage()));
    }

    /**
     * Test that given a non-integer value for sugar, an
     * InventoryException is thrown
     */
    @Test
    public void testAddSugar_NonInteger() {
        Exception e = assertThrows(InventoryException.class, () -> CuT.addSugar("a"));
        assertTrue("Units of sugar must be a positive integer".equals(e.getMessage()));
    }

    /**
     * Test that given a positive value for chocolate, the amount of chocolate in
     * the inventory is updated
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
    public void testAddChocolate_Negative() {
        Exception e = assertThrows(InventoryException.class, () -> CuT.addChocolate("-1"));
        assertTrue("Units of chocolate must be a positive integer".equals(e.getMessage()));
    }

    /**
     * Test that given a non-integer value for chocolate, an
     * InventoryException is thrown
     */
    @Test
    public void testAddChocolate_NonInteger() {
        Exception e = assertThrows(InventoryException.class, () -> CuT.addChocolate("a"));
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
     * Test that enoughIngredients fails when insufficient amount of coffee
     * Test indirectly using useIngredients.
     */
    @Test
    public void testEnoughIngredients_InsufficientCoffee() {
        Recipe r = new Recipe();
        r.setAmtCoffee("16");
        r.setAmtMilk("5");
        r.setAmtSugar("5");
        r.setAmtChocolate("5");

        assertFalse(CuT.useIngredients(r),
                "Should not be able to use ingredients due to insufficient amount of coffee");

        // Verify inventory remains unchanged
        assertAll("Inventory should not change if ingredients are insufficient",
                () -> assertEquals(15, CuT.getCoffee()),
                () -> assertEquals(15, CuT.getMilk()),
                () -> assertEquals(15, CuT.getSugar()),
                () -> assertEquals(15, CuT.getChocolate()));
    }

    /**
     * Test that enoughIngredients fails when insufficient amount of milk
     * Test indirectly using useIngredients.
     */
    @Test
    public void testEnoughIngredients_InsufficientMilk() {
        Recipe r = new Recipe();
        r.setAmtCoffee("5");
        r.setAmtMilk("16");
        r.setAmtSugar("5");
        r.setAmtChocolate("5");

        assertFalse(CuT.useIngredients(r), "Should not be able to use ingredients due to insufficient amount of milk");

        // Verify inventory remains unchanged
        assertAll("Inventory should not change if ingredients are insufficient",
                () -> assertEquals(15, CuT.getCoffee()),
                () -> assertEquals(15, CuT.getMilk()),
                () -> assertEquals(15, CuT.getSugar()),
                () -> assertEquals(15, CuT.getChocolate()));
    }

    /**
     * Test that enoughIngredients fails when insufficient amount of sugar
     * Test indirectly using useIngredients.
     */
    @Test
    public void testEnoughIngredients_InsufficientSugar() {
        Recipe r = new Recipe();
        r.setAmtCoffee("5");
        r.setAmtMilk("5");
        r.setAmtSugar("16");
        r.setAmtChocolate("5");

        assertFalse(CuT.useIngredients(r), "Should not be able to use ingredients due to insufficient amount of sugar");

        // Verify inventory remains unchanged
        assertAll("Inventory should not change if ingredients are insufficient",
                () -> assertEquals(15, CuT.getCoffee()),
                () -> assertEquals(15, CuT.getMilk()),
                () -> assertEquals(15, CuT.getSugar()),
                () -> assertEquals(15, CuT.getChocolate()));
    }

    /**
     * Test that enoughIngredients fails when insufficient amount of chocolate
     * Test indirectly using useIngredients.
     */
    @Test
    public void testEnoughIngredients_InsufficientChocolate() {
        Recipe r = new Recipe();
        r.setAmtCoffee("5");
        r.setAmtMilk("5");
        r.setAmtSugar("5");
        r.setAmtChocolate("16");

        assertFalse(CuT.useIngredients(r),
                "Should not be able to use ingredients due to insufficient amount of chocolate");

        // Verify inventory remains unchanged
        assertAll("Inventory should not change if ingredients are insufficient",
                () -> assertEquals(15, CuT.getCoffee()),
                () -> assertEquals(15, CuT.getMilk()),
                () -> assertEquals(15, CuT.getSugar()),
                () -> assertEquals(15, CuT.getChocolate()));
    }

    // @Test
    // public void testToString() {
    // ToStringVerifier.forClass(Inventory.class).verify();
    // }

    @Test
    public void testToString() {
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", CuT.toString());
    }
}