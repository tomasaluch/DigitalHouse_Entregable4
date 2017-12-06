package com.example.tomasaluch.entregable2java.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.tomasaluch.entregable2java.Controller.ControllerPaints;
import com.example.tomasaluch.entregable2java.Model.AdapterRecyclePaints;
import com.example.tomasaluch.entregable2java.Model.Paint;
import com.example.tomasaluch.entregable2java.Model.ResultListener;
import com.example.tomasaluch.entregable2java.R;
import com.example.tomasaluch.entregable2java.View.Fragments.FacebookFragment;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterRecyclePaints.Notificable{

    List<Paint> paints = new ArrayList<>();
    AdapterRecyclePaints adapterRecyclePaints;

    CallbackManager callbackManager;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();



        final RecyclerView recyclerViewCancionesPlaylist = (RecyclerView) findViewById(R.id.recycle);

        //Si NO varia nada de la visual se puede utilizar esta opcion.
        recyclerViewCancionesPlaylist.setHasFixedSize(true);

        //Le pedimos que muestre las cosas en forma de lista
        recyclerViewCancionesPlaylist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        //Le pedimos que muestre las cosas en forma de grilla
        //recyclerViewPersonajes.setLayoutManager(new GridLayoutManager(this,2));
        obtenerPaints();
        //Creamos el adaptador del recycler
        final AdapterRecyclePaints adapter = new AdapterRecyclePaints(this,paints);
        adapterRecyclePaints = adapter;
        recyclerViewCancionesPlaylist.setAdapter(adapter);

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Quiere ingresar con Facebook?")
                .setTitle("Esto no es obligatorio");

        builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    recyclerViewCancionesPlaylist.setVisibility(View.INVISIBLE);
                FacebookFragment facebookFragment = new FacebookFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, facebookFragment).commit();


            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        builder.show();


    }


    public void obtenerPaints(){
        ControllerPaints controllerPaints = new ControllerPaints();
        controllerPaints.obtenerPaints(new ResultListener<List<Paint>>() {
            @Override
            public void finish(List<Paint> resultado) {
                adapterRecyclePaints.cargarNuevaLista(resultado);
            }
        });

    }

    @Override
    public void notificarClick(Paint paint) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }




    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information



                        } else {


                        }
                    }
                });
    }
}