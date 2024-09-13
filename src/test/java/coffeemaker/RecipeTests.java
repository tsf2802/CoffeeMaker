package coffeemaker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import static org.mockito.Mockito.*;
import coffeemaker.domain.Recipe;
import coffeemaker.domain.RecipeBook;
import coffeemaker.exceptions.RecipeException;
import org.junit.jupiter.api.Assertions;

public class RecipeTests {
    private Recipe mockingRecipe;
	private Recipe CuT;
	
	@BeforeEach
	public void setUp() {
		mockingRecipe = mock(Recipe.class);
		CuT = new Recipe();
		
	}

	@AfterEach
	public void tearDown() {
		CuT = null;
	}

	/***********************************************
	 * Constructor getter method functionality tests
	 ***********************************************/
	
	@Test
	@DisplayName("Get Name Invocation Test")
	public void testGetName() {
		when(mockingRecipe.getName()).thenReturn("getName invoked");
		String name = mockingRecipe.getName(); //should return getName invoked
		assertEquals("getName invoked", name);
		verify(mockingRecipe, times(1)).getName(); //making sure method is only called once as expected
	}

	@Test
	@DisplayName("Get Name Invocation Test")
	public void testGetPrice() {
		when(mockingRecipe.getPrice()).thenReturn(0);
		Integer name = mockingRecipe.getPrice(); //should return getName invoked
		assertEquals(0, name);
		verify(mockingRecipe, times(1)).getPrice(); //making sure method is only called once as expected
	}

	//redundant especially for getters, so not writing more

	/***********************************************
	 * Constructor (ctor) functionality tests
	 ***********************************************/	

	@Test
    public void verifyConstructor(){
        assertAll("Should contain empty string and 0 as initial values",
        () -> assertEquals("", CuT.getName()),
        () -> assertEquals(0, CuT.getPrice()),
        () -> assertEquals(0, CuT.getAmtCoffee()),
        () -> assertEquals(0, CuT.getAmtMilk()),
		() -> assertEquals(0, CuT.getAmtSugar()),
        () -> assertEquals(0, CuT.getAmtChocolate())
        );
    }

	/***********************************************
	 * setter tests
	 ***********************************************/	
	@Test
	@DisplayName("setName happy path test")
    public void setNameStandard() {
        // Test that amtChocolate is initialized to 0
		CuT.setName("newname");
        assertEquals("newname", CuT.getName());
    }
	@Test
	@DisplayName("setName alternative path test")
    public void setNameNull() {
		//name should not change
		CuT.setName(null);
        assertEquals("", CuT.getName());
    }

	@Test
	@DisplayName("setName edge case test")
    public void setNameEmptyString() {
        // name must have at least one non-blank charachter
		CuT.setName("newname");
		CuT.setName("");
        assertEquals("newname", CuT.getName());
    }

	/***********************************************
	 * misc tests. toString, hashCode, and Equals
	 ***********************************************/	

	@Test
	@DisplayName("toString test")
    public void validtoString() {
		CuT.setName("standardDrink");
		assertEquals("Recipe{standardDrink}", CuT.toString());
    }

	@Test
	@DisplayName("hashCode valid test")
    public void validhashCode() {
		CuT.setName("standardDrink");
		assertEquals(-2017642502, CuT.hashCode());
    }

	@Test
	@DisplayName("hashCode null name test")
    public void nullHashCode() throws Exception {
		Field nameField = Recipe.class.getDeclaredField("name");
		nameField.setAccessible(true);
		nameField.set(CuT, null); 
		assertEquals(31, CuT.hashCode());
    }

	@Test
	@DisplayName("Test when Objects names are equal")
    public void sameObjectEqualsName() {
		CuT.setName("standardDrink");
		Recipe compareObject = new Recipe();
		compareObject.setName("standardDrink");
		assertTrue(CuT.equals(compareObject));
    }

	@Test
	@DisplayName("Test when Objects are equal")
    public void sameObjectEquals() {
		assertTrue(CuT.equals(CuT));
    }

	@Test
	@DisplayName("Object input null")
    public void nullObjectEquals() {
		Recipe compareObject = null;
		assertFalse(CuT.equals(compareObject));
    }

	@Test
	@DisplayName("Object input is a different class")
    public void diffClassObjectEquals() {
		RecipeBook compareObject = new RecipeBook();
		assertFalse(CuT.equals(compareObject));
    }


	@Test
	@DisplayName("Original Name is null & other is named")
	public void testNullNameMismatch() throws Exception {
		 Recipe compareObject = new Recipe();
		 compareObject.setName("testName");
		 Field nameField = Recipe.class.getDeclaredField("name");
		 nameField.setAccessible(true);
		 nameField.set(CuT, null);  //reflection to set name field to null
		 boolean compareValue = CuT.equals(compareObject);
		 assertEquals(false, compareValue);
	}
	@Test
	@DisplayName("Original Name is null & other is also null")
	public void testNullNameMismatchAndOtherNull() throws Exception {
		 Recipe compareObject = new Recipe();
		 Field nameField = Recipe.class.getDeclaredField("name");
		 nameField.setAccessible(true);
		 nameField.set(CuT, null); 
		 nameField.set(compareObject,null); //reflection to set name field to null
		 boolean compareValue = CuT.equals(compareObject);
		 assertEquals(true, compareValue);
	}

	@Test
	@DisplayName("Test when Objects are equal")
    public void objectDiffNames() {
		CuT.setName("standardDrink");
		Recipe compareObject = new Recipe();
		compareObject.setName("unstandardDrink");
		assertFalse(CuT.equals(compareObject));
    }

	/***********************************************
	 * set type method tests
	 ***********************************************/	
	//setPrice
	@Test
	@DisplayName("Test standard price and larger than 0")
    public void setPriceHappyPath() {
		CuT.setPrice("10");
		assertEquals(10, CuT.getPrice());
	}

	@Test
	@DisplayName("Test negative price and error")
    public void setPriceNonPositive() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setPrice("-10"));
	}

	@Test
	@DisplayName("Test non numeric string and error")
    public void setPriceNonValid() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setPrice("gg"));
	}

	@Test
	@DisplayName("Test null input and error")
    public void setPriceNull() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setPrice(null));
	}
	//setAmtCoffee
	@Test
	@DisplayName("Test standard price and larger than 0")
    public void setCoffeeHappyPath() {
		CuT.setAmtCoffee("10");
		assertEquals(10, CuT.getAmtCoffee());
	}

	@Test
	@DisplayName("Test negative price and error")
    public void setCoffeeNonPositive() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setAmtCoffee("-10"));
	}

	@Test
	@DisplayName("Test non numeric string and error")
    public void setCoffeeNonValid() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setAmtCoffee("gg"));
	}

	@Test
	@DisplayName("Test null input and error")
    public void setCoffeeNull() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setAmtCoffee(null));
	}
	//setAmtChocolate
	@Test
	@DisplayName("Test standard price and larger than 0")
    public void setChocolateHappyPath() {
		CuT.setAmtChocolate("10");
		assertEquals(10, CuT.getAmtChocolate());
	}

	@Test
	@DisplayName("Test negative price and error")
    public void setChocolateNonPositive() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setAmtChocolate("-10"));
	}

	@Test
	@DisplayName("Test non numeric string and error")
    public void setChocolateNonValid() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setAmtChocolate("gg"));
	}

	@Test
	@DisplayName("Test null input and error")
    public void setChocolateNull() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setAmtChocolate(null));
	}
	//setAmtSugar
	@Test
	@DisplayName("Test standard price and larger than 0")
    public void setSugarHappyPath() {
		CuT.setAmtSugar("10");
		assertEquals(10, CuT.getAmtSugar());
	}

	@Test
	@DisplayName("Test negative price and error")
    public void setSugarNonPositive() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setAmtSugar("-10"));
	}

	@Test
	@DisplayName("Test non numeric string and error")
    public void setSugarNonValid() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setAmtSugar("gg"));
	}

	@Test
	@DisplayName("Test null input and error")
    public void setSugarNull() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setAmtSugar(null));
	}
	//SetAmtMilk Tests
	@Test
	@DisplayName("Test standard price and larger than 0")
    public void setMilkHappyPath() {
		CuT.setAmtMilk("10");
		assertEquals(10, CuT.getAmtMilk());
	}

	@Test
	@DisplayName("Test negative price and error")
    public void setMilkNonPositive() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setAmtMilk("-10"));
	}

	@Test
	@DisplayName("Test non numeric string and error")
    public void setMilkNonValid() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setAmtMilk("gg"));
	}

	@Test
	@DisplayName("Test null input and error")
    public void setMilkNull() {
		Assertions.assertThrows(RecipeException.class, () -> CuT.setAmtMilk(null));
	}
}
