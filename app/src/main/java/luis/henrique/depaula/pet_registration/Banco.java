package luis.henrique.depaula.pet_registration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "PetRegistration";
    private static final int VERSAO = 1;

    public Banco(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS registration (  " +

                "  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,  " +

                "  petName TEXT NOT NULL , " +

                "  yearsPet INT , " +

                "  specie TEXT NOT NULL ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
