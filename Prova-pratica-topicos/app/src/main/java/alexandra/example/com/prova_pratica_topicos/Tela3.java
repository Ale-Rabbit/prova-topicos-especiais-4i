package alexandra.example.com.prova_pratica_topicos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import DAO.TelevisaoDAO;
import POJO.Televisao;

/**
 * Created by alexandra on 28/06/17.
 */

public class Tela3 extends ListActivity {
    private TelevisaoDAO televisaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        televisaoDAO = new TelevisaoDAO(getApplicationContext());

      // chamando método listar de tevelisaoDAO
        ArrayList<Televisao> televisoes = televisaoDAO.listar();

        ArrayAdapter<Televisao> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,televisoes);

        setListAdapter(adapter);
    }

    // ação para cada item da lista, clicado
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Televisao televisao = (Televisao) getListAdapter().getItem(position);
        Intent enviarDados = new Intent(Tela3.this, Tela4.class);
        Bundle dados = new Bundle();
        dados.putSerializable("televisao", televisao);
        enviarDados.putExtras(dados);

        startActivity(enviarDados);
    }
}
