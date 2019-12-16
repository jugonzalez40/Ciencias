import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);
        String line;

        while ((line = in.readLine()) != null) {
            String[] params = line.split(" ");
            int n = Integer.parseInt(params[0]);
            BigInteger c =  new BigInteger(params[1]);
            int x = Integer.parseInt(params[2]);
            int y = Integer.parseInt(params[3]);
            if (n == 0 && x == 0 && c.toString() == "0" && y == 0)
                break;
            y = y - 1;
            // x = (n - 1) - (x - 1);
            x = x-1;
            int[][] world = new int[n][n];
            String binaryConfig[] = c.toString(2).split("");
            Deque<Integer> q = new LinkedList<>();

            for (String bit : binaryConfig) {
                q.add(Integer.parseInt(bit));
            }
            // Ahora se deben incluir los 0 la izquierda
            int diffLeft = ((n * n) - binaryConfig.length);
            for (int k = 0; k < diffLeft; k++)
                q.addFirst(0);

            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    world[i][j] = q.poll();
                }
            }
            String face = "Nort"; // South, East, west
            String message = "";
            while (true) {
                if (x == 0 && y == n - 1) {
                    message = "Yes";
                    break;
                } else if (x < 0 || x >= n || y < 0 || y >= n) {
                    message = "Kaputt!";
                    break;
                }
                switch (face) {
                case "Nort": {
                    if (world[x][y] == 0) {
                        y--;
                        face = "West";
                    } else {
                        y++;
                        face = "East";
                    }
                    // left = j-- face = west
                    // right = j++ face = east
                    break;
                }
                case "South": {
                    if (world[x][y] == 0) {
                        y++;
                        face = "East";
                    } else {
                        y--;
                        face = "West";
                    }
                    // left = j++ face = east
                    // right = j-- face = west
                    break;
                }
                case "West": {
                    if (world[x][y] == 0) {
                        x++;
                        face = "South";
                    } else {
                        x--;
                        face = "North";
                    }
                    // left = i++ face = south
                    // right = i-- face = north
                    break;
                }
                case "East": {
                    if (world[x][y] == 0) {
                        x--;
                        face = "North";
                    } else {
                        x++;
                        face = "South";
                    }
                    // left = i-- face = north
                    // right = i++ face = south
                    break;
                }
                }

            }

            // out.add(message);
            System.out.println(message);
        }
        // for (String message : out)
        //     System.out.println(message);
    }
}