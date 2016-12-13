package Graphics;

import java.util.HashMap;
import java.util.Map;

public class Font {
    public static Font consolas = new Font(" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~", 16, 16, 16, SpriteSheet.consolas16);
    public Map<String, Sprite> characters = new HashMap<String, Sprite>();
    public SpriteSheet sheet;

    public Font(String chars, int width, int height, int rowWidth, SpriteSheet sheet) {
        this.sheet = sheet;
        int i = 0;
        while (i < chars.length()) {
            int x = i % rowWidth;
            int y = (int)Math.floor(i / rowWidth);
            String character = chars.substring(i, i + 1);
            Sprite sp = new Sprite(width, height, x, y, sheet);
            this.characters.put(character, sp);
            ++i;
        }
    }

    public Sprite string(String in) {
        Sprite out = new Sprite();
        int width = this.characters.get(" ").getWidth();
        int height = this.characters.get(" ").getWidth();
        out.pixels = new int[width * height * in.length()];
        out.sheet = this.sheet;
        out.width = width * in.length();
        out.height = height;
        int i = 0;
        while (i < height) {
            int j = 0;
            while (j < in.length()) {
                int k = 0;
                while (k < width) {
                    out.pixels[i * width * in.length() + j * width + k] = this.characters.get(in.substring(j, (j + 1))).pixels[i * width + k];
                    k++;
                }
                j++;
            }
            i++;
        }
        return out;
    }
}