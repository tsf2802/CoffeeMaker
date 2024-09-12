package coffeemaker;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
     * Test that given a negative value for sugar, an InventoryException is thrown
     */
    @Test
    public void testAddSugarNegativeValue() {
        Exception e = assertThrows(InventoryException.class, () -> CuT.addSugar("-1"));
        assertTrue("Units of sugar must be a positive integer".equals(e.getMessage()));
    }

    /**
     * Test that should remove the correct amounts of all ingredients from the
     * inventory
     */
    @Test
    public void testUseIngredients() {
        Recipe r = new Recipe();
        r.setAmtCoffee("5");
        r.setAmtMilk("5");
        r.setAmtSugar("5");
        r.setAmtChocolate("5");

        // Initial amount should be 15 for each ingredient as per the constructor
        assertTrue(CuT.useIngredients(r), "Should be able to use ingredients");

        // Check that the inventory has been updated
        assertAll("Inventory should be updated",
                () -> assertEquals(10, CuT.getCoffee()),
                () -> assertEquals(10, CuT.getMilk()),
                () -> assertEquals(10, CuT.getSugar()),
                () -> assertEquals(10, CuT.getChocolate()));
    }
}
