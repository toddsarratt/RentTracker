package net.toddsarratt.renttracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class QueryHandler {
	static void execute() throws IOException {
		repl();
	}

	private static void repl() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter queries:");
		String input;
		do {
			System.out.print("> ");
			input = reader.readLine();
			checkForExit(input);
			if (!queryShapedProperly(input)) {
				printHelpMessage();
			} else {
				try {
					handleQuery(input);
				} catch (IllegalArgumentException iae) {
					System.out.println(iae.getMessage());
					printHelpMessage();
				}
			}
		} while (!input.equalsIgnoreCase("exit"));
	}

	private static void printHelpMessage() {
		System.out.println("HELP!");
	}

	/**
	 * Basic validity check of input. Must contain "query" and "-s", at a minimum. Does not guarantee that the entire
	 * query statement is valid.
	 *
	 * @param query input from console
	 * @return false if basic information is missing from input
	 */
	private static boolean queryShapedProperly(String query) {
		if (query == null) {
			return false;
		}
		if (!query.startsWith("query")) {
			return false;
		}
		return query.contains("-s");
	}

	private static void handleQuery(String query) throws IllegalArgumentException {
		String[] arguments = query.split("-");
		if (!arguments[1].startsWith("s")) {
			throw new IllegalArgumentException("First flag must be \"-s\"");
		}
		// arguments[1] should look like: s TITLE,REV,DATE
		// View data is stored in fields
		String viewFields = arguments[1].substring(2, arguments[1].length() - 1);
		String viewField[] = viewFields.split(",");
	}

	private static void checkForExit(String input) {
		if (input.equalsIgnoreCase("exit")) {
			System.out.println("Homo sum humani a me nihil alienum puto");
			System.exit(0);
		}
	}
}
