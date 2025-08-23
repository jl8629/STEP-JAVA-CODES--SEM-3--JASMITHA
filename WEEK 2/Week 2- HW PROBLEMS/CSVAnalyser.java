import java.util.Scanner;

public class CSVAnalyser {
    static String[][] parseCSV(String input) {
        int rows = 1;
        for (int i = 0; i < input.length(); i++) if (input.charAt(i) == '\n') rows++;
        String[][] data = new String[rows][];
        int rowIndex = 0, start = 0, colCount = 0;
        for (int i = 0; i < input.length(); i++) if (input.charAt(i) == ',' || input.charAt(i) == '\n') colCount++;
        colCount = colCount / rows + 1;
        for (int r = 0; r < rows; r++) data[r] = new String[colCount];
        int colIndex = 0;
        boolean inQuotes = false;
        for (int i = 0; i <= input.length(); i++) {
            char c = (i < input.length() ? input.charAt(i) : '\n');
            if (c == '\"') inQuotes = !inQuotes;
            else if ((c == ',' || c == '\n') && !inQuotes) {
                String field = input.substring(start, i).replaceAll("^\\s+|\\s+$", "");
                if (field.startsWith("\"") && field.endsWith("\"")) field = field.substring(1, field.length() - 1);
                data[rowIndex][colIndex++] = field;
                start = i + 1;
                if (c == '\n') {
                    rowIndex++;
                    colIndex = 0;
                }
            }
        }
        return data;
    }

    static boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(c >= '0' && c <= '9') && c != '.') return false;
        }
        return true;
    }

    static void analyze(String[][] data) {
        int rows = data.length, cols = data[0].length;
        StringBuilder table = new StringBuilder();
        int[] widths = new int[cols];
        for (int c = 0; c < cols; c++) {
            widths[c] = 0;
            for (int r = 0; r < rows; r++) if (data[r][c] != null && data[r][c].length() > widths[c]) widths[c] = data[r][c].length();
        }
        for (int c = 0; c < cols; c++) if (widths[c] < 10) widths[c] = 10;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                String val = data[r][c] == null ? "" : data[r][c];
                table.append(String.format("%-" + widths[c] + "s", val)).append(" | ");
            }
            table.append("\n");
        }
        System.out.println("Formatted Table:");
        System.out.println(table.toString());
        for (int c = 0; c < cols; c++) {
            boolean numeric = true;
            for (int r = 1; r < rows; r++) if (!isNumeric(data[r][c])) { numeric = false; break; }
            if (numeric) {
                double min = Double.MAX_VALUE, max = Double.MIN_VALUE, sum = 0;
                int count = 0;
                for (int r = 1; r < rows; r++) {
                    if (isNumeric(data[r][c])) {
                        double val = Double.parseDouble(data[r][c]);
                        if (val < min) min = val;
                        if (val > max) max = val;
                        sum += val;
                        count++;
                    }
                }
                double avg = count > 0 ? sum / count : 0;
                System.out.println("Column " + data[0][c] + " -> Min: " + min + " Max: " + max + " Avg: " + String.format("%.2f", avg));
            }
        }
        int missing = 0, total = rows * cols;
        for (int r = 1; r < rows; r++) for (int c = 0; c < cols; c++) if (data[r][c] == null || data[r][c].isEmpty()) missing++;
        double completeness = 100.0 * (total - missing) / total;
        System.out.println("Records processed: " + (rows - 1));
        System.out.println("Missing/Invalid entries: " + missing);
        System.out.println("Data Completeness: " + String.format("%.2f", completeness) + "%");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV-like data (end with an empty line):");
        StringBuilder input = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            input.append(line).append("\n");
        }
        String[][] data = parseCSV(input.toString());
        analyze(data);
    }
}
