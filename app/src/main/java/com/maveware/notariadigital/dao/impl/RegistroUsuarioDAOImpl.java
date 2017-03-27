package com.maveware.notariadigital.dao.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.maveware.notariadigital.dao.RegistroUsuarioDAO;

/**
 * Created by GOAM on 22/03/2017.
 */

public class RegistroUsuarioDAOImpl implements RegistroUsuarioDAO {
    public static final String TAG = "RegistroUsuarioDAOImpl";
    FirebaseAuth.AuthStateListener authStateListener;
    boolean registroOk = false;

    @Override
    public boolean registroUsuario(String email, String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                        registroOk = true;
                else
                    Log.e(TAG, task.getException().getMessage());

            }
        });

        return registroOk;
    }

}
