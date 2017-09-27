import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringCalculatorTest {

		private StringCalculator actual;

		@Before
		public void setUp() throws Exception {
				actual = new StringCalculator();
		}

		@Test
		public void ifNumbersIsEmptyThenReturnZero() throws Exception {
				assertThat(actual.add(""), is(0));
		}

		@Test
		public void ifNumbersIsOneThenReturnOne() throws Exception {
				assertThat(actual.add("1"), is(1));
		}

		@Test
		public void ifNumbersIsTwoThenReturnTwo() throws Exception {
				assertThat(actual.add("2"), is(2));
		}

		@Test
		public void canHandleSeveralNumbersSeparatedByComma() throws Exception {
				assertThat(actual.add("1,2"), is(3));
				assertThat(actual.add("1,2,2,3"), is(8));
		}

		@Test (expected = IllegalArgumentException.class)
		public void nonNumberCharachterThrowsException() throws Exception {
				actual.add("1,2,X");
		}
}