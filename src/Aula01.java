import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by EduardoKarakawa on 06/03/2017.
 */
public class Aula01 {
    public static void main(String [] args) throws IOException {

        /*Cria uma imagem em degrade do preto ao vermelho*/

        BufferedImage image = new BufferedImage(800,  600, BufferedImage.TYPE_INT_ARGB);

        float incremento = 255 / 600.0f;
        float r = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color cor = new Color((int)r, 0, 0);
                image.setRGB(x, y, cor.getRGB());
            }
            r += incremento;
        }

        ImageIO.write(image, "png", new File("red.png"));
        System.out.println("Ola mundo!");
    }
}
