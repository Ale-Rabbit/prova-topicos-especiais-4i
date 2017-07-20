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

public class Tela2 extends Activity {
    private AutoCompleteTextView marca;
    private EditText modelo, peso;
    private CheckBox cbCap, cbDigital, cbSAP;
    private RadioGroup resolucao;
    private Button cadastrar;

    private Televisao televisao;
    private TelevisaoDAO tevelisaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela2);

        this.inicializaComponentes();


        this.cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             // setar marca, modelo e peso
                televisao.setMarca(marca.getText().toString());
                televisao.setModelo(modelo.getText().toString());
                televisao.setPeso(peso.getText().toString());

             // if para setar componente
                if (cbCap.isChecked()){
                    televisao.setComponenteCap(true);
                }
                if (cbDigital.isChecked()) {
                    televisao.setComponenteDig(true);
                }
                if (cbSAP.isChecked()){
                    televisao.setComponenteSap(true);
                }


             // switch case para setar resolução
                switch (resolucao.getCheckedRadioButtonId()){
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


                // chamando método salvar de tevelisaoDAO
                boolean salvou = tevelisaoDAO.salvar(televisao);

                // texto de Toast de acordo com variavel salvou.
                String msgSalvou = (salvou)? "Salvo com sucesso!": "Erro ao salvar!";

                // parmetros de Toast: contexto, texto e duração.
                Toast.makeText(Tela2.this,msgSalvou,Toast.LENGTH_SHORT).show();

                // Intenção para ir para tela anterior
                Intent irParaTela1 = new Intent(Tela2.this, Tela1.class);
                startActivity(irParaTela1);

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
        this.cadastrar = (Button) findViewById(R.id.btnSalvar);

        this.televisao = new Televisao();
        this.tevelisaoDAO = new TelevisaoDAO(getApplicationContext());


        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MARCAS );

        marca.setAdapter(adapter);

    }

    // Componentes do AutoCompleteTextView
    private static final String[] MARCAS = new String[] {"LG", "Sony", "Sansung", "Phillips"};


}
