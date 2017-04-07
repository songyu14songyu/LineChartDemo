package sunrain.linechartdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sunrain.linechartlib.LineChartView;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    //x轴坐标对应的数据
    private List<String> xValue = new ArrayList<>();
    private List<String> xString = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValue = new ArrayList<>();
    //折线对应的数据
    private Map<String, Integer> value = new HashMap<>();

    private List<String> yDegreeString = new ArrayList<String>(asList("严重", "重度", "中度","轻度","良","优"));
    private List<Integer> yDegreeValue = new ArrayList<>(asList(50, 40, 30,20,10,0));
    private List<Integer> pointColor = new ArrayList<>(asList(Color.RED, Color.YELLOW, Color.GREEN,Color.BLUE,Color.WHITE,Color.TRANSPARENT));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();

        for (int i = 0; i < 24; i++) {
            //横坐标轴显示文字
            if(getCurrentTime() == i){
                xString.add("现在");
            }else {
                xString.add((i) + "时");
            }
            xValue.add((i) + "");
            //横纵坐标 点
            value.put((i) + "", (int) (Math.random() * 51 + 10));//60--240
        }

        for (int i = 0; i < 4; i++) {
            //纵坐标轴显示文字
            yValue.add(i * 20);
        }

        final LineChartView chartView = (LineChartView) findViewById(R.id.chartview);
        chartView.setValue(value, xValue, yValue,xString,yDegreeValue,yDegreeString,pointColor);
        chartView.setInterval(width/8);
        new Handler().postDelayed(new Runnable(){

            public void run() {
                if(getCurrentTime() > 3){
                    chartView.setCurrentPosition(getCurrentTime() - 4);
                }
            }

        }, 150);
    }
    public int  getCurrentTime(){
        Time t=new Time();
        t.setToNow(); // 取得系统时间。
        int hour = t.hour; // 0-23
        return hour;
    }
}
