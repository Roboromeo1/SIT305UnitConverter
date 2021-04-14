package com.adhityaram.sit305unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import static android.text.TextUtils.isEmpty;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner menu;
    private EditText input;
    private TextView result1;
    private TextView result2;
    private TextView result3;
    private TextView unit1;
    private TextView unit2;
    private TextView unit3;
    private static DecimalFormat df = new DecimalFormat("0.00");
    private Object MainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = findViewById(R.id.menu);
        input = findViewById(R.id.input);
        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);
        result3 = findViewById(R.id.result3);
        unit1 = findViewById(R.id.unit1);
        unit2 = findViewById(R.id.unit2);
        unit3 = findViewById(R.id.unit3);

        menu.setOnItemSelectedListener(this);

        String[] selectUnit = getResources().getStringArray(R.array.selectUnit);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, selectUnit);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menu.setAdapter(adapter);
    }

    public void lengthConverter(View view)
    {
        if(userPrompt(view)) {
            result1.setText("Centimeter");
            result2.setText("Foot");
            result3.setText("Inch");

            Toast.makeText(this, "Length Converter", Toast.LENGTH_SHORT).show();

            if (!isEmpty(input.getText().toString())) {
                double inputDouble = Double.parseDouble(input.getText().toString());
                double temp = inputDouble * 100;
                unit1.setText(df.format(temp));
                temp = inputDouble * 3.28;
                unit2.setText(df.format(temp));
                temp = inputDouble * 39.37;
                unit3.setText(df.format(temp));
            }
        }
    }

    public void tempConverter(View view)
    {
        if(userPrompt(view)) {
            result1.setText("Fahrenheit");
            result2.setText("Kelvin");
            result3.setText("");

            Toast.makeText(this, "Temperature Converter", Toast.LENGTH_SHORT).show();

            if (!isEmpty(input.getText().toString())) {
                double inputDouble = Double.parseDouble(input.getText().toString());
                double temp = (inputDouble * 9 / 5) + 32;
                unit1.setText(df.format(temp));
                temp = inputDouble + 273.15;
                unit2.setText(df.format(temp));
                unit3.setText("");
            }
        }
    }

    public void weightConverter(View view)
    {
        if(userPrompt(view)) {
            result1.setText("Gram");
            result2.setText("Ounce");
            result3.setText("Pound");

            Toast.makeText(this, "Weight Converter", Toast.LENGTH_SHORT).show();

            if (!isEmpty(input.getText().toString())) {
                double inputDouble = Double.parseDouble(input.getText().toString());
                double temp = inputDouble * 1000;
                unit1.setText(df.format(temp));
                temp = inputDouble * 35.274;
                unit2.setText(df.format(temp));
                temp = inputDouble * 2.205;
                unit3.setText(df.format(temp));
            }
        }
    }

    public boolean onCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_user_prompt);
        builder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        return false;
    }

    public boolean userPrompt(View view)
    {
        boolean result = false;

        if (view.getId() == R.id.imageView && menu.getSelectedItemPosition() != 0 ) {
            result = onCreateDialog();
        }
        else if (view.getId() == R.id.imageView2 && menu.getSelectedItemPosition() != 1) {
            result = onCreateDialog();
        }
        else if (view.getId() == R.id.imageView3 && menu.getSelectedItemPosition() != 2) {
            result = onCreateDialog();
        }
        else result = true;

        return result;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        if (parent.getId() == R.id.menu)
        {
            if(position == 0) lengthConverter(view);
            if(position == 1) tempConverter(view);
            if(position == 2) weightConverter(view);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}