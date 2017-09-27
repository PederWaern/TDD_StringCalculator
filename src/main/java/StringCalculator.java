import java.util.Arrays;

public class StringCalculator {

		private static int CUSTOM_DELIMETER_INDEX = 2;
		private static String CUSTOM_DELIMITER_PATTERNSTART = "//";
		private static String DEFAULT_DELIMITER = ",";
		private static int CUSTOM_DELIMITER_PATTERNEND_INDEX = 4;
		private static final int MIN_CUSTOM_DELIMITER_INPUT_LENGTH = 5;

		public int add(String numbers) {
				if (numbers.isEmpty()) {
						return 0;
				}

				final String delimiter;
				final boolean hasValidCustomDelimiterPatternStart =
								numbers.length() > MIN_CUSTOM_DELIMITER_INPUT_LENGTH
												&& removeCustomDelimiterPatternStart(numbers, 0, 2)
														   .equals(CUSTOM_DELIMITER_PATTERNSTART);
				if (hasValidCustomDelimiterPatternStart) {
						delimiter = parseCustomDelimiter(numbers);
						numbers = removeCustomDelimiterPatternStart(numbers, CUSTOM_DELIMITER_PATTERNEND_INDEX, numbers.length());
				} else {
						delimiter = DEFAULT_DELIMITER;
				}

				String numbersWithNewLineReplaced = numbers.replaceAll("\n", delimiter);
				return Arrays.stream(numbersWithNewLineReplaced.split(delimiter))
								.mapToInt(Integer::parseInt)
                .boxed()
                .filter(number -> number >= 0)
								.mapToInt(num -> num)
								.sum();
		}


		private String removeCustomDelimiterPatternStart(String numbers, int customDelimiterPatternEndIndex, int length) {
				return numbers.substring(customDelimiterPatternEndIndex, length);
		}

		private String parseCustomDelimiter(String numbers) {
				return String.valueOf(numbers.charAt(CUSTOM_DELIMETER_INDEX));
		}
}

