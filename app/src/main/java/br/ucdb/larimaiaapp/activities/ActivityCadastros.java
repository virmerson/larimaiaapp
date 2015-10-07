package ucdb.larimaia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ucdb.larimaia.R;

/**
 * Created by Junio on 20/09/2015.
 */
public class ActivityCadastros extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastros);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_logon)
    public void logon(){
        Intent irParaOpcao =  new Intent(this, ActivityLogon.class);
        startActivity(irParaOpcao);
    }
    @OnClick(R.id.btn_cliente)
    public void cliente(){
        Intent irParaOpcao =  new Intent(this, ActivityCliente.class);
        startActivity(irParaOpcao);
    }
    @OnClick(R.id.btn_produto)
    public void produto(){
        Intent irParaOpcao =  new Intent(this, ActivityProduto.class);
        startActivity(irParaOpcao);
    }
    @OnClick(R.id.btn_tipo_evento)
    public void tipoEvento(){
        Intent irParaOpcao =  new Intent(this, ActivityTipoEvento.class);
        startActivity(irParaOpcao);
    }
}
