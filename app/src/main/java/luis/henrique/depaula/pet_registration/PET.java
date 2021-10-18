package luis.henrique.depaula.pet_registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PET {
    public static void insert(Context context, Registration registration) {
        ContentValues values = new ContentValues();
        values.put("petName", registration.getPetName());
        values.put("yearsPet", registration.getYearsPet());
        values.put("specie", registration.getSpecie());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("registration", null, values);
    }

    public static void edit(Context context, Registration registration) {
        ContentValues values = new ContentValues();
        values.put("petName", registration.getPetName());
        values.put("yearsPet", registration.getYearsPet());
        values.put("specie", registration.getSpecie());


        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("registration", values, "id = " + registration.getId(), null);
    }

    public static void delete(Context context, int idRegistration) {

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("registration", " id = " + idRegistration, null);
    }

    public static List<Registration> getRegistration(Context context) {
        List<Registration> registrations = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM registration ORDER BY petName ", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Registration rpet = new Registration();
                rpet.setId(cursor.getInt(0));
                rpet.setPetName(cursor.getString(1));
                rpet.setSpecie(cursor.getString(2));
                rpet.setYearsPet(cursor.getString(3));
                registrations.add(rpet);

            } while (cursor.moveToNext());
        }
        return registrations;
    }


    public static Registration getRegistrationById(Context context, int idRegistration) {

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM produtos WHERE id =  " + idRegistration, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Registration rpet = new Registration();
            rpet.setId(cursor.getInt(0));
            rpet.setPetName(cursor.getString(1));
            rpet.setSpecie(cursor.getString(2));
            rpet.setYearsPet(cursor.getString(3));

            return rpet;

        } else {
            return null;
        }
    }
}
