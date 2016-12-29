package com.kalkulator.lukasz.indexwig;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    DownloadWigDataFromNet downloadWigDataFromNet;
    TextView averageTextView, maximumTextView, minimumTextView;
    Button DownloadButton;
    private GraphCustomView graphCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        averageTextView=(TextView)findViewById(R.id.TextViewAverage);
        maximumTextView=(TextView)findViewById(R.id.TextViewMaximum);
        minimumTextView=(TextView)findViewById(R.id.TextViewMinimum);
        DownloadButton=(Button)findViewById(R.id.buttonDownload);
        graphCustomView=(GraphCustomView)findViewById(R.id.CustomViewGraph);

        onClickListeners();
    }


    @Override
    public void processFinish(List<Float> output){//receive the result from DownloadWigDataFromNet class
        averageTextView.setText( getResources().getString(R.string.average) +" "+ Statistic.calAverage(output).toString() );
        maximumTextView.setText( getResources().getString(R.string.maximum) +" "+ Collections.max(output).toString() );
        minimumTextView.setText( getResources().getString(R.string.minimum) +" "+ Collections.min(output).toString() );
        graphCustomView.SetWigData(output);
        graphCustomView.invalidate();
    }


    void onClickListeners() {
        DownloadButton.setOnClickListener(v -> {
            downloadWigDataFromNet=new DownloadWigDataFromNet();//task cant be executed  twice
            downloadWigDataFromNet.delegate = this;
            downloadWigDataFromNet.execute(Constants.BASE_URL);
        });
    }


}
