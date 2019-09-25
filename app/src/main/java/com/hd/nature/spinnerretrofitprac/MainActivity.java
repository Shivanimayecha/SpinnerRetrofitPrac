package com.hd.nature.spinnerretrofitprac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.hd.nature.spinnerretrofitprac.retrofit.RetroClient;
import com.hd.nature.spinnerretrofitprac.retrofit.RetroInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spin;
    String TAG = "TAG";
    ArrayList<data_model> arrayList = new ArrayList<>();
    List<String> countryname=new ArrayList<>();
    String w_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findview();
        initView();

    }

    private void findview() {

        spin = findViewById(R.id.spinner);
    }

    private void initView() {


        try {

            RetroInterface retroInterface = RetroClient.getRetroInterface();
            Call<String> call = retroInterface.getData();
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    Log.e(TAG, "onResponse: " + response.body());
                    if (response.body() != null) {

                        parseResponse(response.body(), "");

                    } else {
                        //showSomethingwentWrong();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseResponse(String body, String s) {


        try {

            JSONObject obj = new JSONObject(body);
            if (obj.optString("status").equals("true")) ;
            {

                JSONArray jsonArray = obj.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {

                    data_model model = new data_model();
                    //JSONObject jsonObject = jsonArray.getJSONObject(i);
                    /*model.setId(jsonObject.optString("id"));
                    model.setCountry(jsonObject.optString("country"));
                    model.setCity(jsonObject.optString("city"));*/

                    String country=jsonArray.getJSONObject(i).getString("country");
                    String id=jsonArray.getJSONObject(i).getString("id");
                    //Log.e("City", ">>>>>" + city);

                    data_model c=new data_model();
                    c.setId(id);
                    c.setCountry(country);
                    countryname.add(country);
                    arrayList.add(c);
                }


            }
            spin.setOnItemSelectedListener(MainActivity.this);

            ArrayAdapter aa = new ArrayAdapter(MainActivity.this,R.layout.spinner_item,countryname);
            spin.setAdapter(aa);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        w_id=arrayList.get(i).getId();
        Toast.makeText(this, arrayList.get(i).getCountry(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
