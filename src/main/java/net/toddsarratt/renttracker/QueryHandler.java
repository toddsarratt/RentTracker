package net.toddsarratt.renttracker;

import java.io.Console;

class QueryHandler {
	static void doIt() {
		Console console = System.console();
		if (console == null) {
			System.err.println("No console.");
			System.exit(1);
		}

		System.out.println("Enter queries:");
		String input;
		do {
			input = console.readLine("> ");
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
	 * Basic validity check of input. Must contain "query" and "-s", at a minimum, or be the "exit" command. Does not
	 * guarantee that the entire query statement is valid.
	 *
	 * @param query input from console
	 * @return false if basic information is missing from input
	 */
	private static boolean queryShapedProperly(String query) {
		if (query == null) {
			return false;
		}
		if (!query.startsWith("query") && !query.equalsIgnoreCase("exit")) {
			return false;
		}
		return query.contains("-s");
	}

	private static void handleQuery(String query) throws IllegalArgumentException {
		String[] arguments = query.split("-");
		if (!arguments[1].startsWith("s ")) {
			throw new IllegalArgumentException("First flag must be \"-s\"");
		}
		// arguments[1] should look like: s TITLE,REV,DATE
		// View data is stored in fields
		String viewFields = arguments[1].substring(2, arguments[1].length() - 1);
		String viewField[] = viewFields.split(",");

	}
}
