package com.example.hugo.trabalhoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hugo.trabalhoandroid.Model.Usuario;
import com.example.hugo.trabalhoandroid.Service.AddService;
import com.example.hugo.trabalhoandroid.Service.LoginService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button logar, cadastrar, voltar;
    EditText nome, login, email, senha;
    Boolean cadastrando;
    String token, nome_resp, resp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cadastrando = false;
        binding();

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaDoVoltar();
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cadastrando){
                    try {
                        resp = new AddService().execute(email.getText().toString(), login.getText().toString(), nome.getText().toString(), senha.getText().toString()).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    if (resp.equals("200")){
                            Toast.makeText(getApplicationContext(),"Usuário cadastrado com sucesso!",Toast.LENGTH_LONG).show();
                            limparCampos();
                            telaDoVoltar();
                        }
                }

                else {
                limparCampos();
                nome.setVisibility(View.VISIBLE);
                login.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                voltar.setVisibility(View.VISIBLE);
                logar.setVisibility(View.GONE);
                nome.requestFocus();
                cadastrando=true;
                }
            }
        });

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    resp = new LoginService().execute(login.getText().toString(),senha.getText().toString()).get();
                    limparCampos();
                    if(!resp.equals("404")) {
                        Intent i = new Intent(getApplication(), menuUsuario.class);
                        i.putExtra("obj", resp);
                        finish();
                        startActivityForResult(i, 1);
                    }else{
                        Toast.makeText(getApplicationContext(),"Não foi possível fazer login",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Não foi possível fazer login",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void binding() {
        logar = findViewById(R.id.btnMainLogin);
        cadastrar = findViewById(R.id.btnMainCadastrar);
        voltar = findViewById(R.id.btnMainVoltar);
        nome = findViewById(R.id.edtMainNome);
        login = findViewById(R.id.edtMainLogin);
        email = findViewById(R.id.edtMainEmail);
        senha = findViewById(R.id.edtMainSenha);
    }

    private void telaDoVoltar(){
        nome.setVisibility(View.GONE);
        email.setVisibility(View.GONE);
        voltar.setVisibility(View.GONE);
        login.setVisibility(View.VISIBLE);
        senha.setVisibility(View.VISIBLE);
        logar.setVisibility(View.VISIBLE);
        login.requestFocus();
        cadastrando = false;
        limparCampos();
    }

    private void limparCampos(){
        nome.setText("");
        login.setText("");
        email.setText("");
        senha.setText("");
        nome.setHint("Nome");
        login.setHint("Login");
        email.setHint("E-mail");
        senha.setHint("Senha");
    }
}
