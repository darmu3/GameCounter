package com.example.gamecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // поля
    private TextView score; // табло для вывода счёта игры
    private Button lionsTeam, panthersTeam; // кнопки команд
    private int countLions = 0, countPanthers = 0; // счётчики счёта команд

    // ключи для сохранения состояния
    private static final String KEY_COUNT_LIONS = "countLions";
    private static final String KEY_COUNT_PANTHERS = "countPanthers";

    // создание активности
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Создание активности", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);

        // привязка разметки к полям
        score = findViewById(R.id.score);
        lionsTeam = findViewById(R.id.lionsTeam);
        panthersTeam = findViewById(R.id.panthersTeam);

        // обработка нажатия кнопок
        lionsTeam.setOnClickListener(listener);
        panthersTeam.setOnClickListener(listener);

        // проверяем, сохранены ли данные
        if (savedInstanceState != null) {
            countLions = savedInstanceState.getInt(KEY_COUNT_LIONS);
            countPanthers = savedInstanceState.getInt(KEY_COUNT_PANTHERS);
            updateScore();
        }
    }

    // сохранение состояния
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT_LIONS, countLions);
        outState.putInt(KEY_COUNT_PANTHERS, countPanthers);
    }

    // восстановление состояния
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        countLions = savedInstanceState.getInt(KEY_COUNT_LIONS);
        countPanthers = savedInstanceState.getInt(KEY_COUNT_PANTHERS);
        updateScore();
    }

    // обработка нажатия кнопок
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // обработка счётчиков
            switch (view.getId()) {
                case R.id.lionsTeam:
                    countLions++;
                    break;
                case R.id.panthersTeam:
                    countPanthers++;
                    break;
            }
            updateScore();
        }
    };

    // обновление отображения счёта
    private void updateScore() {
        // вывод данных на экран смартфона
        score.setText(String.format("%02d", countLions) + " : " + String.format("%02d", countPanthers));
    }
}
