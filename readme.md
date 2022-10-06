
## 训练集制作的日语特定角色语音分离与文本标注v1.0.0
## 项目介绍
> 本项目基于 讯飞sdk 进行二次开发，感谢sdk提供的服务！



## 功能

- [x] 将含有说话人的音频从目标音频中分离
- [x] 将说话人的音频进行语音转写
- [x] 将说话人的音频进行声纹识别并标注与目标说话人的相似度
- [x] 将识别度大于一定值的音频进行文本标注与转码
- [x] 最后转码格式为1声道，44100采样率和1声道22050采样率的wav文件

## 前置需求

1. 需要环境安装ffmpeg
2. 需要讯飞语音转写服务api 并开通日语语种服务，请到 https://www.xfyun.cn/services/lfasr
3. 需要讯飞声纹识别服务api 请到 https://www.xfyun.cn/services/voiceprint-recognition
## api文档
1. 声纹识别 https://www.xfyun.cn/doc/voiceservice/isv/API.html
2. 语音转写 https://www.xfyun.cn/doc/asr/lfasr/API.html
## 参数说明
    //项目绝对地址
    private final static String run_path="";
    //ffmpeg绝对地址  例 "C:\ffmpeg-master-latest-win64-gpl\bin\ffmpeg.exe"
    private final static String ffmpeg_path = "";
    //语音转写api密匙
    private static final String APP_ID = "xxx";
    private static final String SECRET_KEY = "xxxxxxxxx";
    //声纹识别api密匙
    private static final String requestUrl = "https://xxxxxx";
    private static final String APPID = "xxxxx";
    private static final String apiSecret = "xxxxxxxx";
    private static final String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    //是否已经创建过识别库
    private static final boolean have_group=false;
    //识别结果容错率 范围0.0f~1.0f,数值越低容错率越低。推荐0.75f
    private final static float fault_tolerance=0.75f;

## 结果说明
    
    private final static String handle_out_path=run_path+"path\\mp3_handle_mi\\";
    //相似度储存文本
    private final static String handle_text_mi=run_path+"path\\filelists\\handle_mi.txt";
    //22050采样率文字标注结果存放文件
    private final static String handle_text_ov_22050=run_path+"path\\filelists\\handle_ov_22050.txt";
    //44100采样率文字标注结果存放文件
    private final static String handle_text_ov_44100=run_path+"path\\filelists\\handle_ov_44100.txt";
    private final static String handle_result_fan="path/mp3_handle_ov/";
    //22050采样率音频结果存放目录
    private final static String handle_result_wav_22050="path/mp3_handle_ov_to_wav22050/";
    //44100采样率音频结果存放目录
    private final static String handle_result_wav_44100="path/mp3_handle_ov_to_wav44100/";
    

##输入说明
    //音频特征文件存放目录
    private final static String build_feature_file_path=run_path+"path\\build_feature\\";    
    //待识别音频存放目录
    private final static String handle_path=run_path+"path\\mp3_list\\";
##输入要求
1. 音频特征文件格式要求 1声道 16000采样率 mp3 时长小于20秒 只包含目标说话人声音
2. 音频特征文件命名要求 从数字1开始自增排序,没有空格eg
    1.mp3
    2.mp3
    ~xx.mp3
3. 待识别音频文件要求 1声道 44100采样率 mp3
4. 待识别音频文件命名要求 从数字1开始自增排序,没有空格
   eg
    1.mp3
    2.mp3
    ~xx.mp3

##最后祝大家轻松取材，愉快炼丹。