import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
/**
 * Diplomado Ciencias de la computación 
 * Problema vjudge.net => Langton's Ant 
 * https://vjudge.net/contest/347090#problem/D
 * @author  Juan Sebastián González Rivera 
 */
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
            x = (x - 1);
            y = (n - y);
            
            // x = x-1;
            
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
            String face = "North"; // South, East, west
            String message = "";
            while (true) {
                // System.out.println("param");
                // for(String param : params) System.out.println(param);
                if (y == 0 && x == n - 1) {
                    message = "Yes";
                    break;
                } else if (x < 0 || x >= n || y < 0 || y >= n) {
                    message = "Kaputt!";
                    break;
                }
                switch (face) {
                case "North": {
                    if (world[y][x] == 0) {
                        world[y][x] = 1;
                        x--;
                        face = "West";
                    } else {
                        world[y][x] = 0;
                        x++;
                        face = "East";
                    }
                    // left = j-- face = west
                    // right = j++ face = east
                    break;
                }
                case "South": {
                    if (world[y][x] == 0) {
                        world[y][x] = 1;
                        x++;
                        face = "East";
                    } else {
                        world[y][x] = 0;
                        x--;
                        face = "West";
                    }
                    // left = j++ face = east
                    // right = j-- face = west
                    break;
                }
                case "West": {
                    if (world[y][x] == 0) {
                        world[y][x] = 1;
                        y++;
                        face = "South";
                    } else {
                        world[y][x] = 0;
                        y--;
                        face = "North";
                    }
                    // left = i++ face = south
                    // right = i-- face = north
                    break;
                }
                case "East": {
                    if (world[y][x] == 0) {
                        world[y][x] = 1;
                        y--;
                        face = "North";
                    } else {
                        world[y][x] = 0;
                        y++;
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