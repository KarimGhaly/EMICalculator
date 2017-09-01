package com.example.admin.emicalculator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;


public class Second extends AppCompatActivity {

    TextView txtName;
    TextView txtAge;
    ImageView imgView2;

    TextView tvIntersetRate;
    TextView tvLoanAmout;
    TextView tvLoanDuration;
    TextView tvTotal;
    TextView tvTotalInterest;
    TextView tvPerMonth;

    SeekBar skLoanAmout;
    SeekBar skLoanDuration;
    SeekBar skInterset;
    TextView txInterest;
    TextView txLoanAmount;
    TextView txLoanDuration;

    GridLayout gridLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        declareVariables();
        Intent intent = getIntent();
        String clientName = intent.getStringExtra(Constants.clientName);
        String clientAge = intent.getStringExtra(Constants.clientAge);
        if(clientName!=null)
        txtName.setText(clientName);
        if(clientAge!=null)
        txtAge.setText(clientAge);
        byte[] byteArray = intent.getByteArrayExtra(Constants.ImageKey);
        if(byteArray!=null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imgView2.setImageBitmap(bmp);
        }
        skLoanAmout.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int Progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Progress = progress;
                txLoanAmount.setText("$"+progress);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });
        skInterset.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int Progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Progress = progress;
                txInterest.setText(Progress+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        skLoanDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int Progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Progress = progress;
                txLoanDuration.setText(Progress+" M");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void declareVariables()
    {

        txtName = (TextView) findViewById(R.id.txtName);
        txtAge = (TextView) findViewById(R.id.txtAge);
        imgView2 = (ImageView) findViewById(R.id.imgView2);

        tvIntersetRate = (TextView) findViewById(R.id.tvInterest);
        tvLoanAmout = (TextView) findViewById(R.id.tvLoanAmount);
        tvLoanDuration = (TextView) findViewById(R.id.tvLoanDuration);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvTotalInterest = (TextView) findViewById(R.id.tvTotalInterset);
        tvPerMonth = (TextView) findViewById(R.id.tvPerMonth);

        skInterset = (SeekBar) findViewById(R.id.sbIntersetRate);
        skLoanAmout = (SeekBar) findViewById(R.id.sbLoanAmount);
        skLoanDuration = (SeekBar) findViewById(R.id.sbLoanTenure);
        txInterest = (TextView) findViewById(R.id.txInterestRate);
        txLoanAmount = (TextView) findViewById(R.id.txLoanAmount);
        txLoanDuration = (TextView) findViewById(R.id.txLoanTenure);

        gridLayout1 = (GridLayout) findViewById(R.id.gridLayout1);
    }

    public void Calculate(View view) {
        double LoanAmount = skLoanAmout.getProgress();
        double Interset = skInterset.getProgress();
        int LoanDuration = skLoanDuration.getProgress();

        double R = (Interset/12)/100;
        double PerMonth = Math.round((LoanAmount * R * (Math.pow((1 + R), LoanDuration)) / ((Math.pow((1 + R), LoanDuration)) - 1)));
        double Total_Interest = Math.round((PerMonth * LoanDuration) - LoanAmount);
        double Total = Math.round((PerMonth * LoanDuration));



        tvLoanAmout.setText("$"+LoanAmount);
        tvIntersetRate.setText(Interset+"%");
        tvLoanDuration.setText(LoanDuration+" Month");
        tvTotal.setText("$"+Total);
        tvTotalInterest.setText("$"+Total_Interest);
        tvPerMonth.setText("$"+PerMonth);
        gridLayout1.setVisibility(View.VISIBLE);
    }
}
