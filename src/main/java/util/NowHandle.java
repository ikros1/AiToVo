package util;

public class NowHandle {
    public int start;
    public int ed;
    public String onebest;
    public String ffmpeg_path;
    public String src;
    public String out;


    public void setStart(int start) {
        this.start = start;
    }

    public void setEd(int ed) {
        this.ed = ed;
    }

    public void setOnebest(String onebest) {
        this.onebest = onebest;
    }

    public void setFfmpeg_path(String ffmpeg_path) {
        this.ffmpeg_path = ffmpeg_path;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setOut(String out) {
        this.out = out;
    }


    public boolean cut_now_handle_to_save(){
        Ffmpeg ffmpeg= new Ffmpeg(ffmpeg_path);
            ffmpeg.mp3Cut(ffmpeg.secondToTime(start), ffmpeg.secondToTime(ed), src, out);

        return true;
    }
    public void turntowav44100(String input,String output){
        Ffmpeg ffmpeg = new Ffmpeg(ffmpeg_path);
        ffmpeg.mp3toAr44100Ac1wav(input,output);
    }
    public  void turntowav22050(String input,String output){
        Ffmpeg ffmpeg = new Ffmpeg(ffmpeg_path);
        ffmpeg.mp3toAr22050Ac1wav(input,output);
    }
    public boolean save_string_to_txt(String  txt,String qian){

            FileTxtHandle fileTxtHandle = new FileTxtHandle(txt);
            fileTxtHandle.txt_add(qian + onebest);

        return true;
    }
}
