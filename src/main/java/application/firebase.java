package application;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import javafx.application.Platform;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class firebase {
    private static String bucketName;
    private static Storage storage;

    public firebase(){
        try {
            String pathToFirebaseAdminSDK = "D:/eclipse/book system/St-System-Library/src/main/resources/fxmlFile/firebaseAdmin.json";
            FileInputStream serviceAccount = new FileInputStream(pathToFirebaseAdminSDK);
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
            bucketName = "stsystemlibrary.appspot.com";
        }
        catch (Exception e){
            notificationsClass nC=new notificationsClass();
            nC.showNotificaitonSomethingWrong("failed to Connect To Firebase");
        }
    }

    public String uploadFileUrl(String filePath, String remoteFilePath){
        try (
                InputStream content = Files.newInputStream(new File(filePath).toPath())) {
            storage.get(bucketName).create(remoteFilePath, content);
            Blob blob = storage.get(bucketName, remoteFilePath);
            return storage.signUrl(BlobInfo.newBuilder(blob.getBlobId()).build(), 1, TimeUnit.HOURS).toString();
        }catch (Exception e) {
            Platform.runLater(() -> {
                notificationsClass nC = new notificationsClass();
                if (e instanceof StorageException) {
                    nC.showNotificaitonNointernet();
                } else {
                    nC.showNotificaitonSomethingWrong("failed to uploaded to firebase");
                }
            });
            return "0";
        }
    }
    public String uploadImageUrl(String imagePath,String remoteImagePath){
        try (
                InputStream content = Files.newInputStream(new File(imagePath).toPath())) {
            storage.get(bucketName).create(remoteImagePath, content);
            Blob blob = storage.get(bucketName, remoteImagePath);
            return storage.signUrl(BlobInfo.newBuilder(blob.getBlobId()).build(), 1, TimeUnit.HOURS).toString();
        }
        catch (Exception e) {
            Platform.runLater(() -> {
                notificationsClass nC = new notificationsClass();
                if (e instanceof StorageException) {
                    nC.showNotificaitonNointernet();
                } else {
                    nC.showNotificaitonSomethingWrong("failed to uploaded to firebase");
                }
            });
            return "0";
        }
    }
    //        try {
    //            URL fileUrl = new URL(url);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) fileUrl.openConnection();
//            httpURLConnection.setRequestMethod("GET");
//            httpURLConnection.setConnectTimeout(5000);
//            int responseCode = httpURLConnection.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                InputStream inputStream = httpURLConnection.getInputStream();
//                FileOutputStream outputStream = new FileOutputStream(destinationPath);
//                byte[] buffer = new byte[4096];
//                int bytesRead;
//                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//                inputStream.close();
//                outputStream.close();
//            }
    public InputStream downloadFile(String urlGet, String destinationPath) {
        InputStream inputStream = null;
        try {
            URL url = new URL(urlGet);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                 inputStream = connection.getInputStream();
            }else {
                System.out.println("Failed to load image. HTTP error code: " + connection.getResponseCode());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            notificationsClass nC=new notificationsClass();
            nC.showNotificaitonSomethingWrong("failed to download file from Firebase");
        }
        return inputStream;
    }
    public void deleteFile(String objectPath) {
        try {
            BlobId blobId = BlobId.of(bucketName, objectPath);
            Blob blob = storage.get(blobId);

            if (blob != null && blob.exists()) {
                blob.delete();
            } else {
                notificationsClass nC=new notificationsClass();
                nC.showNotificaitonSomethingWrong("failed to delete File Or Image");
            }
        } catch (Exception e) {
                notificationsClass nC=new notificationsClass();
                nC.showNotificaitonSomethingWrong("failed to delete File Or Image");
        }
    }

}
