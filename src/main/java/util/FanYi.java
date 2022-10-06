package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.msp.lfasr.LfasrClient;
import com.iflytek.msp.lfasr.exception.LfasrException;
import com.iflytek.msp.lfasr.model.Message;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title : SDK 调用实例</p>
 * <p>Description : </p>
 * <p>Date : 2020/4/20 </p>
 *
 * @author : hejie
 */
public class FanYi {
    private  String APP_ID ;
    private  String SECRET_KEY;
    private  String ffmpeg_path;

    public FanYi(String APP_ID, String SECRET_KEY ,String ffmpeg_path) {
        this.APP_ID = APP_ID;
        this.SECRET_KEY = SECRET_KEY;
        this.ffmpeg_path = ffmpeg_path;
    }

    public  void standard(String mp3_path) throws InterruptedException {
        //1、创建客户端实例
        LfasrClient lfasrClient = LfasrClient.getInstance(APP_ID, SECRET_KEY);

        //2、上传
        Message task = lfasrClient.upload(mp3_path);
        String taskId = task.getData();
        System.out.println("转写任务 taskId：" + taskId);

        //3、查看转写进度
        int status = 0;
        while (status != 9) {
            Message message = lfasrClient.getProgress(taskId);
            JSONObject object = JSON.parseObject(message.getData());
            if (object ==null) throw new LfasrException(message.toString());
            status = object.getInteger("status");
            System.out.println(message.getData());
            TimeUnit.SECONDS.sleep(2);
        }
        //4、获取结果
        Message result = lfasrClient.getResult(taskId);
        System.out.println("转写结果: \n" + result.getData());


        //退出程序，关闭线程资源，仅在测试main方法时使用。
        System.exit(0);
    }

    /**
     * 带有业务参数，调用样例
     *
     * @throws InterruptedException e
     */

    public NowHandle[] return_nowhandle(String mp3_path) throws InterruptedException {
        //1、创建客户端实例
        LfasrClient lfasrClient = LfasrClient.getInstance(APP_ID, SECRET_KEY);

        //2、上传
        //2.1、设置业务参数
        Map<String, String> param = new HashMap<>(16);
        //是否开启分词：默认 false
        //param.put("has_participle","true");
        //转写结果中最大的候选词个数：默认：0，最大不超过5
        //param.put("max_alternatives","2");

        //是否开启角色分离：默认为false
        //param.put("has_seperate","true");
        //发音人个数，可选值：0-10，0表示盲分：默认 2
        //param.put("speaker_number","3");
        //角色分离类型 1-通用角色分离；默认 1
        //param.put("role_type","1");

        //是否开启敏感词检测：默认 false
        //param.put("has_sensitive","true");
        //敏感词检测类型： 0-默认词库；1-自定义敏感词
        //param.put("sensitive_type","1");
        //自定义的敏感词：每个词用英文逗号分割，整个字符串长度不超过256
        //param.put("keywords","你好");

        //语种： cn-中文（默认）;en-英文（英文不支持热词）
        param.put("language", "ja");
        //垂直领域个性化：法院-court；教育-edu；金融-finance；医疗-medical；科技-tech
        //param.put("pd","finance");

        Message task = lfasrClient.upload(
                mp3_path
                , param);
        String taskId = task.getData();
        //System.out.println("转写任务 taskId：" + taskId);

        //3、查看转写进度
        int status = 0;
        while (status != 9) {
            Message message = lfasrClient.getProgress(taskId);
            JSONObject object = JSON.parseObject(message.getData());
            status = object.getInteger("status");
            //System.out.println(message.getData());
            TimeUnit.SECONDS.sleep(2);
        }
        //4、获取结果
        Message result = lfasrClient.getResult(taskId);
        JSONArray jsonObject =JSONObject.parseArray(result.getData());
        NowHandle nowHandle_shu[]={};
        for(int i=0;i<jsonObject.size();i++) {
            JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get(i).toString());
            NowHandle nowHandle = new NowHandle();
            nowHandle.setFfmpeg_path(ffmpeg_path);
            nowHandle.setStart(jsonObject1.getInteger("bg"));
            nowHandle.setEd(jsonObject1.getInteger("ed"));
            nowHandle.setOnebest(jsonObject1.getString("onebest"));
            nowHandle.setSrc(mp3_path);
            nowHandle_shu= Arrays.copyOf(nowHandle_shu,nowHandle_shu.length+1);
            nowHandle_shu[nowHandle_shu.length-1]=nowHandle;

        }
        return nowHandle_shu;
        // System.out.println("转写结果: \n" + result.getData());


        //退出程序，关闭线程资源，仅在测试main方法时使用。

    }
    public int businessExtraParams(String mp3_path) throws InterruptedException {
        //1、创建客户端实例
        LfasrClient lfasrClient = LfasrClient.getInstance(APP_ID, SECRET_KEY);

        //2、上传
        //2.1、设置业务参数
        Map<String, String> param = new HashMap<>(16);
        //是否开启分词：默认 false
        //param.put("has_participle","true");
        //转写结果中最大的候选词个数：默认：0，最大不超过5
        //param.put("max_alternatives","2");

        //是否开启角色分离：默认为false
        //param.put("has_seperate","true");
        //发音人个数，可选值：0-10，0表示盲分：默认 2
        //param.put("speaker_number","3");
        //角色分离类型 1-通用角色分离；默认 1
        //param.put("role_type","1");

        //是否开启敏感词检测：默认 false
        //param.put("has_sensitive","true");
        //敏感词检测类型： 0-默认词库；1-自定义敏感词
        //param.put("sensitive_type","1");
        //自定义的敏感词：每个词用英文逗号分割，整个字符串长度不超过256
        //param.put("keywords","你好");

        //语种： cn-中文（默认）;en-英文（英文不支持热词）
        param.put("language", "ja");
        //垂直领域个性化：法院-court；教育-edu；金融-finance；医疗-medical；科技-tech
        //param.put("pd","finance");

        Message task = lfasrClient.upload(
                mp3_path
                , param);
        String taskId = task.getData();
        //System.out.println("转写任务 taskId：" + taskId);

        //3、查看转写进度
        int status = 0;
        while (status != 9) {
            Message message = lfasrClient.getProgress(taskId);
            JSONObject object = JSON.parseObject(message.getData());
            status = object.getInteger("status");
            System.out.println(message.getData());
            TimeUnit.SECONDS.sleep(2);
        }
        //4、获取结果
        Message result = lfasrClient.getResult(taskId);
        JSONArray jsonObject =JSONObject.parseArray(result.getData());
        return jsonObject.size();
       // System.out.println("转写结果: \n" + result.getData());


        //退出程序，关闭线程资源，仅在测试main方法时使用。

    }

    /**
     * 设置网络代理，调用样例
     *
     * @throws InterruptedException e
     */
    private  void netProxy(String mp3_path) throws InterruptedException {
        //1、创建客户端实例, 设置网络代理
        LfasrClient lfasrClient = LfasrClient.getInstance(APP_ID, SECRET_KEY, "http://x.y.z/");
        //LfasrClient lfasrClient = LfasrClient.getInstance(APP_ID, SECRET_KEY);


        //2、上传
        //2.1、设置业务参数
        Map<String, String> param = new HashMap<>(16);
        //语种： cn-中文（默认）;en-英文（英文不支持热词）
        param.put("language", "cn");
        //垂直领域个性化：法院-court；教育-edu；金融-finance；医疗-medical；科技-tech
        //param.put("pd","finance");

        Message task = lfasrClient.upload(
                mp3_path
                , param);
        String taskId = task.getData();
        System.out.println("taskId" + taskId);


        //3、查看转写进度
        int status = 0;
        while (status != 9) {
            Message message = lfasrClient.getProgress(taskId);
            JSONObject object = JSON.parseObject(message.getData());
            status = object.getInteger("status");
            System.out.println(message.getData());
            TimeUnit.SECONDS.sleep(2);
        }
        //4、获取结果
        Message result = lfasrClient.getResult(taskId);


        //System.out.println("转写结果: \n" + result.getData());


        //退出程序，关闭线程资源，仅在测试main方法时使用。
        System.exit(0);
    }

    /**
     * 性能调优参数，调用样例
     *
     * @throws InterruptedException e
     */
    private  void performance(String mp3_path) throws InterruptedException {
        //1、创建客户端实例, 设置性能参数
        LfasrClient lfasrClient =
                LfasrClient.getInstance(
                        APP_ID,
                        SECRET_KEY,
                        10, //线程池：核心线程数
                        50, //线程池：最大线程数
                        50, //网络：最大连接数
                        10000, //连接超时时间
                        30000, //响应超时时间
                        null);
        //LfasrClient lfasrClient = LfasrClient.getInstance(APP_ID, SECRET_KEY);


        //2、上传
        //2.1、设置业务参数
        Map<String, String> param = new HashMap<>(16);
        //语种： cn-中文（默认）;en-英文（英文不支持热词）
        param.put("language", "cn");
        //垂直领域个性化：法院-court；教育-edu；金融-finance；医疗-medical；科技-tech
        //param.put("pd","finance");

        Message task = lfasrClient.upload(
                mp3_path
                , param);
        String taskId = task.getData();
        System.out.println("转写任务 taskId：" + taskId);


        //3、查看转写进度
        int status = 0;
        while (status != 9) {
            Message message = lfasrClient.getProgress(taskId);
            JSONObject object = JSON.parseObject(message.getData());
            status = object.getInteger("status");
            System.out.println(message.getData());
            TimeUnit.SECONDS.sleep(2);
        }
        //4、获取结果
        Message result = lfasrClient.getResult(taskId);
        System.out.println("转写结果: \n" + result.getData());


        //退出程序，关闭线程资源，仅在测试main方法时使用。
        System.exit(0);
    }

}
