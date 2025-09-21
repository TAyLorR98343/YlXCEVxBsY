// 代码生成时间: 2025-09-22 06:17:16
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ProcessManager class that manages and displays process information.
 */
public class ProcessManager {

    /**
     * Main method to run the process manager.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            // Get the list of running processes
            List<ProcessInfo> processes = getProcesses();
            // Display the processes
            displayProcesses(processes);
        } catch (IOException e) {
            System.err.println("Error retrieving process information: " + e.getMessage());
        }
    }

    /**
     * Retrieves a list of running processes.
     * @return A list of ProcessInfo objects representing the running processes.
     * @throws IOException If an I/O error occurs.
     */
    private static List<ProcessInfo> getProcesses() throws IOException {
        // Execute the command to get the list of processes
        Process process = Runtime.getRuntime().exec("ps");
        // Read the output of the command
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        // Skip the header line
        reader.readLine();
        // Read each process line and extract process information
        return reader.lines()
            .skip(1)
            .map(line -> parseProcessInfo(line))
            .collect(Collectors.toList());
    }

    /**
     * Parses a single line of process information and returns a ProcessInfo object.
     * @param line The line of process information to parse.
     * @return A ProcessInfo object representing the process.
     */
    private static ProcessInfo parseProcessInfo(String line) {
        // Assuming the format of the process line is: PID USERNAME CMD
        String[] parts = line.trim().split(" +");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid process information format: " + line);
        }
        // Create and return a new ProcessInfo object
        return new ProcessInfo(parts[0], parts[1], parts[2]);
    }

    /**
     * Displays a list of processes.
     * @param processes The list of processes to display.
     */
    private static void displayProcesses(List<ProcessInfo> processes) {
        processes.forEach(process -> {
            System.out.printf("PID: %s, Username: %s, Command: %s\
", process.getPid(), process.getUsername(), process.getCommand());
        });
    }

    /**
     * A class representing process information.
     */
    public static class ProcessInfo {
        private final String pid;
        private final String username;
        private final String command;

        /**
         * Constructor for ProcessInfo.
         * @param pid The process ID.
         * @param username The username running the process.
         * @param command The command running the process.
         */
        public ProcessInfo(String pid, String username, String command) {
            this.pid = pid;
            this.username = username;
            this.command = command;
        }

        // Getters for PID, username, and command
        public String getPid() { return pid; }
        public String getUsername() { return username; }
        public String getCommand() { return command; }
    }
}