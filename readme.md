
## ѵ���������������ض���ɫ�����������ı���עv1.0.0
## ��Ŀ����
> ����Ŀ���� Ѷ��sdk ���ж��ο�������лsdk�ṩ�ķ���



## ����

- [x] ������˵���˵���Ƶ��Ŀ����Ƶ�з���
- [x] ��˵���˵���Ƶ��������תд
- [x] ��˵���˵���Ƶ��������ʶ�𲢱�ע��Ŀ��˵���˵����ƶ�
- [x] ��ʶ��ȴ���һ��ֵ����Ƶ�����ı���ע��ת��
- [x] ���ת���ʽΪ1������44100�����ʺ�1����22050�����ʵ�wav�ļ�

## ǰ������

1. ��Ҫ������װffmpeg
2. ��ҪѶ������תд����api ����ͨ�������ַ����뵽 https://www.xfyun.cn/services/lfasr
3. ��ҪѶ������ʶ�����api �뵽 https://www.xfyun.cn/services/voiceprint-recognition
## api�ĵ�
1. ����ʶ�� https://www.xfyun.cn/doc/voiceservice/isv/API.html
2. ����תд https://www.xfyun.cn/doc/asr/lfasr/API.html
## ����˵��
    //��Ŀ���Ե�ַ
    private final static String run_path="";
    //ffmpeg���Ե�ַ  �� "C:\ffmpeg-master-latest-win64-gpl\bin\ffmpeg.exe"
    private final static String ffmpeg_path = "";
    //����תдapi�ܳ�
    private static final String APP_ID = "xxx";
    private static final String SECRET_KEY = "xxxxxxxxx";
    //����ʶ��api�ܳ�
    private static final String requestUrl = "https://xxxxxx";
    private static final String APPID = "xxxxx";
    private static final String apiSecret = "xxxxxxxx";
    private static final String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    //�Ƿ��Ѿ�������ʶ���
    private static final boolean have_group=false;
    //ʶ�����ݴ��� ��Χ0.0f~1.0f,��ֵԽ���ݴ���Խ�͡��Ƽ�0.75f
    private final static float fault_tolerance=0.75f;

## ���˵��
    
    private final static String handle_out_path=run_path+"path\\mp3_handle_mi\\";
    //���ƶȴ����ı�
    private final static String handle_text_mi=run_path+"path\\filelists\\handle_mi.txt";
    //22050���������ֱ�ע�������ļ�
    private final static String handle_text_ov_22050=run_path+"path\\filelists\\handle_ov_22050.txt";
    //44100���������ֱ�ע�������ļ�
    private final static String handle_text_ov_44100=run_path+"path\\filelists\\handle_ov_44100.txt";
    private final static String handle_result_fan="path/mp3_handle_ov/";
    //22050��������Ƶ������Ŀ¼
    private final static String handle_result_wav_22050="path/mp3_handle_ov_to_wav22050/";
    //44100��������Ƶ������Ŀ¼
    private final static String handle_result_wav_44100="path/mp3_handle_ov_to_wav44100/";
    

##����˵��
    //��Ƶ�����ļ����Ŀ¼
    private final static String build_feature_file_path=run_path+"path\\build_feature\\";    
    //��ʶ����Ƶ���Ŀ¼
    private final static String handle_path=run_path+"path\\mp3_list\\";
##����Ҫ��
1. ��Ƶ�����ļ���ʽҪ�� 1���� 16000������ mp3 ʱ��С��20�� ֻ����Ŀ��˵��������
2. ��Ƶ�����ļ�����Ҫ�� ������1��ʼ��������,û�пո�eg
    1.mp3
    2.mp3
    ~xx.mp3
3. ��ʶ����Ƶ�ļ�Ҫ�� 1���� 44100������ mp3
4. ��ʶ����Ƶ�ļ�����Ҫ�� ������1��ʼ��������,û�пո�
   eg
    1.mp3
    2.mp3
    ~xx.mp3

##���ף�������ȡ�ģ����������