import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

/**
 * Created by EduardoKarakawa on 06/03/2017.
 */


public class Exercicios {
    /*Pega uma imagem, pega do usuario uma intensidade e aumenta o brilho da imagem (nao podendo estourar 255 ou ser inferior a 0)*/

    public  int saturate(int value){
        return value > 255 ? 255 : value;
    }


    public  BufferedImage bright(BufferedImage img, float intensity){
        intensity = intensity + 1 <= 0 ? 0 : intensity < 0 ? intensity + 1 : intensity;

        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int y = 0; y < out.getHeight(); y++){
            for(int x = 0; x < out.getWidth(); x++){
                Color cor = new Color(img.getRGB(x,y));
                int r = saturate((int) (cor.getRed() * intensity));
                int g = saturate((int) (cor.getGreen() * intensity));
                int b = saturate((int) (cor.getBlue() * intensity));

                Color outColor = new Color(r, g, b);
                out.setRGB(x, y, outColor.getRGB());
            }
        }
        return out;
    }


    public BufferedImage grayscale(BufferedImage img){
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                Color cor = new Color(img.getRGB(x, y));

                int gray = (int)((0.3f * cor.getRed() + 0.59f * cor.getGreen() + 0.11f *cor.getBlue()));
                //int gray = (int)((cor.getRed() + cor.getGreen() + cor.getBlue()) / 3.f);

                Color outColor = new Color(gray, gray, gray);
                out.setRGB(x, y, outColor.getRGB());
            }
        }

    return out;
    }


    public boolean iqual(BufferedImage img1, BufferedImage img2){
        return img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight();
    }


    public int minus(int n1, int n2){

        return n1 - n2 < 0 ? 0 : n1 - n2;
    }


    public BufferedImage subtract(BufferedImage img1, BufferedImage img2){
        BufferedImage out = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);

        if(iqual(img1, img2)){
            for(int y = 0; y < img1.getHeight(); y++){
                for(int x = 0; x < img1.getWidth(); x++){
                    Color pix1 = new Color(img1.getRGB(x, y));
                    Color pix2 = new Color(img2.getRGB(x, y));


                    out.setRGB(x, y, new Color( minus(pix1.getRed(), pix2.getRed()),
                                                minus(pix1.getGreen(), pix2.getGreen()),
                                                minus(pix1.getBlue(), pix2.getBlue())).getRGB());

                }
            }
        }

        return out;
    }


    public int plus(int n1, int n2){

        return n1 + n2 > 255 ? 255 : n1 + n2;
    }


    public BufferedImage add(BufferedImage img1, BufferedImage img2){
        BufferedImage out = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);

        if(iqual(img1, img2)){
            for(int y = 0; y < img1.getHeight(); y++){
                for(int x = 0; x < img1.getWidth(); x++){
                    Color pix1 = new Color(img1.getRGB(x, y));
                    Color pix2 = new Color(img2.getRGB(x, y));


                    out.setRGB(x, y, new Color( plus(pix1.getRed(), pix2.getRed()),
                                                plus(pix1.getGreen(), pix2.getGreen()),
                                                plus(pix1.getBlue(), pix2.getBlue())).getRGB());

                }
            }
        }

        return out;
    }


    public BufferedImage lerp(BufferedImage img1, BufferedImage img2, float percent){
        BufferedImage out = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);
        if(iqual(img1, img2)){
            for(int y = 0; y < img1.getHeight(); y++){
                for(int x = 0; x < img1.getWidth(); x++){
                    Color p1 = new Color(img1.getRGB(x, y));
                    Color p2 = new Color(img2.getRGB(x, y));

                    out.setRGB(x, y, new Color( (int)(p1.getRed() * (1.0f - percent) + p2.getRed() * percent),
                                                (int)(p1.getGreen() * (1.0f - percent) + p2.getGreen() * percent),
                                                (int)(p1.getBlue() * (1.0f - percent) + p2.getBlue() * percent)).getRGB());



                }
            }
        }
        return out;

    }


    public float[] getRGBf(int x, int y, BufferedImage img){
        float aux = 1.f / 255.f;
        Color pixel = new Color(img.getRGB(x, y));
        float outRGB[] = {pixel.getRed() * aux, pixel.getGreen() * aux, pixel.getBlue() * aux};
        return outRGB;
    }


    public float[] getRGBAf(int x, int y, BufferedImage img){
        float aux = 1.f / 255.f;
        Color pixel = new Color(img.getRGB(x, y));
        float outRGB[] = {pixel.getRed() * aux, pixel.getGreen() * aux, pixel.getBlue() * aux, pixel.getAlpha() * aux};
        return outRGB;
    }


    public BufferedImage setRGBf(int x, int y, BufferedImage img, float[] color){
        BufferedImage outImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        outImg = img;
        outImg.setRGB(x, y, new Color((int)(color[0] * 255), (int)(color[1] * 255), (int)(color[2] * 255)).getRGB());
        return outImg;
    }


    public BufferedImage setRGBAf(int x, int y, BufferedImage img, float[] color){
        BufferedImage outImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        outImg = img;
        outImg.setRGB(x, y, new Color((int)(color[0] * 255), (int)(color[1] * 255), (int)(color[2] * 255), (int)(color[3] * 255)).getRGB());
        return outImg;
    }


    public BufferedImage multiply(BufferedImage src, float[] color){
        BufferedImage outImg = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for(int y = 0; y < src.getHeight();y++){
            for(int x = 0; x < src.getWidth(); x++){

                float tmpPixel[] = getRGBf(x, y, src);
                if(color.length > 3) {
                    tmpPixel = getRGBAf(x, y, src);
                }

                for(int i = 0; i < color.length; i++){
                    System.out.print(tmpPixel[i]);
                    System.out.print("    ");
                    tmpPixel[i] = tmpPixel[i] * color[i];
                    System.out.println(tmpPixel[i]);
                }
                System.out.println("------------------");
                if(color.length > 3)
                    outImg = setRGBAf(x, y, outImg, tmpPixel);
                else
                    outImg = setRGBf(x, y, outImg, tmpPixel);
            }
        }
        return outImg;
    }


    public void run() throws IOException {

        String PATH = "C:\\Users\\B155 FIRE V3\\Downloads\\Image\\img";
        BufferedImage img = ImageIO.read(new File(PATH, "\\cor\\puppy.png"));
        BufferedImage sub1 = ImageIO.read(new File(PATH, "\\pb\\errosB1.png"));
        BufferedImage sub2 = ImageIO.read(new File(PATH, "\\pb\\errosB2.png"));

        BufferedImage darkimg = bright(img, -0.9f);
        BufferedImage lightimg = bright(img, 5.f);
        BufferedImage grayimg = grayscale(img);
        BufferedImage subtractimg = subtract(sub1, sub2);
        float color[] = {0.8f, 0.9f, 0.1f, 0.6f};
        BufferedImage multiplyimg = multiply(img, color);


        ImageIO.write(darkimg, "png", new File("darkpupp.png"));

        ImageIO.write(lightimg, "png", new File("lightpuppy.png"));

        ImageIO.write(grayimg, "png", new File("graypuppy.png"));

        ImageIO.write(multiplyimg, "png", new File("multiplypuppy.png"));


        ImageIO.write(subtractimg, "png", new File("subtract.png"));

        sub1 = ImageIO.read(new File("C:\\Users\\B155 FIRE V3\\Meus Arquivos\\Estudo\\Pucpr\\3º Semestre\\Programacao 3D\\Aula 01\\subtract.png"));
        sub2 = ImageIO.read(new File(PATH, "\\pb\\errosB1.png"));
        BufferedImage addimg = add(sub1, sub2);

        ImageIO.write(addimg, "png", new File("addimg.png"));

        sub1 = ImageIO.read(new File("C:\\Users\\B155 FIRE V3\\Downloads\\Image\\img\\pb\\errosB1.png"));
        sub2 = ImageIO.read(new File("C:\\Users\\B155 FIRE V3\\Downloads\\Image\\img\\pb\\errosB2.png"));

        BufferedImage lerpimg = lerp(sub1, sub2, 0.25f);
        ImageIO.write(lerpimg, "png", new File("lerpimg25.png"));

        lerpimg = lerp(sub1, sub2, 0.5f);
        ImageIO.write(lerpimg, "png", new File("lerpimg50.png"));

        lerpimg = lerp(sub1, sub2, 0.75f);
        ImageIO.write(lerpimg, "png", new File("lerpimg75.png"));

    }



    public static void main(String [] args) throws IOException{
        new Exercicios().run();

    }
}
