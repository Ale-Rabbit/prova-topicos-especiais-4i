package alexandra.example.com.prova_pratica_topicos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


import DAO.TelevisaoDAO;
import POJO.Televisao;

/**
 * Created by alexandra on 28/06/17.
 */

public class Tela4 extends Activity {
    private AutoCompleteTextView marca;
    private EditText modelo, peso;
    private CheckBox cbCap, cbDigital, cbSAP;
    private RadioGroup resolucao;
    private Button salvar;
    private Button excluir;

    private Televisao televisao;
    protected TelevisaoDAO televisaoDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela4);

        this.inicializaComponentes();

        final int id1 = televisao.getId();
        this.marca.setText(televisao.getMarca());
        this.modelo.setText(televisao.getModelo());
        this.peso.setText(televisao.getPeso());

        if (televisao.isComponenteCap() == true) {
            this.cbCap.setChecked(true);
        }
        if (televisao.isComponenteDig() == true) {
            this.cbDigital.setChecked(true);
        }
        if (televisao.isComponenteSap() == true) {
            this.cbSAP.setChecked(true);
        }

        if (televisao.getResolucao().equals("HD")) {
            this.resolucao.check(R.id.rbHD);
        }
        if (televisao.getResolucao().equals("Full HD")) {
            this.resolucao.check(R.id.rbFullHd);
        }
        if (televisao.getResolucao().equals("4K")) {
            this.resolucao.check(R.id.rb4k);
        }


        this.salvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                switch (resolucao.getCheckedRadioButtonId()) {
                    case R.id.rbHD:
                        televisao.setResolucao("HD");
                        break;
                    case R.id.rbFullHd:
                        televisao.setResolucao("Full HD");
                        break;
                    case R.id.rb4k:
                        televisao.setResolucao("4K");
                        break;
                }

                televisao.setComponenteDig(cbCap.isChecked());
                televisao.setComponenteCap(cbCap.isChecked());
                televisao.setComponenteSap(cbSAP.isChecked());


                televisao.setMarca(marca.getText().toString());
                televisao.setModelo(modelo.getText().toString());
                televisao.setPeso(peso.getText().toString());



                boolean editou = televisaoDAO.editar(televisao);
                String msgEditou = (editou) ? "Editado com sucesso." : "Erro ao editar.";
                Toast.makeText(Tela4.this, msgEditou, Toast.LENGTH_SHORT).show();

                Intent irParaTela1 = new Intent(Tela4.this, Tela1.class);
                startActivity(irParaTela1);

            }


        });

        this.excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean apagou = televisaoDAO.excluir(id1);
                String mensagemEditar = (apagou) ? "Apagado com sucesso!" : "Erro ao apagar.";
                Toast.makeText(Tela4.this, mensagemEditar, Toast.LENGTH_SHORT).show();

                Intent it = new Intent(Tela4.this, Tela1.class);
                startActivity(it);
            }
        });

    }


    private void inicializaComponentes(){

        this.marca = (AutoCompleteTextView) findViewById(R.id.ctMarca);
        this.modelo = (EditText) findViewById(R.id.etModelo);
        this.peso = (EditText) findViewById(R.id.etPeso);
        this.cbCap = (CheckBox) findViewById(R.id.cbClosed);
        this.cbDigital = (CheckBox) findViewById(R.id.cbCDigital);
        this.cbSAP = (CheckBox) findViewById(R.id.cbFunSAP);
        this.resolucao = (RadioGroup) findViewById(R.id.rgResolucao);
        this.salvar = (Button) findViewById(R.id.btnSalvar);
        this.excluir = (Button) findViewById(R.id.btnExcluir);


        Bundle pegarTelevisao = getIntent().getExtras();
        televisao = (Televisao) pegarTelevisao.getSerializable("televisao");

        this.televisaoDAO = new TelevisaoDAO(getApplicationContext());


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, MARCAS);

        marca.setAdapter(adapter);

    }

    private static final String[] MARCAS = new String[] {"LG", "Sony", "Sansung", "Phillips"};

}