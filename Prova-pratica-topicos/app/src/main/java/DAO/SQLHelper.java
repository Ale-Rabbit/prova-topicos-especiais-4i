package DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alexandra on 28/06/17.
 */

public class SQLHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "televisao.bd";
    private static final int VERSAO = 1;
    private static SQLHelper instance;


    public SQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    public static SQLHelper getIntance(Context ctx){
        if (instance == null){
            instance = new SQLHelper(ctx);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TelevisaoDAO.CRIAR_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
