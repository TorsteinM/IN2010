import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {
    // Generert med KI for å teste løsning opp mot Kattis kriterier
    // Scorer 98/100 med 0.72 kjøretid. Antar det er Stringbuilder etc. som tar mye tid.
    public static void main(String[] args) throws Exception {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        int numberOfCommands = Integer.parseInt(reader.readLine());
        Teque teque = new Teque();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < numberOfCommands; i++) {
            String line = reader.readLine();

            int separator = line.indexOf(' ');
            String command = line.substring(0, separator);
            int value = Integer.parseInt(line.substring(separator + 1));

            switch (command) {
                case "push_back":
                    teque.push_back(value);
                    break;

                case "push_front":
                    teque.push_front(value);
                    break;

                case "push_middle":
                    teque.push_middle(value);
                    break;

                case "get":
                    output.append(teque.get(value)).append('\n');
                    break;
            }
        }

        System.out.print(output);
    }
}