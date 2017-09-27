public class StringCalculator {

		public int add(String numbers) {
				if (numbers.isEmpty()) {
						return 0;
				}
				numbers = numbers.replaceAll("\n", ",");
				String[] splitNumbers = numbers.split(",");
				int result = 0;
				for (String splitNumber : splitNumbers) {
						result += Integer.parseInt(splitNumber);
				}
				return result;
		}
}

