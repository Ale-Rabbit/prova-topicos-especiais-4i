package alexandra.example.com.prova_pratica_topicos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela1 extends Activity {
    private Button botaoCadastrar, botaoListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela1);

        botaoCadastrar = (Button) findViewById(R.id.btnSalvar);
        botaoListar = (Button) findViewById(R.id.btnListar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irParaTela2 = new Intent(Tela1.this, Tela2.class);
                startActivity(irParaTela2);
            }
        });


        botaoListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irParaTela3 = new Intent(Tela1.this, Tela3.class);
                startActivity(irParaTela3);
            }
        });

    }
}