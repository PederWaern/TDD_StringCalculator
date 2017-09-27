package string_calc;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StringCalculator {

	private static final int CUSTOM_DELIMETER_INDEX = 2;
	private static final String CUSTOM_DELIMITER_PATTERNSTART = "//";
	private static final String DEFAULT_DELIMITER = ",";
	private static final int CUSTOM_DELIMITER_PATTERNEND_INDEX = 4;
	private static final int MIN_CUSTOM_DELIMITER_INPUT_LENGTH = 5;

	private List<Integer> positiveNumbers;
	private List<Integer> negativeNumbers;
	private String numbersWithNewLineReplaced;
	private String delimiter;

	public int add(String numbers) {
		if (numbers.isEmpty() || numbers == null) {
			return 0;
		}

		boolean hasValidCustomDelimiterPatternStart = numbers.length() > MIN_CUSTOM_DELIMITER_INPUT_LENGTH
																													&& removeCustomDelimiterPatternStart(numbers, 0, 2).equals
																																																											(CUSTOM_DELIMITER_PATTERNSTART);
		if (hasValidCustomDelimiterPatternStart) {
			delimiter = parseCustomDelimiter(numbers);
			numbers = removeCustomDelimiterPatternStart(numbers, CUSTOM_DELIMITER_PATTERNEND_INDEX, numbers.length());
		} else {
			delimiter = DEFAULT_DELIMITER;
		}

		numbersWithNewLineReplaced = numbers.replaceAll("\n", delimiter);
		positiveNumbers = getNumbersBasedOnPredicate(num -> num > 0);
		negativeNumbers = getNumbersBasedOnPredicate(num -> num < 0);

		if (negativeNumbers.size() > 0) {
			throwNegativeNumberException();
		}

		return positiveNumbers.stream()
									 .mapToInt(num -> num)
									 .sum();
	}

	private void throwNegativeNumberException() {
		throw new NegativeNumberException("Invalid input: " + negativeNumbers.stream()
																																	.map(num -> Integer.toString(num))
																																	.collect(Collectors.joining(",")));
	}

	private List<Integer> getNumbersBasedOnPredicate(Predicate<Integer> predicate) {
		return Arrays.stream(numbersWithNewLineReplaced.split(delimiter))
									 .mapToInt(Integer::parseInt)
									 .boxed()
									 .filter(predicate)
									 .collect(Collectors.toList());

	}

	private String removeCustomDelimiterPatternStart(String numbers, int customDelimiterPatternEndIndex, int length) {
		return numbers.substring(customDelimiterPatternEndIndex, length);
	}

	private String parseCustomDelimiter(String numbers) {
		return String.valueOf(numbers.charAt(CUSTOM_DELIMETER_INDEX));
	}
}

