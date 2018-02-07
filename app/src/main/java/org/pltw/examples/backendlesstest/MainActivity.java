package org.pltw.examples.backendlesstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String BE_APP_ID = "FAEB45D1-AABF-A967-FFBC-4395AA912400";
    private final String BE_ANDROID_API_KEY = "C231B3AB-38C3-97E3-FFE3-03428E439D00";





    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for email in college app
        /* SharedPreferences mSharedPreferences = this.getSharedPreferences("",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("s", "d");
        editor.commit(); */

        Backendless.initApp(getApplicationContext(), BE_APP_ID, BE_ANDROID_API_KEY);

        //Person me = new Person("Sjostrom", "Bailee", 4.0, 67);

       // me.setObjectId("41870AF3-D1B7-BCF9-FF0A-8EE8E05ED100");


        String objectId = "41870AF3-D1B7-BCF9-FF0A-8EE8E05ED100";
        String whereClause = "objectId = '" + objectId + " ' ";
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);

        Backendless.Data.of(Person.class).find(query, new AsyncCallback<List<Person>>() {
            @Override
            public void handleResponse(List<Person> response) {
                if(!response.isEmpty()) {

                   final Person person = response.get(0);

                   final TextView tv = findViewById(R.id.text_view_person);
                    tv.setText(person.toString());

                   final EditText editTextFirstName = findViewById(R.id.editText_firstName);
                   final  EditText editTextLastName = findViewById(R.id.editText_lastName);

                    editTextFirstName.setText(person.getFirstName());
                    editTextLastName.setText(person.getLastName());

                    Button buttonSave = findViewById(R.id.button_save);

                    buttonSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            person.setFirstName(editTextFirstName.getText().toString());
                            person.setLastName(editTextLastName.getText().toString());

                            Backendless.Data.of(Person.class).save(person, new AsyncCallback<Person>() {
                                @Override
                                public void handleResponse(Person response) {
                                    tv.setText(person.toString());
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {

                                }
                            });
                        }
                    });

                    Log.d(this.getClass().getName(), "Retrieved " + person.toString());
                } else {
                    Log.i(this.getClass().getName(), "Retrieved an empty list.");
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(this.getClass().getName(), "There was an error: " + fault.getMessage());

            }
        });

       /* Backendless.Data.of(Person.class).save(me, new AsyncCallback<Person>() {
            @Override
            public void handleResponse(Person response) {
               Log.i(this.getClass().getName(), "Person was saved successfully.") ;
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(this.getClass().getName(), "There was an error: " + fault.getMessage());
            }
        }); */
    }

}
