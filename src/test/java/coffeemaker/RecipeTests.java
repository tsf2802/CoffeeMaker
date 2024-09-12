package coffeemaker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


import coffeemaker.domain.Recipe;

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
		// add tear down here
//		CuT = null;
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
	@DisplayName("setName edge case test")
    public void validtoString() {
		CuT.setName("standardDrink");
		assertEquals("Recipe{standardDrink}", CuT.toString());
    }
	

}
