import java.util.ArrayList;
import java.util.List;

public class RallyDakar {

    public static void main(String[] args) {
        int L = 100;
        int d = 40;
        int[] stops = {10, 30, 40, 80};

        List<Integer> result = rallyStops(stops, L, d);

        if (result != null) {
            System.out.println("Paradas no rally: " + result);
        } else {
            System.out.println("Não é possível completar o rally com os pontos de parada fornecidos.");
        }
    }

    public static List<Integer> rallyStops(int[] stops, int L, int d) {
        List<Integer> result = new ArrayList<>();
        int currentPosition = 0; 
        int n = stops.length; 

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
