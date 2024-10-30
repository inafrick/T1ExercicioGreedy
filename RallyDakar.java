import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RallyDakar {

    public static void main(String[] args) {
        int L = 200;
        int d = 20;
        
        // Lendo o arquivo e extraindo as paradas
        String filePath = "stops.txt";
        int[] stops = readStopsFromFile(filePath);
        System.out.println("\n");
        System.out.println("\n");
        
        if (stops == null) {
            System.out.println("Erro ao ler o arquivo de paradas.");
            return;
        }

        List<Integer> result = rallyStops(stops, L, d);

        if (result != null) {
            System.out.println("Paradas no rally: " + result);
            System.out.println("Numero de paradas: " + result.size());
        } else {
            System.out.println("Não é possível completar o rally com os pontos de parada fornecidos.");
        }
    }

    public static int[] readStopsFromFile(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            int[] stops = new int[lines.size()];
            
            for (int i = 0; i < lines.size(); i++) {
                stops[i] = Integer.parseInt(lines.get(i).trim());
            }
            
            return stops;
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter uma linha para número: " + e.getMessage());
        }
        
        return null;
    }

    public static List<Integer> rallyStops(int[] stops, int L, int d) {
        List<Integer> result = new ArrayList<>();
        int currentPosition = 0; 
        int n = stops.length; 

        if (L - currentPosition <= d) {
                result.add(L);
                return result;
            }

        for (int i = 0; i < n;) {
            int lastPosition = currentPosition;

            while (i < n && stops[i] - currentPosition <= d) {
                lastPosition = stops[i];
                i++;
            }

            if (lastPosition == currentPosition) {
                System.out.println("Impossível alcançar o próximo ponto.");
                return null;
            }

            result.add(lastPosition);
            currentPosition = lastPosition;

            if (L - currentPosition <= d) {
                result.add(L);
                break;
            }
        }

        return result;
    }
}
