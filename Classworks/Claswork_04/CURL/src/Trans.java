import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Trans {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            // grab the file path the user types 
            System.out.print("Enter path to .txt file: ");
            String filePath = scanner.nextLine();
            // just to checkk if the file exists 
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("File not found.");
                return;
            }

            //  read the whole file into a single string
            String content = new String(Files.readAllBytes(file.toPath()));

            // destination language
            System.out.print("Translate to: ");
            String targetLang = scanner.nextLine();

            // pulls the token from the environment variable "OpenAIToken"
            String token = System.getenv("OpenAIToken");

            if (token == null) {
                System.out.println("OpenAIToken not set.");
                return;
            }

            // MPrompt to tell the model what to do
            String prompt = "Translate this text to " + targetLang + ": " + content;

            // we escape quotes so they don't break the JSON structure,
            // and flatten newlines because the JSON needs to stay on one line
            prompt = prompt.replace("\"", "\\\"")
                           .replace("\n", " ")
                           .replace("\r", " ");

            // build the JSON payload for the API request
            String json = "{"
                    + "\"model\":\"gpt-4.1-mini\","
                    + "\"messages\":[{\"role\":\"user\",\"content\":\"" + prompt + "\"}]"
                    + "}";

            /// each flag is a separate list item, that's how ProcessBuilder expects it 
            // "@-" tells curl to read the body from stdin instead of an argument — avoids
            // the issue where spaces in the text get treated as separate arguments
            ProcessBuilder pb = new ProcessBuilder(
                    "curl",
                    "-s",           // helps to hide the progress bar so it doesn't mix into the response
                    "-X", "POST",
                    "https://api.openai.com/v1/chat/completions",
                    "-H", "Content-Type: application/json",
                    "-H", "Authorization: Bearer " + token,
                    "-d", "@-"
            );
            // merge stderr into stdout so we catch everything in one stream
            pb.redirectErrorStream(true);

            Process process = pb.start();

            // write the JSON into curl's stdin 
            try (OutputStream os = process.getOutputStream()) {
                os.write(json.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            // We use StringBuilder instead of += to avoid creating a new object every iteration
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            process.waitFor();

            // This helps if the folder name has ".txt" in it we don't break the path
            String outputPath = filePath.replaceFirst("\\.txt$", "_translated.txt");

            // try-with-resources makes sure the file closes even if something blows up
            try (FileWriter writer = new FileWriter(outputPath)) {
                writer.write(response.toString());
            }

            System.out.println("Saved in: " + outputPath);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}