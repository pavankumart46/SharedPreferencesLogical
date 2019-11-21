package pavankreddy.blogspot.sharedprefadvanced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    EditText et1,et2;
    SharedPreferences sharedPreferences;
    String key,val;
    TextView results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.editText2);
        results = findViewById(R.id.results);
        sharedPreferences = getSharedPreferences("FILENAME",MODE_PRIVATE);
        assert sharedPreferences != null;
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }



    private void displayData() {
        results.setText("");
        if(sharedPreferences!=null) {
            Map<String, ?> keys = sharedPreferences.getAll();
            for (Map.Entry<String, ?> entry : keys.entrySet()) {
                results.append(entry.getKey()+" : "+sharedPreferences.getString(entry.getKey(),"")+"\n");
            }
        }
    }

    public void saveData(View view)
    {
        key = et1.getText().toString();
        val = et2.getText().toString();
        boolean flag = true;
        if(sharedPreferences!=null){
            Map<String,?> keys = sharedPreferences.getAll();
            for(Map.Entry<String,?> entry : keys.entrySet()){
                if(val.equals(sharedPreferences.getString(entry.getKey(),""))){
                    flag = false;
                }
            }
            if(flag){
                saveDataToPreferences();
            }
            else{
                Toast.makeText(this, "EMAIL ALREADY EXISTS!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key,val);
            editor.apply();
        }


    }

    private void saveDataToPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,val);
        editor.apply();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        displayData();
    }
}
