import java.util.Arrays;

public class ChatWindow {
    private final int windowWidth;

    public ChatWindow(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    private static Text[] breakLines(Text data) {
        // Break strings at the next word after line length hits 30 characters
        return data.lines()
                .flatMap((s) -> Arrays.stream(s.split("(?<=\\G\\b?.{30,}\\s)")))
                .map(Text::strip)
                .toArray(Text[]::new);
    }

    public void printResponse(Text response) {
        final Text[] lines = breakLines(response);
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
                .append(" ".repeat(windowWidth - 7)).append("MIA ╰╯ \n");
        System.out.printf(sb.toString());
    }

    public void printCommand(Text command) {
        final Text[] lines = breakLines(command);
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
                .append(" ╰╯ You").append(" ".repeat(maxLength - 3)).append("\n");
        System.out.printf(sb.toString());
    }
}