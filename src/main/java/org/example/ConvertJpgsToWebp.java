import com.luciad.imageio.webp.WebPWriteParam;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

public class ConvertJpgsToWebp {

    public static void main(String[] args) {
        String inputDirectory = "E:\\pic\\jpg\\";
        String outputDirectory = "E:\\pic\\webp\\";

        ConvertJpgsToWebp converter = new ConvertJpgsToWebp();
        converter.convertJpgsToWebp(inputDirectory, outputDirectory);
    }

    private void convertJpgsToWebp(String inputDirectory, String outputDirectory) {
        File folder = new File(inputDirectory);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null) {
            System.out.println("Input directory does not exist or is not a directory.");
            return;
        }

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".jpg")) {
                try {
                    String inputFilePath = file.getAbsolutePath();
                    String outputFilePath = outputDirectory + file.getName().replace(".jpg", ".webp");

                    BufferedImage image = ImageIO.read(file);
                    ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/webp").next();
                    WebPWriteParam writeParam = new WebPWriteParam(writer.getLocale());
                    writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    writeParam.setCompressionType(writeParam.getCompressionTypes()[WebPWriteParam.LOSSY_COMPRESSION]);
                    writeParam.setCompressionQuality(0.8f);

                    writer.setOutput(new FileImageOutputStream(new File(outputFilePath)));
                    writer.write(null, new IIOImage(image, null, null), writeParam);
                    writer.dispose();

                    System.out.println("File converted: " + outputFilePath);
                } catch (IOException e) {
                    System.out.println("Error converting file: " + file.getAbsolutePath());
                    e.printStackTrace();
                }
            }
        }
    }
}
