package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class calculatorTest {
	//add method :
	@Test
	void test_1() {
		calc obj=new calc();
		assertEquals(5, obj.add(6, -1));

	}
	@Test
	void test_2() {
		calc obj=new calc();
		assertEquals(6, obj.add(5, 1));

	}
	@Test
	void test_3() {
		calc obj=new calc();
		assertEquals(-7, obj.add(-2, -5));
	}
	@Test
	void test_4() {
		calc obj=new calc();
		assertEquals(3, obj.add(-2, 5));
	}

	//divide method :
	@Test
	void test_5() {
		calc obj=new calc();
		assertEquals(2, obj.divide(10, 1));

	}
	@Test
	void test_6() {
		calc obj=new calc();
		assertEquals(-3, obj.divide(-6, 2));
	}
	@Test
	void test_7() {
		calc obj=new calc();
		float t=(float)7/3;
		assertEquals(t, obj.divide(7, 3));

	}
	@Test
	void test_8() {
		calc obj=new calc();
		assertEquals(0, obj.divide(0, 8));

	}
	@Test
	void test_9() {
		calc obj=new calc();
		assertEquals(5.5, obj.divide(11, 2));

	}
	@Test
	void test_10() {
		calc obj=new calc();
		ArithmeticException thrown = assertThrows (ArithmeticException.class,() -> obj.divide(5,0));
		assertEquals("Error,Division by zero",thrown.getMessage());
	}
}
