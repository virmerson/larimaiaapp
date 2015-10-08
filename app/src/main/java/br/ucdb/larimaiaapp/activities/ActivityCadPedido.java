package br.ucdb.larimaiaapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import br.ucdb.larimaiaapp.R;
import br.ucdb.larimaiaapp.api.ApiWeb;
import br.ucdb.larimaiaapp.model.Cliente;
import br.ucdb.larimaiaapp.model.ItemPedido;
import br.ucdb.larimaiaapp.model.Pedido;
import br.ucdb.larimaiaapp.model.Produto;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by MarcosVinicius on 23/09/2015.
 */
public class ActivityCadPedido extends AppCompatActivity{

    Produto pro = new Produto();

    Integer qtdInt;

    @Bind(R.id.tx_lc)
    EditText textLocal;

    @Bind(R.id.tx_cerimonial)
    EditText textCerimonial;

    @Bind(R.id.txt_he)
    EditText horaEvento;

    @Bind(R.id.txt_dte)
    EditText dataEvento;

    @Bind(R.id.txt_dtped)
    EditText dataPedido;

    @Bind(R.id.tx_indicacao)
    EditText indicacao;

    @Bind(R.id.txt_obs)
    EditText obs;

    @Bind(R.id.txt_cep)
    EditText cep;

    @Bind(R.id.txt_rua)
    EditText rua;

    @Bind(R.id.txt_complemento)
    EditText complemento;

    @Bind(R.id.txt_bairro)
    EditText bairro;

    @Bind(R.id.txt_numero)
    EditText numero;

    @Bind(R.id.txt_cidade)
    EditText cidade;

    @Bind(R.id.txt_estado)
    EditText estado;

    @Bind(R.id.txt_qtd)
    EditText quantidade;

    @Bind(R.id.lv_cli)
    Spinner cliente;

    @Bind(R.id.spi_pro)
    Spinner produto;

    @Bind(R.id.spi_tipo_evento)
    Spinner tipoEvento;

    final ItemPedido ip = new ItemPedido();

    private List<Cliente> listaClientes;

    @OnClick(R.id.btn_salvar_evento_pedido)
    public void salvar(){

        final Pedido pedido = new Pedido();

        if(textLocal.getText() != null)
            pedido.setLocalContrato(textLocal.getText().toString());

        if(textCerimonial.getText() != null)
            pedido.setCerimonial(textCerimonial.getText().toString());

        if(horaEvento.getText() != null)
            pedido.setHora(horaEvento.getText().toString());

        if(dataEvento.getText() != null) {
            Date date = null;
            String dataText = new String(dataEvento.getText().toString());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                date = format.parse(dataText);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            pedido.setDataEvento(date);
        }

        if(dataPedido.getText() != null){
            Date date = null;
            String dataText = new String(dataPedido.getText().toString());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                date = format.parse(dataText);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            pedido.setDataPedido(date);
        }

        if(indicacao.getText()!= null)
            pedido.setIndicacao(indicacao.getText().toString());

        if(obs.getText()!=null)
            pedido.setObs(obs.getText().toString());

        if(cep.getText()!=null)
            pedido.setCep(cep.getText().toString());

        if(rua.getText()!=null)
            pedido.setRua(rua.getText().toString());

        if(complemento.getText()!=null)
            pedido.setComplemento(complemento.getText().toString());

        if(bairro.getText()!=null)
            pedido.setBairro(bairro.getText().toString());

        if(numero.getText()!=null)
            pedido.setNumero(Integer.parseInt(numero.getText().toString()));

        if(cidade.getText()!=null)
            pedido.setCidade(cidade.getText().toString());

        if(estado.getText()!=null)
            pedido.setEstado(estado.getText().toString());

        if(quantidade.getText()!=null) {
            String qtd = quantidade.getText().toString();
            qtdInt = Integer.parseInt(qtd);
            ip.setQuantidade(qtdInt);
        }

        ip.setPedido(pedido);

        cliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cliente cli = new Cliente();
                cli = (Cliente) parent.getItemAtPosition(position);
                pedido.setCliente(cli);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tipoEvento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TipoEvento te = new TipoEvento();
                te = (TipoEvento) parent.getItemAtPosition(position);
                pedido.setEvento(te);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        produto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pro = (Produto) parent.getItemAtPosition(position);
                ip.setProduto(pro);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Double total = ip.getValor() * qtdInt;
        ip.setValor(total);
        List<ItemPedido> listaIP = new ArrayList<>();
        listaIP.add(ip);
        pedido.setListaItemPedido(listaIP);

        ApiWeb.getRotas().salvarPedido(pedido, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Toast.makeText(ActivityCadPedido.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActivityCadPedido.this, "Falha ao salvar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        ButterKnife.bind(this);

        ApiWeb.getRotas().listarCliente(new Callback<List<Cliente>>() {
            @Override
            public void success(List<Cliente> clientes, Response response) {
                ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(ActivityCadPedido.this, android.R.layout.simple_list_item_activated_1, clientes);
                cliente.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        ApiWeb.getRotas().listarProduto(new Callback<List<Produto>>() {
            @Override
            public void success(List<Produto> produtos, Response response) {
                ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(ActivityCadPedido.this, android.R.layout.simple_list_item_activated_1, produtos);
                produto.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        ApiWeb.getRotas().listarTipoEvento(new Callback<List<TipoEvento>>() {
            @Override
            public void success(List<TipoEvento> tiposEventos, Response response) {
                ArrayAdapter<TipoEvento> adapter = new ArrayAdapter<TipoEvento>(ActivityCadPedido.this, android.R.layout.simple_list_item_activated_1, tiposEventos);
                tipoEvento.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
