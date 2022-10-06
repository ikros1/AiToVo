package com;

import util.*;

public class MainAi {
    //项目路径和ffmpeg绝对路径
    private final static String run_path="C:\\Users\\ikaros\\Desktop\\zhenghe\\";
    private final static String ffmpeg_path = "C:\\ffmpeg-master-latest-win64-gpl\\bin\\ffmpeg.exe";
    //语音转写api密匙
    private static final String APP_ID = "92c8395";
    private static final String SECRET_KEY = "93e78bf87c10b866754a809e64a0e97";
    //声纹识别api密匙
    private static final String requestUrl = "https://api.xf-yun.com/v1/private/s782b4996";
    private static final String APPID = "aab1359";
    private static final String apiSecret = "Zjk2ZmFY2E4ZGEzMWU5ZjljY2ZhZWE1";
    private static final String apiKey = "c5711d5e53dad90b32265b9ba4704fa";
    //是否已经创建过识别库
    private static final boolean have_group=false;
    //识别结果容错率 范围0.0f~1.0f,数值越低容错率越低。推荐0.75f
    private final static float fault_tolerance=0.75f;




    private final static String handle_path=run_path+"path\\mp3_list\\";
    private final static String handle_out_path=run_path+"path\\mp3_handle_mi\\";
    private final static String handle_text_mi=run_path+"path\\filelists\\handle_mi.txt";
    private final static String handle_text_ov_22050=run_path+"path\\filelists\\handle_ov_22050.txt";
    private final static String handle_text_ov_44100=run_path+"path\\filelists\\handle_ov_44100.txt";
    private final static String handle_result_fan="path/mp3_handle_ov/";
    private final static String handle_result_wav_22050="path/mp3_handle_ov_to_wav22050/";
    private final static String handle_result_wav_44100="path/mp3_handle_ov_to_wav44100/";
    private final static String build_feature_file_path=run_path+"path\\build_feature\\";




    public static void main(String[] args) {
        creat_group(have_group);
        build_feature();
        file_cut_to_piece();
        System.exit(0);
    }
    public static void creat_group(boolean have){
        if(!have) {
            CreateGroup.doCreateGroup(requestUrl, APPID, apiSecret, apiKey);
        }
    }
    public static void build_feature(){
        GetNumOfFolder getNumOfFolder = new GetNumOfFolder();
        int files_length=getNumOfFolder.numberOfFiles(build_feature_file_path);
        for (int i=1;i<=files_length;i++) {
            CreateFeature.doCreateFeature(requestUrl,APPID,apiSecret,apiKey,build_feature_file_path+i+".mp3");
        }

    }
    public static void file_cut_to_piece(){
        GetNumOfFolder getNumOfFolder = new GetNumOfFolder();
        int files_length=getNumOfFolder.numberOfFiles(handle_path);
        for (int i=1;i<=files_length;i++) {
            DieDai dieDai =new DieDai(handle_result_wav_22050,handle_result_wav_44100,handle_text_ov_22050,handle_text_ov_44100,requestUrl,APPID,apiSecret,apiKey,run_path,handle_path,handle_out_path,ffmpeg_path,handle_text_mi,handle_result_fan,APP_ID,SECRET_KEY);
            dieDai.setSrc(handle_path+i+".mp3");
            dieDai.setFault_tolerance(fault_tolerance);
            GetMp3Len getMp3Len = new GetMp3Len();
            dieDai.setOver(getMp3Len.getMp3Duration(handle_path+i+".mp3")*1000);
            dieDai.diedai(i);
        }
    }

}
