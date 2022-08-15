import java.util.Arrays;
import java.util.Scanner;

public class Mia {
    public static void main(String[] args) {
        final String logo = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                            "┃ You are talking to MIA... ┃\n" +
                            "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";
        System.out.println(logo);

        final int windowWidth = 50;
        printResponse("Hello there!", windowWidth);
        final Scanner sc = new Scanner(System.in);

        System.out.print("Enter a command: ");
        while (sc.hasNextLine()) {
            final String line = sc.nextLine().strip();

            // Replaces the entered command (previous line) with a bubble
            System.out.print("\u001B[1A\u001B[K");
            printCommand(line);

            if (line.equals("bye")) {
                printResponse("See you!", windowWidth);
                break;
            }
            printResponse(line, windowWidth);
            System.out.print("Enter a command: ");
        }
        sc.close();
    }

    private static String[] breakLines(String data) {
        // Break strings at the next word after line length hits 30 characters
        return data.lines()
                .flatMap((s) -> Arrays.stream(s.split("(?<=\\G\\b?.{30,}\\s)")))
                .map(String::strip)
                .toArray(String[]::new);
    }

    private static void printResponse(String response, int windowWidth) {
        final String[] lines = breakLines(response);
        int maxLength = 3; // prevents negative count
        for (int i = 0; i < lines.length; i++) {
            if (maxLength < lines[i].length()) {
                maxLength = lines[i].length();
            }
        }
        final int paddingLength = windowWidth - maxLength - 4;
        StringBuilder sb = new StringBuilder(" ".repeat(paddingLength))
                .append("╭").append("─".repeat(maxLength + 2)).append("╮\n");
        // Pad lines right
        final String formatString = "%-" + maxLength + "s";
        for (int i = 0; i < lines.length; i++) {
            sb.append(" ".repeat(paddingLength))
                    .append("│ ").append(String.format(formatString, lines[i])).append(" │\n");
        }
        sb.append(" ".repeat(paddingLength))
                .append("╰").append("─".repeat(maxLength)).append("╮┬╯\n")
                .append(" ".repeat(windowWidth-7)).append("MIA ╰╯ \n");
        System.out.printf(sb.toString());
    }

    private static void printCommand(String command) {
        final String[] lines = breakLines(command);
        int maxLength = 3; // prevents negative count
        for (int i = 0; i < lines.length; i++) {
            if (maxLength < lines[i].length()) {
                maxLength = lines[i].length();
            }
        }
        StringBuilder sb = new StringBuilder("╭").append("─".repeat(maxLength + 2)).append("╮\n");
        // Pad lines right
        final String formatString = "%-" + maxLength + "s";
        for (int i = 0; i < lines.length; i++) {
            sb.append("│ ").append(String.format(formatString, lines[i])).append(" │\n");
        }
        sb.append("╰┬╭").append("─".repeat(maxLength)).append("╯\n")
                .append(" ╰╯ You").append(" ".repeat(maxLength-3)).append("\n");
        System.out.printf(sb.toString());
    }
}
