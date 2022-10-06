package util;

public class DieDai {
    private int bg=0;
    private int ed=40000;
    private int sheng=-2;
    private String src;
    private int over;
    private  String APPID ;
    private  String apiSecret ;
    private  String apiKey ;
    private String requestUrl = "https://api.xf-yun.com/v1/private/s782b4996";



    private  String run_path;
    private  String handle_path;
    private  String handle_out_path;
    private  String ffmpeg_path;
    private  String handle_text_mi;
    private  String handle_text_ov_22050;
    private  String handle_text_ov_44100;
    private  String handle_result_fan;
    private  String handle_result_wav_22050;
    private  String handle_result_wav_44100;
    //翻译的密码
    private String APP_ID;
    private String SECRET_KEY;

    private float fault_tolerance;
    public void setOver(int over) {
        this.over = over;
    }

    public DieDai(String handle_result_wav_22050,String handle_result_wav_44100,String handle_text_ov_22050,String handle_text_ov_44100,String requestUrl,String APPID, String apiSecret, String apiKey, String run_path, String handle_path, String handle_out_path, String ffmpeg_path, String handle_text_mi, String handle_result_fan, String APP_ID, String SECRET_KEY) {
        this.handle_result_wav_22050=handle_result_wav_22050;
        this.handle_result_wav_44100=handle_result_wav_44100;
        this.handle_text_ov_22050=handle_text_ov_22050;
        this.handle_text_ov_44100=handle_text_ov_44100;
        this.requestUrl=requestUrl;
        this.APPID = APPID;
        this.apiSecret = apiSecret;
        this.apiKey = apiKey;
        this.run_path = run_path;
        this.handle_path = handle_path;
        this.handle_out_path = handle_out_path;
        this.ffmpeg_path = ffmpeg_path;
        this.handle_text_mi = handle_text_mi;
        this.handle_result_fan = handle_result_fan;
        this.APP_ID = APP_ID;
        this.SECRET_KEY = SECRET_KEY;
    }

    public void setFault_tolerance(float fault_tolerance) {
        this.fault_tolerance = fault_tolerance;
    }


    public void setSrc(String src) {
        this.src = src;
    }
    public boolean diedai(int j){
        FanYi fanYi = new FanYi(APP_ID, SECRET_KEY, ffmpeg_path);
        Ffmpeg ffmpeg =new Ffmpeg(ffmpeg_path);
        SearchOneFeature searchOneFeature=new SearchOneFeature(requestUrl,APPID,apiSecret,apiKey);
        int i=1;
        if(ed>=over){
            ed=over;
        }
        float jindu=0;
        boolean jieshupanding= false;
        while(sheng!=-1) {
            NowHandle[] nowHandle_shu ={};
            ffmpeg.mp3Cut(ffmpeg.secondToTime(bg),ffmpeg.secondToTime(ed),src,handle_out_path+j+"."+i+".mp3");
                NowHandle nowHandle=null;
                Ffmpeg ffmpeg1=new Ffmpeg(ffmpeg_path);
                   try {
                    nowHandle_shu = fanYi.return_nowhandle(handle_out_path  +j+"."+i + ".mp3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                   sheng=nowHandle_shu.length;
            if(sheng>1){
                int pp=1;


                for(int d=0;d<sheng-1;d++,pp++) {
                    nowHandle=nowHandle_shu[d];
                    if (nowHandle.ed - nowHandle.start > 2000) {

                        float rsu = 0;
                        nowHandle.setOut(handle_result_fan + j + "." + i + "."+pp+ ".mp3");
                        nowHandle.cut_now_handle_to_save();
                        ffmpeg1.mp3toAr16000Ac1mp3(handle_result_fan + j + "." + i + "."+pp+ ".mp3", handle_out_path + j + "." + i + "."+pp+ "yan.mp3");
                        searchOneFeature.setAUDIO_PATH(handle_out_path + j + "." + i + "."+pp+ "yan.mp3");
                        rsu = searchOneFeature.doSearchOneFeature();
                        nowHandle.save_string_to_txt(handle_text_mi, handle_result_fan + j + "." + i + "."+pp+ ".mp3|相似度:" + rsu + "|");


                        if (fault_tolerance < rsu) {
                            System.out.println("捕获成功 好耶！！！！！");
                            nowHandle.turntowav22050(handle_result_fan + j + "." + i + "."+pp+ ".mp3", run_path + handle_result_wav_22050 + j + "." + i + "."+pp+ ".wav");
                            nowHandle.turntowav44100(handle_result_fan + j + "." + i + "."+pp+ ".mp3", run_path + handle_result_wav_44100 + j + "." + i + "."+pp+ ".wav");
                            nowHandle.save_string_to_txt(handle_text_ov_22050, handle_result_wav_22050 + j + "." + i + "."+pp+ ".wav|");
                            nowHandle.save_string_to_txt(handle_text_ov_44100, handle_result_wav_44100 + j + "." + i + "."+pp+ ".wav|");
                        }

                    }if(d==sheng-2) {
                        bg = nowHandle.ed + bg;
                    }


                }
                ed = bg + 40000;
                if (ed >= over) {
                    ed = over;
                }


            }
            else {
                ed+=40000;
                if(ed>=over){
                    ed=over;
                    jieshupanding=true;
                }
                if(jieshupanding){sheng=-1; bg=over;}
            }



            jindu=(float)bg/(float)over;
            System.out.println("//////////////////////////////////////当前文件 "+j+" 进度:  "+jindu+"    ///////////////////////////////////////////////////");
            i++;
        }
        return true;
    }
}
