package com.tim.picassoinrecyclerview.ui.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.tim.picassoinrecyclerview.R;
import com.tim.picassoinrecyclerview.model.Person;
import com.tim.picassoinrecyclerview.ui.adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvPerson;
    ArrayList<Person> persons;
    RecyclerViewAdapter adapter;
    private EditText tvSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        persons = new ArrayList<>();

        rvPerson = (RecyclerView) findViewById(R.id.recycler_view_person);

        rvPerson.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvPerson.setLayoutManager(llm);

        persons.add(new Person("Мартин Лютер Кинг", "http://100grp.ru/wp-content/uploads/2011/09/0192-150x150.jpg"));
        persons.add(new Person("Сергей Павлович Королёв", "http://100grp.ru/wp-content/uploads/2011/09/0191-150x150.jpg"));
        persons.add(new Person("Дэн Сяопин", "http://100grp.ru/wp-content/uploads/2011/09/0190-150x150.jpg"));
        persons.add(new Person("Роберт Оппенгеймер", "http://100grp.ru/wp-content/uploads/2011/09/0189-150x150.jpg"));
        persons.add(new Person("Альфред Хичкок", "http://100grp.ru/wp-content/uploads/2011/09/0188-150x150.jpg"));
        persons.add(new Person("Мао Цзэдун", "http://100grp.ru/wp-content/uploads/2011/09/0187-150x150.jpg"));
        persons.add(new Person("Чарльз Спенсер", "http://100grp.ru/wp-content/uploads/2011/09/0186-150x150.jpg"));
        persons.add(new Person("Адольф Гитлер", "http://100grp.ru/wp-content/uploads/2011/09/0185-150x150.jpg"));
        persons.add(new Person("Коко Шанель", "http://100grp.ru/wp-content/uploads/2011/09/0183-150x150.jpg"));
        persons.add(new Person("Исаак Ньютон", "http://www.calend.ru/img/content_events/i0/525.jpg"));

        adapter = new RecyclerViewAdapter(this, persons);

        rvPerson.setAdapter(adapter);

        tvSelect = (EditText) findViewById(R.id.text_view_list_select);
        tvSelect.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void filter(final CharSequence chars) {

        new AsyncTask<CharSequence, Void, ArrayList>() {

            @Override
            protected ArrayList doInBackground(CharSequence... params) {

                CharSequence charSequence = params[0].toString().toLowerCase();

                if (charSequence == null || charSequence.length() == 0) {
                    return persons;
                } else {

                    ArrayList<Person> newPersonList = new ArrayList<Person>();

                    for (Person p : persons) {
                        if (p.getmName().toUpperCase().contains(charSequence.toString().toUpperCase())) {
                            newPersonList.add(p);
                        }
                    }
                    return newPersonList;
                }
            }

            @Override
            protected void onPostExecute(ArrayList arrayList) {
                super.onPostExecute(arrayList);

                if (arrayList == null || arrayList.size() == 0) {
                    adapter.resetData(arrayList);
                    Toast.makeText(MainActivity.this, "Совпадений не найдено", Toast.LENGTH_SHORT).show();
                } else {
                    adapter.resetData(arrayList);
                }
            }
        }.execute(chars);
    }
}
