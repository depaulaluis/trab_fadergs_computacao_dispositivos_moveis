package luis.henrique.depaula.pet_registration;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvRegistration;
    private ArrayAdapter adapter;
    private List<Registration> listPet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvRegistration = findViewById(R.id.lvRegistration);

        loadRegistration();

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, form.class);
                intent.putExtra("action", "insert");
                startActivity(intent);

            }
        });

        lvRegistration.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idRegistration = listPet.get( position ).getId();
                Intent intent = new Intent(MainActivity.this, form.class);
                intent.putExtra("action", "edit");
                intent.putExtra("idRegistration" , idRegistration);
                startActivity(intent);
            }
        });

        lvRegistration.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                delete(position);
                return true;
            }
        });

    }

    private void delete(int posicao){
        Registration rpet = listPet.get( posicao );
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Excluir...");
        alert.setIcon(android.R.drawable.ic_delete);
        alert.setMessage("Confirma a exclusão do produto " + rpet.getPetName() +"?");
        alert.setNeutralButton("Cancelar", null);

        alert.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PET.delete(MainActivity.this, rpet.getId());
                loadRegistration();
            }
        });
        alert.show();
    }
    private void loadRegistration(){

        listPet = PET.getRegistration(this);
        if( listPet.size() == 0 ) {
            Registration fake = new Registration("Campo vazio", "idade nao informada","lista vazia");
            listPet.add(fake);
            lvRegistration.setEnabled(false);
        }else{
            lvRegistration.setEnabled(true);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listPet);
        lvRegistration.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}