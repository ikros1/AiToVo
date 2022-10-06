package util;

import java.io.File;

public class GetNumOfFolder {
    public  int numberOfFiles(String src){
        File folder = new File(src);
        File []list = folder.listFiles();
        int fileCount = 0, folderCount = 0;
        long length = 0;
        for (File file : list){
            if (file.isFile()){
                fileCount++;
                length += file.length();
            }else {
                folderCount++;
            }
        }
        return fileCount;
    }



}
