package org.example;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputDirectory = "E:\\pic\\jpg\\";
        String outputDirectory = "E:\\pic\\webp\\";

        // Create the output directory if it doesn't exist
        File outputDirFile = new File(outputDirectory);
        if (!outputDirFile.exists()) {
            outputDirFile.mkdirs();
        }

        File folder = new File(inputDirectory);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".jpg")) {
                String inputFilePath = file.getAbsolutePath();
                String outputFilePath = outputDirectory + file.getName().replace(".jpg", ".webp");
                convertJpegToWebp(inputFilePath, outputFilePath);
                //System.out.println(outputFilePath);
            }
        }
    }

    private static void convertJpegToWebp(String inputFile, String outputFile) throws Exception {
        // Read JPG image
        BufferedImage image = ImageIO.read(new File(inputFile));
        // Encode it as webp using default settings and save it as webp file
        ImageIO.write(image, "webp", new File(outputFile));
    }
}
