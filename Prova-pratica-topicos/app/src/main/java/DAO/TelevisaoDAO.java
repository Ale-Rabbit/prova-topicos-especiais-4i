package DAO;

import POJO.Televisao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Created by alexandra on 28/06/17.
 */

public class TelevisaoDAO {
    private SQLiteDatabase db;
    private static final String TABELA = "televisao";
    private static final String ID = "_id";
    private static final String MARCA = "marca";
    private static final String MODELO = "modelo";
    private static final String PESO = "peso";
    private static final String COMPONENTE_CAP = "cap";
    private static final String COMPONENTE_DIG = "digital";
    private static final String COMPONENTE_SAP = "sap";
    private static final String RESOLUCAO = "resolucao";
    public static final String CRIAR_TABELA = "CREATE TABLE " + TABELA + "("
                                                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                                                + MARCA + " TEXT NOT NULL, "
                                                + MODELO + " TEXT NOT NULL, "
                                                + PESO + " TEXT NOT NULL, "
                                                + COMPONENTE_CAP + " TEXT NOT NULL,"
                                                + COMPONENTE_DIG + " TEXT NOT NULL,"
                                                + COMPONENTE_SAP + " TEXT NOT NULL,"
                                                + RESOLUCAO + " TEXT NOT NULL"
                                                + ");";


    public TelevisaoDAO(Context ctx) {
        db = SQLHelper.getIntance(ctx).getWritableDatabase();
    }

    public boolean salvar(Televisao televisao) {
        long id = this.db.insert(TABELA, null, this.contatoToValues(televisao));
        return id != -1;
    }

    public boolean editar(Televisao televisao){
        String where = ID + "=?";
        String[] whereArgs = {String.valueOf(televisao.getId())};
        int registrosAfetados = this.db.update(TABELA,this.contatoToValues(televisao),where,whereArgs);
        return registrosAfetados > 0;
    }

    public boolean excluir(int id){
        String where = ID + "=?";
        String[] whereArgs = {String.valueOf(id)};
        int excluidos = this.db.delete(TABELA,where, whereArgs);
        return excluidos > 0;
    }

    public ArrayList<Televisao> listar(){
        ArrayList<Televisao> televisoes = new ArrayList<>();
        String sqlListar = " SELECT * FROM " + TABELA;
        Cursor cursor = this.db.rawQuery(sqlListar,null);


        // indice de cada coluna
        int iId = cursor.getColumnIndex(ID);
        int iMarca = cursor.getColumnIndex(MARCA);
        int iModelo = cursor.getColumnIndex(MODELO);
        int iPeso = cursor.getColumnIndex(PESO);
        int iComponenteCap = cursor.getColumnIndex(COMPONENTE_CAP);
        int iComponenteDig = cursor.getColumnIndex(COMPONENTE_CAP);
        int iComponenteSap = cursor.getColumnIndex(COMPONENTE_CAP);
        int iResolucao = cursor.getColumnIndex(RESOLUCAO);

        while (cursor.moveToNext()){
            televisoes.add(new Televisao(
                    cursor.getInt(iId),
                    cursor.getString(iMarca),
                    cursor.getString(iModelo),
                    cursor.getString(iPeso),
                    cursor.getInt(iComponenteCap) == 1 ? true : false,
                    cursor.getInt(iComponenteDig) == 1 ? true : false,
                    cursor.getInt(iComponenteSap) == 1 ? true : false,
                    cursor.getString(iResolucao)
            ));
        }

        return televisoes;

    }

    private ContentValues contatoToValues(Televisao televisao){
        ContentValues cv = new ContentValues();
        cv.put(MARCA,televisao.getMarca());
        cv.put(MODELO,televisao.getModelo());
        cv.put(PESO,televisao.getPeso());
        cv.put(COMPONENTE_CAP,televisao.isComponenteCap());
        cv.put(COMPONENTE_DIG,televisao.isComponenteDig());
        cv.put(COMPONENTE_SAP,televisao.isComponenteSap());
        cv.put(RESOLUCAO,televisao.getResolucao());
        return cv;
    }


}
