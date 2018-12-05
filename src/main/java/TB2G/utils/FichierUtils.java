package TB2G.utils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FichierUtils {

    public File imageDansFichier (Part filePart) throws IOException {

        File uploads = new File(PropertiesUtils.cheminPro());
        File file = File.createTempFile("img", ".jpg", uploads);

        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return file;
    }
}
