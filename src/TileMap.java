import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TileMap {
    private Image[][] tiles;


    public TileMap(int width, int height) {
        tiles = new Image[width][height];
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

    public void setTile(int x, int y, Image tile) {
        tiles[x][y] = tile;
    }

    public Image getTile(int x, int y) {
        if(x < 0 || x >= getWidth() || y < 0 || y >=getHeight()){
            return null;
        } else {
            return tiles[x][y];
        }
    }

    private TileMap loadMap(String filename) throws IOException {
        List lines = new ArrayList();
        int width = 0;
        int height = 0;

        BufferedReader reader = new BufferedReader(
                new FileReader(filename));

        while(true) {
            String line = reader.readLine();

            if (line == null) {
                reader.close();
                break;
            }

            if(!line.startsWith("#")) {
                lines.add(line);
                // finding longest length for the width
                width = Math.max(width, line.length());
            }
        }

        height = lines.size();
        TileMap tileMap = new TileMap(width, height);

        for(int y = 0; y < height; y++) {
            String line = (String) lines.get(y);

            for(int x = 0; x < line.length(); x++) {
                char ch = line.charAt(x);

                // check if char represents tile A, B, C
                int tile = ch - 'A';

                // TO DO FIX
                if(tile >= 0 && tile < tiles.length) {
                    // TO DO
                }
            }
        }
    }
}
