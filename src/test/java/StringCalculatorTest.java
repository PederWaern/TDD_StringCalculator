import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringCalculatorTest {
		@Test
		public void ifNumbersIsEmptyThenReturnZero() throws Exception {
				StringCalculator actual = new StringCalculator();
				assertThat(actual.add(""), is(0));
		}

}