package com.ianchen23.rubbercalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    final HashMap<String, Double> densityList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        densityList.put("577", 0.0451);
        densityList.put("834", 0.0452);
        densityList.put("553", 0.0453);
        densityList.put("906", 0.0454);
        densityList.put("920", 0.0455);
        densityList.put("922", 0.0456);
        densityList.put("886", 0.0457);

        final Spinner rubberTypeSpinner = findViewById(R.id.rubberTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rubberType, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        rubberTypeSpinner.setAdapter(adapter);
        rubberTypeSpinner.setOnItemSelectedListener(this);

        Button calBtn = findViewById(R.id.calBtn);
        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView resultDisplay = findViewById(R.id.resultDisplay);
                EditText rubberODEditText = findViewById(R.id.rubberODEditText);
                EditText mandrelODEditText = findViewById(R.id.mandrelODEditText);
                EditText elementLengthEditText = findViewById(R.id.elementLengthEditText);
                String rubberType = rubberTypeSpinner.getSelectedItem().toString();
                double density = densityList.get(rubberType);

                String rubberODStr = rubberODEditText.getText().toString();
                String mandrelODStr = mandrelODEditText.getText().toString();
                String elementLengthStr = elementLengthEditText.getText().toString();
                Double rubberOD = 0.0;
                Double mandrelOD = 0.0;
                Double elementLength = 0.0;
                if (!rubberODStr.equals("")) rubberOD = Double.parseDouble(rubberODStr);
                if (!mandrelODStr.equals("")) mandrelOD = Double.parseDouble(mandrelODStr);
                if (!elementLengthStr.equals("")) elementLength = Double.parseDouble(elementLengthStr);

                double area = (Math.pow(rubberOD + 0.5, 2) - Math.pow(mandrelOD, 2)) * Math.PI / 4.0;
                double result = area * elementLength * density;
                resultDisplay.setText((int)Math.ceil(result) + " lb/tool");
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String rubberType = adapterView.getItemAtPosition(i).toString();
        TextView rubberDensity = findViewById(R.id.rubberDensity);
        rubberDensity.setText("Rubber Density: " + densityList.get(rubberType) + " lb/in3");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
