package util;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import java.io.File;

/** @Author huyi @Date 2021/9/30 15:06 @Description: mp3��Ƶ���� */
public class GetMp3Len {
    /**
     * ��ȡmp3�����ļ�����ʱ��(��) mp3
     *
     * @param filePath
     * @return
     */
    public int  getMp3Duration(String filePath) {
        MP3AudioHeader audioHeader=null;
        try {
            File mp3File = new File(filePath);
            MP3File f = (MP3File) AudioFileIO.read(mp3File);
            audioHeader = (MP3AudioHeader) f.getAudioHeader();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assert audioHeader != null;
        return audioHeader.getTrackLength();
    }


}

