package luis.henrique.depaula.pet_registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class form extends AppCompatActivity {
    private EditText petName;
    private Spinner spSpecies;
    private EditText yearsPet;
    private Button btnSave;
    private String action;
    private Registration registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        petName = findViewById(R.id.petName);

        spSpecies = findViewById(R.id.spSpecies);

        yearsPet = findViewById(R.id.yearsPet);

        btnSave = findViewById(R.id.btnSave);

        action = getIntent().getStringExtra("action");
        if (action.equals("edit")) {
            loadForm();
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void loadForm() {
        int id = getIntent().getIntExtra("idRegistration", 0);
        registration = PET.getRegistrationById(this, id);
        petName.setText(registration.getPetName());
        String[] specie = getResources().getStringArray(R.array.spSpecies);
        for (int i = 1; i < specie.length; i++) {
            if (registration.getSpecie().equals(specie[i])) {
                spSpecies.setSelection(i);
                break;
            }
        }
    }

    private void save() {
        String pname = petName.getText().toString();
        if (pname.isEmpty() || spSpecies.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Você deve preencher todos os campos!", Toast.LENGTH_LONG).show();
        } else {
            if (action.equals("insert")) {
                registration = new Registration();
            }
            registration.setPetName(pname);
            registration.setSpecie(spSpecies.getSelectedItem().toString());
            if (action.equals("insert")) {
                PET.insert(this, registration);
                petName.setText("");
                spSpecies.setSelection(0, true);
            } else {
                PET.edit(this, registration);
                finish();
            }
        }
    }
}