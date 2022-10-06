package util;

public class Ffmpeg {
    private String ffmpeg;

    public Ffmpeg(String ffmpeg) {
        this.ffmpeg = ffmpeg;
    }




    //整数秒转换,精确到毫秒
    public   String secondToTime(int seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException("Seconds must be a positive number!");
        } else {
            int hour = seconds / 3600000;
            int other = seconds % 3600000;
            int minute = other / 60000;
            other = other % 60000;
            int second = other / 1000;
            int ms = other % 1000;
            StringBuilder sb = new StringBuilder();
            if (hour < 10) {
                sb.append("0");
            }

            sb.append(hour);
            sb.append(":");
            if (minute < 10) {
                sb.append("0");
            }

            sb.append(minute);
            sb.append(":");
            if (second < 10) {
                sb.append("0");
            }

            sb.append(second);
            sb.append(".");
            if (ms < 100) {
                sb.append("0");
            }
            if (ms < 10) {
                sb.append("0");
            }
            sb.append(ms);
            return sb.toString();
        }
    }

    public void mp3toAr16000Ac1mp3(String input,String output){
        String cmd = ffmpeg + " -y -i " + input + " -ac 1 -ar 16000 " + output;
        //System.out.println("ffmpeg命 令:"+cmd);

        try {
            Runtime runtime = Runtime.getRuntime();
            Process exec = runtime.exec(cmd);
            exec.waitFor();
            exec.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void mp3toAr44100Ac1wav(String input,String output){
        String cmd = ffmpeg + " -y -i " + input + " -ac 1 -ar 44100 " + output;
        //System.out.println("ffmpeg命 令:"+cmd);

        try {
            Runtime runtime = Runtime.getRuntime();
            Process exec = runtime.exec(cmd);
            exec.waitFor();
            exec.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void mp3toAr22050Ac1wav(String input,String output){
        String cmd = ffmpeg + " -y -i " + input + " -ac 1 -ar 22050 " + output;
        //System.out.println("ffmpeg命 令:"+cmd);

        try {
            Runtime runtime = Runtime.getRuntime();
            Process exec = runtime.exec(cmd);
            exec.waitFor();
            exec.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public  void mp3Cut(String start, String end, String input, String out) {
        //E:\ffmpeg\bin\ffmpeg.exe -i E:/tlb/365.mp3 -ss 00:00:08.551 -to 00:01:04.023 -y E:/tlb/365/5.mp3

        String cmd = ffmpeg + " -i " + input + " -ss " + start + " -to " + end + " -y " + out;
        //System.out.println("ffmpeg命 令:"+cmd);

        try {
            Runtime runtime = Runtime.getRuntime();
            Process exec = runtime.exec(cmd);
            exec.waitFor();
            exec.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
